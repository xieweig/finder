package cn.sisyphe.coffee.bill.domain.mistake;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.db.DbStation;
import cn.sisyphe.coffee.bill.domain.base.model.enums.*;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.cargo.repo.CargoRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.rawmaterial.RawMaterialRepository;
import cn.sisyphe.coffee.bill.viewmodel.mistake.ConditionQueryMistakeBill;
import cn.sisyphe.coffee.bill.viewmodel.plan.ConditionQueryPlanBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Amy on 2018/1/18.
 *         describe：
 */
@Service
public class MistakeBillExtraServiceImpl extends AbstractBillExtraService<MistakeBill, ConditionQueryMistakeBill> implements MistakeBillExtraService {
    @Autowired
    public MistakeBillExtraServiceImpl(BillRepository<MistakeBill> billRepository) {
        super(billRepository);
    }

    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Override
//    protected void addExtraExpression(ConditionQueryMistakeBill conditionQuery, List<Expression<Boolean>> expressions, Root<MistakeBill> root, CriteriaBuilder criteriaBuilder) {
//
//        //追加误差单类型的条件
//        expressions.add(criteriaBuilder.equal(root.get("specificBillType").as(BillTypeEnum.class), BillTypeEnum.NO_PLAN));
//        //目标匹配
//        if (conditionQuery.obtainTarget(conditionQuery.getTargetEnumSet())) {
//            //都匹配
//            if (!StringUtils.isEmpty(conditionQuery.getTargetCode())) {
//                //当目标编号不为空时-匹配原料的编号和货物编号
//                expressions.add(
//                        criteriaBuilder.or(
//                                root.get("billDetails").get("dbGoods").get("rawMaterialCode").as(String.class).in( conditionQuery.getTargetCode()),
//                                root.get("billDetails").get("dbGoods").get("cargoCode").as(String.class).in(conditionQuery.getTargetCode())
//                        )
//                );
//            }
//            if (!StringUtils.isEmpty(conditionQuery.getTargetName())) {
//                //当目标名称不为空时-匹配原料编号集合和货物编号集合
//                List<String> cargoCodeList = cargoRepository.findCargoCodesByCargoName(conditionQuery.getTargetName());
//                List<String> rawMaterialCodeList = rawMaterialRepository.findRawMaterialCodesByRawMaterialName(conditionQuery.getTargetName());
//                expressions.add(
//                        criteriaBuilder.or(
//                                root.get("dbGoods").get("cargoCode").as(String.class).in(cargoCodeList),
//                                root.get("dbGoods").get("rawMaterialCode").as(String.class).in(rawMaterialCodeList)
//                        )
//                );
//            }
//        } else {
//            //匹配其中一个
//            for (BasicEnum basicEnum : conditionQuery.getTargetEnumSet()) {
//                expressions.add(criteriaBuilder.equal(root.get("basicEnum").as(BasicEnum.class), basicEnum));
//                if (BasicEnum.BY_CARGO.equals(basicEnum)) {
//                    if (!StringUtils.isEmpty(conditionQuery.getTargetCode())) {
//                        expressions.add(criteriaBuilder.equal(root.get("dbGoods").get("cargoCode").as(String.class), conditionQuery.getTargetCode()));
//                    }
//                    if (!StringUtils.isEmpty(conditionQuery.getTargetName())) {
//                        List<String> cargoCodeList = cargoRepository.findCargoCodesByCargoName(conditionQuery.getTargetName());
//                        expressions.add(root.get("dbGoods").get("cargoCode").as(String.class).in(cargoCodeList));
//                    }
//                    break;
//                } else if (BasicEnum.BY_MATERIAL.equals(basicEnum)) {
//                    if (!StringUtils.isEmpty(conditionQuery.getTargetCode())) {
//                        expressions.add(criteriaBuilder.equal(root.get("dbGoods").get("rawMaterialCode").as(String.class), conditionQuery.getTargetCode()));
//                    }
//                    if (!StringUtils.isEmpty(conditionQuery.getTargetName())) {
//                        List<String> rawMaterialCodeList = rawMaterialRepository.findRawMaterialCodesByRawMaterialName(conditionQuery.getTargetName());
//                        expressions.add(root.get("dbGoods").get("rawMaterialCode").as(String.class).in(rawMaterialCodeList));
//                    }
//                    break;
//                }
//            }
//        }
//
//        /**
//         * 出库库位条件
//         */
//        if (!StringUtils.isEmpty(conditionQuery.getOutStorageCodeSet())) {
//            expressions.add(criteriaBuilder.equal(root.get("dbStation").get("outStorageCode").as(String.class), conditionQuery.getOutStorageCodeSet()));
//        }
//        /**
//         * 入库库位条件
//         */
//        if (!StringUtils.isEmpty(conditionQuery.getInStorageCodeSet())) {
//            expressions.add(criteriaBuilder.equal(root.get("dbStation").get("inStorageCode").as(String.class), conditionQuery.getInStorageCodeSet()));
//        }
//    }


    @Override
    protected Page<MistakeBill> pageCondition(ConditionQueryMistakeBill conditionQuery, Pageable pageable) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuilder sql = new StringBuilder("select")
                .append(" b.bill_id, b.create_time, b.basic_enum, b.belong_station_code, b.bill_code, b.bill_purpose, b.bill_state,b.operator_code, ")
                .append(" b.bill_type ,b.specific_bill_type,b.in_station_code, b.in_storage_code, b.out_station_code, b.out_storage_code, b.supplier_code ,")
                .append(" d.bill_detail_id, d.actual_amount, d.cargo_code, d.raw_material_code,d.shipped_amount, d.bill_code ")
                .append(" from mistake_bill b left join mistake_bill_detail d on b.bill_code = d.bill_code where ");

        //追加误差单类型的条件
        sql.append("  b.specific_bill_type  = '").append(BillTypeEnum.NO_PLAN.name()).append("'");
        //追加作用
        sql.append(" and  b.bill_purpose  = '").append(conditionQuery.getPurposeEnum().name()).append("'");


        //开始时间
        if (!StringUtils.isEmpty(conditionQuery.getCreateStartTime())) {
            sql.append(" and b.create_time >= '").append(dateFormat.format(conditionQuery.getCreateStartTime())).append("' ");
        }

        //结束时间
        if (!StringUtils.isEmpty(conditionQuery.getCreateEndTime())) {
            sql.append(" and b.create_time <= '").append(dateFormat.format(conditionQuery.getCreateEndTime())).append("' ");
        }


        //目标匹配 -都匹配
        if (conditionQuery.obtainTarget(conditionQuery.getTargetEnumSet())) {
            if (!StringUtils.isEmpty(conditionQuery.getTargetCode())) {
                sql.append(" and ( d.cargo_code = '").append(conditionQuery.getTargetCode())
                        .append("' or d.raw_material_code = '").append(conditionQuery.getTargetCode())
                        .append(" ') ");
            }
            if (!StringUtils.isEmpty(conditionQuery.getTargetName())) {
                List<String> cargoCodeList = cargoRepository.findCargoCodesByCargoName(conditionQuery.getTargetName());
                List<String> rawMaterialCodeList = rawMaterialRepository.findRawMaterialCodesByRawMaterialName(conditionQuery.getTargetName());
                String cargoCodeStr = null;
                for (String s : cargoCodeList) {
                    cargoCodeStr = StringUtils.isEmpty(cargoCodeStr) ? "'" + s + "'" : cargoCodeStr + ",'" + s + "'";
                }
                String rawMaterialCodeStr = null;
                for (String s : rawMaterialCodeList) {
                    rawMaterialCodeStr = StringUtils.isEmpty(rawMaterialCodeStr) ? "'" + s + "'" : rawMaterialCodeStr + ",'" + s + "'";
                }
                sql.append(" and ( d.cargo_code in ( ").append(cargoCodeStr)
                        .append(" ) or d.raw_material_code in ( ").append(rawMaterialCodeStr).append(" )) ");
            }
        } else {
            //匹配其中一个
            for (BasicEnum basicEnum : conditionQuery.getTargetEnumSet()) {
                sql.append(" and b.basic_enum = '").append(basicEnum.name()).append("'");
                if (BasicEnum.BY_CARGO.equals(basicEnum)) {
                    if (!StringUtils.isEmpty(conditionQuery.getTargetCode())) {
                        sql.append(" and d.cargo_code = '").append(conditionQuery.getTargetCode()).append("'");
                    }
                    if (!StringUtils.isEmpty(conditionQuery.getTargetName())) {
                        List<String> cargoCodeList = cargoRepository.findCargoCodesByCargoName(conditionQuery.getTargetName());
                        String str = null;
                        for (String s : cargoCodeList) {
                            str = StringUtils.isEmpty(str) ? "'" + s + "'" : str + ",'" + s + "'";
                        }
                        sql.append(" and  d.cargo_code in ( ").append(str).append(" ) ");
                    }
                    break;
                } else if (BasicEnum.BY_MATERIAL.equals(basicEnum)) {
                    if (!StringUtils.isEmpty(conditionQuery.getTargetCode())) {
                        sql.append(" and  d.raw_material_code = '").append(conditionQuery.getTargetCode()).append("'");
                    }
                    if (!StringUtils.isEmpty(conditionQuery.getTargetName())) {
                        List<String> rawMaterialCodeList = rawMaterialRepository.findRawMaterialCodesByRawMaterialName(conditionQuery.getTargetName());
                        String str = null;
                        for (String s : rawMaterialCodeList) {
                            str = StringUtils.isEmpty(str) ? "'" + s + "'" : str + ",'" + s + "'";
                        }
                        sql.append(" and  d.raw_material_code in ( ").append(str).append(" ) ");
                    }
                    break;
                }
            }
        }

        //入站点
        if (!StringUtils.isEmpty(conditionQuery.getInStationCodes()) && conditionQuery.getInStationCodes().size()>0) {
            String str = null;
            for (String s : conditionQuery.getInStationCodes()) {
                str = StringUtils.isEmpty(str) ? "'" + s + "'" : str + ",'" + s + "'";
            }
            sql.append(" and b.in_station_code in (").append(str).append(" ) ");
        }
        //出站点
        if (!StringUtils.isEmpty(conditionQuery.getOutStationCodes()) &&conditionQuery.getOutStationCodes().size()>0) {
            String str = null;
            for (String s : conditionQuery.getOutStationCodes()) {
                str = StringUtils.isEmpty(str) ? "'" + s + "'" : str + ",'" + s + "'";
            }
            sql.append(" and b.out_station_code in (").append(str).append(" ) ");
        }

        //出库库位条件
        if (!StringUtils.isEmpty(conditionQuery.getOutStorageCodeSet()) && conditionQuery.getOutStorageCodeSet().size()>0) {
            String str = null;
            for (String s : conditionQuery.getOutStorageCodeSet()) {
                str = StringUtils.isEmpty(str) ? "'" + s + "'" : str + ",'" + s + "'";
            }
            sql.append(" and b.out_storage_code in ( ").append(str).append(" ) ");
        }
        //入库库位条件
        if (!StringUtils.isEmpty(conditionQuery.getInStorageCodeSet()) && conditionQuery.getInStorageCodeSet().size()>0) {
            String str = null;
            for (String s : conditionQuery.getInStorageCodeSet()) {
                str = StringUtils.isEmpty(str) ? "'" + s + "'" : str + ",'" + s + "'";
            }
            sql.append(" and b.in_storage_code in ( ").append(str).append(" ) ");
        }
        if (!StringUtils.isEmpty(conditionQuery.getOperatorCodeList()) && conditionQuery.getOperatorCodeList().size()>0) {
            String str = null;
            for (String s : conditionQuery.getOperatorCodeList()) {
                str = StringUtils.isEmpty(str) ? "'" + s + "'" : str + ",'" + s + "'";
            }
            sql.append(" and b.operator_code in (").append(str).append(" ) ");
        }

        sql.append(" group by b.bill_id");
        String withoutPage = sql.toString();
        System.out.println("条件筛选查询的sql语句：" + sql);
        List<MistakeBill> planBills = jdbcTemplate.query(withPage(sql, conditionQuery), getRowMapper());

        long total = jdbcTemplate.query(withoutPage, getRowMapper()).size();
        return new PageImpl<>(planBills, pageable, total);

    }

    private RowMapper<MistakeBill> getRowMapper() {
        return (resultSet, i) -> {
            MistakeBill mistakeBill = new MistakeBill();
            //数据ID
            mistakeBill.setBillId(resultSet.getLong("b.bill_id"));
            //单据编号
            mistakeBill.setBillCode(resultSet.getString("b.bill_code"));
            //生成时间
            mistakeBill.setCreateTime(resultSet.getDate("b.create_time"));
            //货物或原料
            if (!StringUtils.isEmpty(resultSet.getString("b.basic_enum"))) {
                mistakeBill.setBasicEnum(BasicEnum.valueOf(resultSet.getString("b.basic_enum")));
            }
            //误差类型
            if (!StringUtils.isEmpty(resultSet.getString("b.specific_bill_type"))) {
                mistakeBill.setSpecificBillType(BillTypeEnum.valueOf(resultSet.getString("b.specific_bill_type")));
            }
            //操作人名称
            mistakeBill.setOperatorCode(resultSet.getString("b.operator_code"));
            //位置信息
            DbStation station = new DbStation();

            Station outStation = new Station();
            Storage outStorage = new Storage();
            outStorage.setStorageCode(resultSet.getString("b.out_storage_code"));
            outStation.setStorage(outStorage);
            outStation.setStationCode(resultSet.getString("b.out_station_code"));
            station.setOutLocation(outStation);


            Station inStation = new Station();
            Storage inStorage = new Storage();
            inStorage.setStorageCode(resultSet.getString("b.in_storage_code"));
            inStation.setStorage(inStorage);
            inStation.setStationCode(resultSet.getString("b.in_station_code"));
            station.setInLocation(inStation);

            mistakeBill.setDbStation(station);
            return mistakeBill;
        };
    }

    private String withPage(StringBuilder sql, ConditionQueryMistakeBill conditionQuery) {
        sql.append(" limit ");
        sql.append(conditionQuery.getPage() * conditionQuery.getPageSize() - conditionQuery.getPageSize());
        sql.append(",");
        sql.append(conditionQuery.getPageSize());
        return sql.toString();
    }
}
