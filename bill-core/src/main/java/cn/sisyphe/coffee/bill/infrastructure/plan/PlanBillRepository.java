package cn.sisyphe.coffee.bill.infrastructure.plan;

import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.plan.ConditionQueryPlanBill;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ncmao
 * @Date 2017/12/25 17:08
 * @description
 */
public interface PlanBillRepository extends BillRepository<PlanBill> {

    PlanBill findByBillCode(String billCode);

    void save(List<PlanBill> planBills);

    Page<PlanBill> findByCondition(ConditionQueryPlanBill conditionQueryPlanBill);

}
