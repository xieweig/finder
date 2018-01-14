package cn.sisyphe.coffee.bill.infrastructure.adjust;

import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.infrastructure.adjust.jpa.JpaAdjustBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：
 * version:
 */
@Repository
public class AdjustBillRepositoryImpl extends AbstractBillRepository<AdjustBill> implements AdjustBillRepository {

    @Autowired
    public AdjustBillRepositoryImpl(JpaBillRepository<AdjustBill> jpaBillRepository) {
        super(jpaBillRepository);
    }
}
