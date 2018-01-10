package cn.sisyphe.coffee.bill.infrastructure.adjust;

import cn.sisyphe.coffee.bill.domain.adjust.AdjustBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：
 * version:
 */
public interface AdjustBillRepository extends BillRepository<AdjustBill> {

    Page<AdjustBill> findAll(Specification<AdjustBill> ta, Pageable pageable);
}
