package cn.sisyphe.coffee.bill.infrastructure.plan;

import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.plan.jpa.JPAPlanBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ncmao
 * @Date 2017/12/25 17:09
 * @description
 */
@Repository
public class PlanBillRepositoryImpl extends AbstractBillRepository<PlanBill> implements PlanBillRepository {

    @Autowired
    private JPAPlanBillRepository jpaPlanBillRepository;

    @Override
    public void save(PlanBill bill) {
        jpaPlanBillRepository.save(bill);
    }

    @Override
    public PlanBill findOne(Long id) {
        return jpaPlanBillRepository.findOne(id);
    }

    @Override
    public PlanBill findByBillCode(String billCode) {
        return jpaPlanBillRepository.findByBillCode(billCode);
    }

    @Override
    public void save(List<PlanBill> planBills) {
        jpaPlanBillRepository.save(planBills);
    }

    @Override
    public Page<PlanBill> findAll(Specification<PlanBill> ta, Pageable pageable) {
        return jpaPlanBillRepository.findAll(ta, pageable);
    }
    /*@Override
    public Page<PlanBill> findByCondition(ConditionQueryPlanBill conditionQueryPlanBill) {
        // 组装页面
        Pageable pageable = new PageRequest(conditionQueryPlanBill.getPage() - 1, conditionQueryPlanBill.getPageSize());

        Page<PlanBill> planBillWithPage;
        planBillWithPage = queryByParams(conditionQueryPlanBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (planBillWithPage.getContent().size() < 1 && planBillWithPage.getTotalElements() > 0) {
            pageable = new PageRequest(planBillWithPage.getTotalPages() - 1, conditionQueryPlanBill.getPageSize());
            planBillWithPage = queryByParams(conditionQueryPlanBill, pageable);
        }
        return planBillWithPage;
    }

    private Page<PlanBill> queryByParams(ConditionQueryPlanBill conditionQueryPlanBill, Pageable pageable) {
        return jpaPlanBillRepository.findAll((root, criteriaQuery, cb) -> {
            criteriaQuery.distinct(true);
            Predicate predicate = cb.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getBillCode())) {
                expressions.add(cb.like(root.get("billCode").as(String.class), "%" + conditionQueryPlanBill.getBillCode() + "%"));
            }
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getBillName())) {
                expressions.add(cb.like(root.get("billName").as(String.class), "%" + conditionQueryPlanBill.getBillName() + "%"));
            }
            return predicate;
        }, pageable);
    }*/


}
