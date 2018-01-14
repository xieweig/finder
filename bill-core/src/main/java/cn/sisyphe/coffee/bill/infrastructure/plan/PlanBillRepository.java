package cn.sisyphe.coffee.bill.infrastructure.plan;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;

/**
 * @author ncmao
 * @Date 2017/12/25 17:08
 * @description
 */
public interface PlanBillRepository extends BillRepository<PlanBill> {

    PlanBill findByBillCodeAndType(String billCode, BillTypeEnum billType);
}
