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
    public PlanBill findOneByBillCode(String billCode) {
        return jpaPlanBillRepository.findByBillCode(billCode);
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
}
