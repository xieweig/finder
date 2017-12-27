package cn.sisyphe.bill.infrastructure.plan;

import cn.sisyphe.bill.domain.plan.PlanBill;
import cn.sisyphe.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.bill.infrastructure.plan.jpa.JPAPlanBillRepository;
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
    private JPAPlanBillRepository jpaPlanBillRepository;

    @Override
    public void save(PlanBill bill) {
        jpaPlanBillRepository.save(bill);
    }

    @Override
    public PlanBill findOne(Long id) {
        return jpaPlanBillRepository.findOne(id);
    }
}
