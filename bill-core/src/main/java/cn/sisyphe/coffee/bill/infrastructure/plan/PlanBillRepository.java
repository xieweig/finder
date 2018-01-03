package cn.sisyphe.coffee.bill.infrastructure.plan;

import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author ncmao
 * @Date 2017/12/25 17:08
 * @description
 */
public interface PlanBillRepository extends BillRepository<PlanBill> {

    PlanBill findByBillCode(String billCode);


    /**
     * 根据条件返回信息
     * @param ta
     * @return
     */
    Page<PlanBill> findAll(Specification<PlanBill> ta, Pageable pageable);
}
