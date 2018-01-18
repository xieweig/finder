package cn.sisyphe.coffee.bill.infrastructure.plan.jpa;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;

/**
 * @author ncmao
 * @Date 2017/12/25 17:09
 * @description
 */
public interface JpaPlanBillRepository extends JpaBillRepository<PlanBill> {
    PlanBill findByBillCodeAndSpecificBillType(String billCode, BillTypeEnum billType);
}
