package cn.sisyphe.coffee.bill.infrastructure.plan;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.plan.jpa.JpaPlanBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author ncmao
 * @Date 2017/12/25 17:09
 * @description
 */
@Repository
public class PlanBillRepositoryImpl extends AbstractBillRepository<PlanBill> implements PlanBillRepository {

    @Autowired
    public PlanBillRepositoryImpl(JpaBillRepository<PlanBill> jpaBillRepository) {
        super(jpaBillRepository);
    }

    @Override
    public PlanBill findByBillCodeAndSpecificBillType(String billCode, BillTypeEnum billType) {
        return ((JpaPlanBillRepository) getJpaBillRepository()).findByBillCodeAndSpecificBillType(billCode, billType);
    }
}
