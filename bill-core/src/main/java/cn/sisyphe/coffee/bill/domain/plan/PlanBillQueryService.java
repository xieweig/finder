package cn.sisyphe.coffee.bill.domain.plan;

import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import org.springframework.data.domain.Page;

public interface PlanBillQueryService {
    /**
     * 多条件查询计划单数据
     *
     * @param conditionQueryPlanBill 查询条件
     * @return
     */
    Page<PlanBill> findPageByCondition(ConditionQueryPlanBill conditionQueryPlanBill);

    /**
     * 根据计划单号查询
     *
     * @param planBillCode 查询条件
     * @return
     */
    PlanBill findByBillCode(String planBillCode);
}
