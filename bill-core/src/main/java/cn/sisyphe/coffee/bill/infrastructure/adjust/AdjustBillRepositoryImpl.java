package cn.sisyphe.coffee.bill.infrastructure.adjust;

import cn.sisyphe.coffee.bill.domain.adjust.AdjustBill;
import cn.sisyphe.coffee.bill.infrastructure.adjust.jpa.JPAAdjustBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：
 * version:
 */
@Service
public class AdjustBillRepositoryImpl extends AbstractBillRepository<AdjustBill> implements AdjustBillRepository {

    @Autowired
    private JPAAdjustBillRepository jpaAdjustBillRepository;

    @Override
    public AdjustBill findOneByBillCode(String billCode) {
        return jpaAdjustBillRepository.findByBillCode(billCode);
    }

    @Override
    public Page<AdjustBill> findAll(Specification<AdjustBill> ta, Pageable pageable) {
        return jpaAdjustBillRepository.findAll(ta, pageable);
    }
}
