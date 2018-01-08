package cn.sisyphe.coffee.bill.infrastructure.plan;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.plan.jpa.JPAPlanBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

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
    public PlanBill findOneByBillCode(String billCode) {
        return jpaPlanBillRepository.findByBillCode(billCode);
    }

    @Override
    public Page<PlanBill> findAll(Specification<PlanBill> ta, Pageable pageable) {
        return jpaPlanBillRepository.findAll(ta, pageable);
    }

    @Override
    public PlanBill findByBillCodeAndType(String billCode, BillTypeEnum billType) {
        return jpaPlanBillRepository.findByBillCodeAndSpecificBillType(billCode, billType);
    }
}
