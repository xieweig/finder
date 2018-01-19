package cn.sisyphe.coffee.bill.domain.plan;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.enums.OperationStateEnum;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
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

@Service
public class PlanBillExtraServiceImpl extends AbstractBillExtraService<PlanBill, ConditionQueryPlanBill> implements PlanBillExtraService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PlanBillExtraServiceImpl(BillRepository<PlanBill> billRepository) {
        super(billRepository);
    }

    @Override
    public void updateOperationStateByBill(PlanBill planBill, OperationStateEnum operationState) {
        planBill.setOperationState(operationState);
        getBillRepository().save(planBill);
    }

    @Override
    public Page<PlanBill> findChildPlanBillBy(ConditionQueryPlanBill conditionQueryPlanBill) {
        return findPageByCondition(conditionQueryPlanBill);
    }

    @Override
    public PlanBill findByBillCodeAndSpecificBillType(String billCode, BillTypeEnum billType) {
        return ((PlanBillRepository) getBillRepository()).findByBillCodeAndSpecificBillType(billCode, billType);
    }

    @Override
    protected void addExtraExpression(ConditionQueryPlanBill conditionQuery, List<Expression<Boolean>> expressions, Root<PlanBill> root, CriteriaBuilder criteriaBuilder) {
        if (!conditionQuery.getHqBill()) {
            expressions.add(criteriaBuilder.equal(root.get("hqBill").as(Boolean.class), false));
        }
    }

    @Override
    protected Page<PlanBill> pageCondition(ConditionQueryPlanBill conditionQuery, Pageable pageable) {
        if (!conditionQuery.getHqBill()) {
            return super.pageCondition(conditionQuery, pageable);
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        StringBuilder sql = new StringBuilder("select p.bill_id, p.create_time, p.update_time, p.version, p.allot_status, p.audit_memo, p.audit_person_code," +
                "p.audit_state, p.basic_enum, p.belong_station_code, p.bill_code, p.bill_purpose, p.bill_state, p.bill_type, p.in_or_out_state, p.in_ware_house_time, p.operator_code," +
                " p.in_or_out_state, p.out_storage_memo, p.out_ware_house_time, p.plan_memo, p.root_code, p.source_code, p.submit_state, p.total_amount, p.total_variety_amount, p.bill_name, p.hq_bill," +
                " p.operation_state, p.specific_bill_type, d.bill_detail_id, d.actual_amount, d.cargo_code, d.raw_material_code, d.package_code, d.shipped_amount, d.in_station_code, d.in_storage_code, d.out_station_code, d.out_storage_code, d.supplier_code, d.bill_code").append(" from plan_bill p left join plan_bill_detail d on p.bill_code = d.bill_code where p.hq_bill=1 ");
        if (conditionQuery.getSpecificBillType() != null) {
            sql.append(" and p.specific_bill_type = '").append(conditionQuery.getSpecificBillType().get(0).name()).append("'");
        }
        if (conditionQuery.getCargoCodes() != null && conditionQuery.getCargoCodes().size() > 0) {

            StringBuilder cargoSql = new StringBuilder();

            for (int i = 0; i < conditionQuery.getCargoCodes().size(); i++) {
                cargoSql.append("'").append(conditionQuery.getCargoCodes().get(i)).append("'");
                if (i != conditionQuery.getCargoCodes().size() - 1) {
                    cargoSql.append(",");
                }
            }

            sql.append(" and d.cargo_code in (").append(cargoSql.toString()).append(")");
        }
        if (!StringUtils.isEmpty(conditionQuery.getCargoCode())) {
            sql.append("and d.cargo_code like '").append("%").append(conditionQuery.getCargoCode()).append("%'");
        }

        /*
         * 单号查询条件
         */
        if (!StringUtils.isEmpty(conditionQuery.getBillCode())) {
            sql.append("and p.bill_code like '").append("%").append(conditionQuery.getBillCode()).append("%'");
        }

        /*
         * 计划名称查询
         */
        if (!StringUtils.isEmpty(conditionQuery.getBillName())) {
            sql.append("and p.bill_name like '").append("%").append(conditionQuery.getBillName()).append("%'");

        }

        if (conditionQuery.getInStationCodes() != null && conditionQuery.getInStationCodes().size() > 0) {

            StringBuilder inStationCodesSql = new StringBuilder();

            for (int i = 0; i < conditionQuery.getInStationCodes().size(); i++) {
                inStationCodesSql.append("'").append(conditionQuery.getInStationCodes().get(i)).append("'");
                if (i != conditionQuery.getInStationCodes().size() - 1) {
                    inStationCodesSql.append(",");
                }
            }

            sql.append(" and d.in_station_code in (").append(inStationCodesSql.toString()).append(")");
        }

        if (conditionQuery.getOutStationCodes() != null && conditionQuery.getOutStationCodes().size() > 0) {

            StringBuilder outStationCodesSql = new StringBuilder();

            for (int i = 0; i < conditionQuery.getOutStationCodes().size(); i++) {
                outStationCodesSql.append("'").append(conditionQuery.getOutStationCodes().get(i)).append("'");
                if (i != conditionQuery.getOutStationCodes().size() - 1) {
                    outStationCodesSql.append(",");
                }
            }

            sql.append(" and d.out_station_code in (").append(outStationCodesSql.toString()).append(")");
        }

        if (conditionQuery.getOperatorCodeList() != null && conditionQuery.getOperatorCodeList().size() > 0) {

            StringBuilder operatorCodesSql = new StringBuilder();

            for (int i = 0; i < conditionQuery.getOperatorCodeList().size(); i++) {
                operatorCodesSql.append("'").append(conditionQuery.getOperatorCodeList().get(i)).append("'");
                if (i != conditionQuery.getOperatorCodeList().size() - 1) {
                    operatorCodesSql.append(",");
                }
            }

            sql.append(" and p.operator_code in (").append(operatorCodesSql.toString()).append(")");
        }

        /*
         * 录单开始时间
         */
        if (!StringUtils.isEmpty(conditionQuery.getCreateStartTime())) {
            sql.append("and p.create_time >= '").append(dateFormat.format(conditionQuery.getCreateStartTime())).append("'");
        }

        /*
         * 录单结束时间
         */
        if (!StringUtils.isEmpty(conditionQuery.getCreateEndTime())) {
            sql.append("and p.create_time <= '").append(dateFormat.format(conditionQuery.getCreateEndTime())).append("'");

        }


        /*
         * 提交状态
         */
        if (conditionQuery.getSubmitStates() != null && conditionQuery.getSubmitStates().size() > 0) {
            StringBuilder submitStatesSql = new StringBuilder();

            for (int i = 0; i < conditionQuery.getSubmitStates().size(); i++) {
                submitStatesSql.append("'").append(conditionQuery.getSubmitStates().get(i).name()).append("'");
                if (i != conditionQuery.getSubmitStates().size() - 1) {
                    submitStatesSql.append(",");
                }
            }

            sql.append(" and p.submit_state in (").append(submitStatesSql.toString()).append(")");
        }


        /*
         * 审核状态
         */
        if (conditionQuery.getAuditStates() != null && conditionQuery.getAuditStates().size() > 0) {
            StringBuilder auditStatesSql = new StringBuilder();

            for (int i = 0; i < conditionQuery.getAuditStates().size(); i++) {
                auditStatesSql.append("'").append(conditionQuery.getAuditStates().get(i).name()).append("'");
                if (i != conditionQuery.getAuditStates().size() - 1) {
                    auditStatesSql.append(",");
                }
            }

            sql.append(" and p.audit_state in (").append(auditStatesSql.toString()).append(")");
        }
        sql.append(" group by p.bill_id");
        String withoutPage = sql.toString();
        System.out.println(sql);
        List<PlanBill> planBills = jdbcTemplate.query(withPage(sql, conditionQuery), getRowMapper());

        long total = jdbcTemplate.query(withoutPage, getRowMapper()).size();
        return new PageImpl<>(planBills, pageable, total);

    }

    private RowMapper<PlanBill> getRowMapper() {
        return (resultSet, i) -> {
            PlanBill planBill = new PlanBill();
            planBill.setBillId(resultSet.getLong("p.bill_id"));
            planBill.setCreateTime(resultSet.getDate("p.create_time"));
            planBill.setUpdateTime(resultSet.getDate("p.update_time"));
            planBill.setBillName(resultSet.getString("p.bill_name"));
            planBill.setBillCode(resultSet.getString("p.bill_code"));
            planBill.setOperatorCode(resultSet.getString("p.operator_code"));
            planBill.setAuditPersonCode(resultSet.getString("p.audit_person_code"));
            if (resultSet.getString("p.specific_bill_type") != null) {
                planBill.setSpecificBillType(BillTypeEnum.valueOf(resultSet.getString("p.specific_bill_type")));
            }
            if (resultSet.getString("p.bill_state") != null) {
                planBill.setBillState(BillStateEnum.valueOf(resultSet.getString("p.bill_state")));
            }
            planBill.setBasicEnum(BasicEnum.valueOf(resultSet.getString("p.basic_enum")));
            planBill.setSubmitState(BillSubmitStateEnum.valueOf(resultSet.getString("p.submit_state")));
            planBill.setAuditState(BillAuditStateEnum.valueOf(resultSet.getString("p.audit_state")));
            planBill.setAuditMemo(resultSet.getString("p.audit_memo"));
            planBill.setPlanMemo(resultSet.getString("p.plan_memo"));
            return planBill;
        };
    }

    private String withPage(StringBuilder sql, ConditionQueryPlanBill conditionQuery) {
        sql.append(" limit ");
        sql.append(conditionQuery.getPage() * conditionQuery.getPageSize() - conditionQuery.getPageSize());
        sql.append(",");
        sql.append(conditionQuery.getPageSize());
        return sql.toString();
    }
}
