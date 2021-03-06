package cn.sisyphe.coffee.bill.domain.transmit;

import cn.sisyphe.coffee.bill.domain.transmit.enums.ReceivedStatusEnum;
import cn.sisyphe.coffee.bill.infrastructure.transmit.WayBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.waybill.ConditionQueryWayBill;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;

/**
 * 运货单
 * Created by Administrator on 2017/12/28.
 */
@Service
public class WayBillServiceImpl implements WayBillService {


    @Autowired
    private WayBillRepository wayBillRepository;


    /**
     * @param billCode
     * @return
     */
    @Override
    public WayBill findOneBillByCode(String billCode) {
        return wayBillRepository.findOneByCode(billCode);
    }

    /**
     * 分页查询运单
     *
     * @param conditionQueryWayBill
     * @return
     * @throws DataException
     */
    @Override
    public Page<WayBill> findPageByCondition(ConditionQueryWayBill conditionQueryWayBill) throws DataException {


        Pageable pageable = new PageRequest(conditionQueryWayBill.getPage() - 1, conditionQueryWayBill.getPageSize());
        Page<WayBill> configurePage = pageCondition(conditionQueryWayBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (configurePage.getContent().size() < 1 && configurePage.getTotalElements() > 0) {
            pageable = new PageRequest(configurePage.getTotalPages() - 1, conditionQueryWayBill.getPageSize());

            configurePage = pageCondition(conditionQueryWayBill, pageable);
        }
        return configurePage;
    }


    /**
     * @param conditionQueryWayBill
     * @param pageable
     * @return
     * @throws DataException
     */
    private Page<WayBill> pageCondition(final ConditionQueryWayBill conditionQueryWayBill,
                                        Pageable pageable) throws DataException {
        return wayBillRepository.findAll((root, query, cb) -> {
            // 分组后去重复
            query.distinct(true);
            // left join
            Join<WayBill, WayBillDetail> itemJoin = root.join("wayBillDetailSet", JoinType.LEFT);
            Predicate predicate = cb.conjunction();

            List<Expression<Boolean>> expressions = predicate.getExpressions();
            //billCode
            if (!StringUtils.isEmpty(conditionQueryWayBill.getWayBillCode())) {
                expressions.add(cb.like(root.<String>get("billCode"),
                        "%" + conditionQueryWayBill.getWayBillCode() + "%"));
            }
            //出库单号
            if (!StringUtils.isEmpty(conditionQueryWayBill.getOutStorageBillCode())) {
                //必须加上这个multiSelect
                query.multiselect(root.get("billCode"));
                expressions.add(cb.like(itemJoin.<String>get("sourceCode"),
                        "%" + conditionQueryWayBill.getOutStorageBillCode() + "%"));
            }
            // 入库站点
            if (conditionQueryWayBill.getInStationCode() != null
                    && conditionQueryWayBill.getInStationCode().size() > 0) {
                expressions.add(root.<String>get("inStationCode").in(conditionQueryWayBill.getInStationCode()));
            }
            //出库站点
            if (conditionQueryWayBill.getOutStationCode() != null
                    && conditionQueryWayBill.getOutStationCode().size() > 0) {
                //outStationCode
                expressions.add(root.<String>get("outStationCode").in(conditionQueryWayBill.getOutStationCode()));
            }
            //物流公司名称
            if (!StringUtils.isEmpty(conditionQueryWayBill.getLogisticsCompanyName())) {
                expressions.add(cb.like(root.<String>get("logisticsCompanyName"),
                        "%" + conditionQueryWayBill.getLogisticsCompanyName() + "%"));
            }
            //操作人姓名
            if (!StringUtils.isEmpty(conditionQueryWayBill.getOperatorName())) {
                expressions.add(cb.like(root.<String>get("operatorName"),
                        "%" + conditionQueryWayBill.getOperatorName() + "%"));
            }
            //收货状态receivedStatus
            if (conditionQueryWayBill.getReceivedStatus() != null
                    && !StringUtils.isEmpty(conditionQueryWayBill.getReceivedStatus())) {

                expressions.add(cb.equal(root.get("receivedStatus").as(String.class),
                        conditionQueryWayBill.getReceivedStatus()));
            }
            /**
             * 录单开始时间
             */
            if (!StringUtils.isEmpty(conditionQueryWayBill.getCreateStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class),
                        conditionQueryWayBill.getCreateStartTime()));
            }
            /**
             * 录单结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryWayBill.getCreateEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class),
                        conditionQueryWayBill.getCreateEndTime()));
            }

            /**
             * 发货开始时间
             */
            if (!StringUtils.isEmpty(conditionQueryWayBill.getDeliveryStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("deliveryTime").as(Date.class),
                        conditionQueryWayBill.getCreateStartTime()));
            }

            /**
             *  发货结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryWayBill.getDeliveryEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("deliveryTime").as(Date.class),
                        conditionQueryWayBill.getCreateEndTime()));
            }

            //运货件数
            if (conditionQueryWayBill.getAmountOfPackages() != null) {
                expressions.add(cb.equal(root.<String>get("amountOfPackages"),
                        +conditionQueryWayBill.getAmountOfPackages()));
            }
            //query.groupBy(root.get("billCode"));
            return predicate;
        }, pageable);


    }


    /**
     * 收货确定
     *
     * @param billCode
     */
    @Override
    public void confirmReceiptBill(String billCode) {

        WayBill wayBill = wayBillRepository.findOneByCode(billCode);
        if (wayBill == null) {
            throw new DataException("50001", "单据不存在不能确认收货");
        }
        //
        if (wayBill.getReceivedStatus().equals(ReceivedStatusEnum.IS_RECEIVED)) {
            throw new DataException("50002", "已经确定收货了");
        }
        wayBill.setReceivedStatus(ReceivedStatusEnum.IS_RECEIVED);
        //
        wayBillRepository.save(wayBill);


    }

    /**
     * 创建运单
     *
     * @param wayBill
     * @return
     */
    @Override
    public WayBill createBill(WayBill wayBill) {
        // 运货件数
        int totalPackAgeAmount = wayBill.getAmountOfPackages();
        wayBill.setAmountOfPackages(totalPackAgeAmount);

        return wayBillRepository.createBill(wayBill);
    }

    /**
     * 修改运单
     *
     * @param wayBill
     * @return
     */
    @Override
    public WayBill updateBill(WayBill wayBill) {

        //1先查询一条数据库里的内容
        WayBill wayBillDB = wayBillRepository.findOneByCode(wayBill.getBillCode());
        //清除
        wayBillDB.getWayBillDetailSet().clear();

        // 入库站点code
        if (!StringUtils.isEmpty(wayBill.getInStationCode())) {
            wayBillDB.setInStationCode(wayBill.getInStationCode());
        }

        if (!StringUtils.isEmpty(wayBill.getInStationName())) {
            wayBillDB.setInStationName(wayBill.getInStationName());
        }
        //出库站点code
        if (!StringUtils.isEmpty(wayBill.getOutStationCode())) {
            wayBillDB.setOutStationCode(wayBill.getOutStationCode());
        }
        //
        if (!StringUtils.isEmpty(wayBill.getOutStationName())) {
            wayBillDB.setOutStationName(wayBill.getOutStationName());
        }
        //公司名称
        if (!StringUtils.isEmpty(wayBill.getLogisticsCompanyName())) {
            wayBillDB.setLogisticsCompanyName(wayBill.getLogisticsCompanyName());
        }
        //目的地
        if (!StringUtils.isEmpty(wayBill.getDestination())) {
            wayBillDB.setDestination(wayBill.getDestination());
        }
        //备注
        if (!StringUtils.isEmpty(wayBill.getMemo())) {
            wayBillDB.setMemo(wayBill.getMemo());
        }
        //到货时间
        if (!StringUtils.isEmpty(wayBill.getPlanArrivalTime())) {
            wayBillDB.setPlanArrivalTime(wayBill.getPlanArrivalTime());
        }
        //发货时间
        if (!StringUtils.isEmpty(wayBill.getDeliveryTime())) {
            wayBillDB.setDeliveryTime(wayBill.getDeliveryTime());

        }
        //总重量
        if (!StringUtils.isEmpty(wayBill.getTotalWeight())) {
            wayBillDB.setTotalWeight(wayBill.getTotalWeight());
        }
        //运货件数
        if (wayBill.getAmountOfPackages() != null) {
            wayBillDB.setAmountOfPackages(wayBill.getAmountOfPackages());
        }
        //
        if (wayBillDB.getReceivedStatus().equals(ReceivedStatusEnum.IS_RECEIVED)) {
            throw new DataException("50003", "已经确定了收货不能修改");
        }
        // 设置明细的方法
        wayBillDB.getWayBillDetailSet().addAll(wayBill.getWayBillDetailSet());//
        //
        wayBillDB = wayBillRepository.save(wayBillDB);
        return wayBillDB;
    }


    /**
     * 条件查询
     *
     * @param wayBill
     * @return
     */
    @Override
    public List<WayBill> findByConditions(WayBill wayBill) {

        return wayBillRepository.findAllByCondition((root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();

            //id
            if (wayBill.getBillId() != null) {
                //id
                expressions.add(criteriaBuilder.equal(root.get("billId").as(Long.class), wayBill.getBillId()));
            }
            // bill编码
            if (!StringUtils.isEmpty(wayBill.getBillCode())) {
                expressions.add(criteriaBuilder.like(root.get("billCode").as(String.class),
                        "%" + wayBill.getBillCode() + "%"));
            }
            //物流公司名称
            if (!StringUtils.isEmpty(wayBill.getLogisticsCompanyName())) {
                expressions.add(criteriaBuilder.like(root.get("logisticsCompanyName").as(String.class),
                        "%" + wayBill.getLogisticsCompanyName() + "%"));
            }
            //单据收货状态
            if (wayBill.getReceivedStatus() != null) {
                expressions.add(criteriaBuilder.equal(root.get("receivedStatus")
                        .as(Long.class), wayBill.getReceivedStatus()));
            }
            return predicate;

        });

    }

}
