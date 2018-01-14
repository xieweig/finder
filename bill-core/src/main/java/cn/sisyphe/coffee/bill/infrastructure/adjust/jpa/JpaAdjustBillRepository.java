package cn.sisyphe.coffee.bill.infrastructure.adjust.jpa;

import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：
 * version:
 */
public interface JpaAdjustBillRepository extends JpaBillRepository<AdjustBill> {

}
