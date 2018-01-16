package cn.sisyphe.coffee.bill.domain.base;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public abstract class AbstractBillExtraService<T extends Bill, Q extends ConditionQueryBill> implements BillExtraService<T, Q> {

    /**
     * 持久化
     */
    private BillRepository<T> billRepository;

    public AbstractBillExtraService(BillRepository<T> billRepository) {
        this.billRepository = billRepository;
    }

    public BillRepository<T> getBillRepository() {
        return billRepository;
    }

    public void setBillRepository(BillRepository<T> billRepository) {
        this.billRepository = billRepository;
    }

    /**
     * 根据单据编码查询单据信息
     *
     * @param billCode 单据编码
     * @return
     */
    @Override
    public T findByBillCode(String billCode) {
        return billRepository.findOneByBillCode(billCode);
    }

    /**
     * 根据sourceCode查询调剂单据
     *
     * @param sourceCode
     * @return
     */
    @Override
    public T findBySourceCode(String sourceCode) {
        return billRepository.findOneBySourceCode(sourceCode);
    }

    /**
     * 根据发起单号发查
     *
     * @param rootCode
     * @return
     */
    @Override
    public T findByRootCode(String rootCode) {
        return billRepository.findOneByRootCode(rootCode);
    }

    /**
     * 多条件查询
     *
     * @param conditionQueryBill
     * @return
     */
    @Override
    public Page<T> findPageByCondition(Q conditionQueryBill) {
        Pageable pageable = new PageRequest(conditionQueryBill.getPage() - 1, conditionQueryBill.getPageSize());
        Page<T> configurePage = pageCondition(conditionQueryBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (configurePage.getContent().size() < 1 && configurePage.getTotalElements() > 0) {
            pageable = new PageRequest(configurePage.getTotalPages() - 1, conditionQueryBill.getPageSize());
            configurePage = pageCondition(conditionQueryBill, pageable);
        }
        return configurePage;
    }

    protected void addExtraExpression(ConditionQueryBill conditionQuery, List<Expression<Boolean>> expressions, Root<T> root, CriteriaBuilder criteriaBuilder) {
    }

    /**
     * 条件组装
     *
     * @param conditionQuery
     * @param pageable
     * @return
     */
    private Page<T> pageCondition(Q conditionQuery, Pageable pageable) {
        return billRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.distinct(true);
            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            /*
             * 增加操作人条件查询
             */
            if (conditionQuery.getOperatorCodeList() != null && conditionQuery.getOperatorCodeList().size() > 0) {
                expressions.add(root.get("operatorCode").as(String.class).in(conditionQuery.getOperatorCodeList()));
            }

            /*
             * 单号查询条件
             */
            if (!StringUtils.isEmpty(conditionQuery.getBillCode())) {
                expressions.add(criteriaBuilder.like(root.get("billCode").as(String.class), "%" + conditionQuery.getBillCode() + "%"));
            }
            /*
             * 入库站点条件
             */
            if (conditionQuery.getInStationCodes() != null && conditionQuery.getInStationCodes().size() > 0) {
                expressions.add(root.get("dbStation").get("inStationCode").as(String.class).in(conditionQuery.getInStationCodes()));
            }

            /*
             * 出库站点查询条件
             */
            if (conditionQuery.getOutStationCodes() != null && conditionQuery.getOutStationCodes().size() > 0) {
                expressions.add(root.get("dbStation").get("outStationCode").as(String.class).in(conditionQuery.getOutStationCodes()));
            }
            /*
             * 录单开始时间
             */
            if (!StringUtils.isEmpty(conditionQuery.getCreateStartTime())) {
                expressions.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), conditionQuery.getCreateStartTime()));
            }
            /*
             * 录单结束时间
             */
            if (!StringUtils.isEmpty(conditionQuery.getCreateEndTime())) {
                expressions.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), conditionQuery.getCreateEndTime()));
            }
            /*
             * 出库开始时间
             */
            if (!StringUtils.isEmpty(conditionQuery.getOutStorageStartTime())) {
                expressions.add(criteriaBuilder.greaterThanOrEqualTo(root.get("outWareHouseTime").as(Date.class), conditionQuery.getOutStorageStartTime()));
            }
            /*
             * 出库结束时间
             */
            if (!StringUtils.isEmpty(conditionQuery.getOutStorageEndTime())) {
                expressions.add(criteriaBuilder.lessThanOrEqualTo(root.get("outWareHouseTime").as(Date.class), conditionQuery.getOutStorageEndTime()));
            }

            /*
             * 入库开始时间
             */
            if (!StringUtils.isEmpty(conditionQuery.getInStorageStartTime())) {
                expressions.add(criteriaBuilder.greaterThanOrEqualTo(root.get("inWareHouseTime").as(Date.class), conditionQuery.getInStorageStartTime()));
            }
            /*
             * 入库结束时间
             */
            if (!StringUtils.isEmpty(conditionQuery.getInStorageEndTime())) {
                expressions.add(criteriaBuilder.lessThanOrEqualTo(root.get("inWareHouseTime").as(Date.class), conditionQuery.getInStorageEndTime()));
            }

            /*
             * 提交状态
             */
            if (conditionQuery.getSubmitStates() != null && conditionQuery.getSubmitStates().size() > 0) {
                expressions.add(root.get("submitState").as(BillSubmitStateEnum.class).in(conditionQuery.getSubmitStates()));
            }

            /*
             * 审核状态
             */
            if (conditionQuery.getAuditStates() != null && conditionQuery.getAuditStates().size() > 0) {
                expressions.add(root.get("auditState").as(BillAuditStateEnum.class).in(conditionQuery.getAuditStates()));
            }
            /*
             * 出入库状态
             */
            if (conditionQuery.getInOrOutStates() != null && conditionQuery.getInOrOutStates().size() > 0) {
                expressions.add(root.get("inOrOutState").as(BillInOrOutStateEnum.class).in(conditionQuery.getInOrOutStates()));
            }
            /*
             * 单据属性
             */
            if (conditionQuery.getBillTypes() != null && conditionQuery.getBillTypes().size() > 0) {
                expressions.add(root.get("billProperty").as(SourcePlanTypeEnum.class).in(conditionQuery.getBillTypes()));
            }
            /*
             * 配送品种开始数量
             */
            if (conditionQuery.getVarietyStart() != null && conditionQuery.getVarietyStart() > 0) {
                expressions.add(criteriaBuilder.greaterThanOrEqualTo(root.get("totalVarietyAmount").as(Integer.class), conditionQuery.getVarietyStart()));
            }
            /*
             * 配送品种结束数量
             */
            if (conditionQuery.getVarietyEnd() != null && conditionQuery.getVarietyEnd() > 0) {
                expressions.add(criteriaBuilder.lessThanOrEqualTo(root.get("totalVarietyAmount").as(Integer.class), conditionQuery.getVarietyStart()));
            }

            addExtraExpression(conditionQuery, expressions, root, criteriaBuilder);
            return predicate;
        }, pageable);
    }
}
