package cn.sisyphe.coffee.bill.domain.plan;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;

@Service
public class PlanBillQueryServiceImpl implements PlanBillQueryService{

    @Autowired
    private PlanBillRepository planBillRepository;

    @Override
    public Page<PlanBill> findPageByCondition(ConditionQueryPlanBill conditionQueryPlanBill) {

        // 组装页面
        Pageable pageable = new PageRequest(conditionQueryPlanBill.getPage() - 1, conditionQueryPlanBill.getPageSize());

        Page<PlanBill> planBillPage;
        planBillPage = queryByParams(conditionQueryPlanBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (planBillPage.getContent().size() < 1 && planBillPage.getTotalElements() > 0) {
            pageable = new PageRequest(planBillPage.getTotalPages() - 1, conditionQueryPlanBill.getPageSize());
            planBillPage = queryByParams(conditionQueryPlanBill, pageable);
        }
        return planBillPage;
    }

    @Override
    public PlanBill findByBillCode(String planBillCode) {
        if (StringUtils.isEmpty(planBillCode)) {
            throw new DataException("20011", "进货单编码为空");
        }
        PlanBill planBill = planBillRepository.findOneByBillCode(planBillCode);
        if (planBill != null) {
            return planBill;
        } else {
            throw new DataException("20012", "根据该进货单编码没有查询到具体的进货单信息");
        }
    }

    /**
     * @param conditionQueryPlanBill
     * @param pageable
     * @return
     * @throws DataException
     */
    private Page<PlanBill> queryByParams(final ConditionQueryPlanBill conditionQueryPlanBill,
                                         Pageable pageable) throws DataException {
        return planBillRepository.findAll((root, criteriaQuery, cb) -> {
            //去重
            criteriaQuery.distinct(true);
            Predicate predicate = cb.conjunction();

            List<Expression<Boolean>> expressions = predicate.getExpressions();

            //站点计划号未实现
            /*if (!StringUtils.isEmpty(conditionQueryPlanBill.getBillCode())) {
                expressions.add(cb.like(root.<String>get("billCode"), "%" + conditionQueryPlanBill.getBillCode() + "%"));
            }*/

            //单据的种类
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getSpecificBillType())){
                expressions.add(cb.equal(root.get("specificBillType").as(BillStateEnum.class),
                        conditionQueryPlanBill.getSpecificBillType()));
            }

            // 入库站点
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getInStationCode())) {
                expressions.add(cb.equal(root.<String>get("inStationCode"),
                        conditionQueryPlanBill.getInStationCode()));
            }
            //出库站点
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getOutStationCode())) {
                expressions.add(cb.equal(root.<String>get("outStationCode"),
                        conditionQueryPlanBill.getOutStationCode() ));
            }

            //录单人
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getCreatorName())) {
                expressions.add(cb.like(root.<String>get("creatorName"),
                        "%" + conditionQueryPlanBill.getCreatorName() + "%"));
            }

            /**
             * 录单开始时间
             */
            if(!StringUtils.isEmpty(conditionQueryPlanBill.getCreateStartTime())){
                expressions.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryPlanBill.getCreateStartTime()));
            }
            /**
             * 录单结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getCreateEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryPlanBill.getCreateEndTime()));
            }

//            SetJoin<PlanBill, Long> planBillLongSetJoin = root.join(root.getModel().getSet(""));
           /* //分组查询
            query.groupBy(root.get("billCode"));
            //*/
            return predicate;
        }, pageable);
    }
}
