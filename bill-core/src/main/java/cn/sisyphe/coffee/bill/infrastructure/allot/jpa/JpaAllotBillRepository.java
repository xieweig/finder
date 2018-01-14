package cn.sisyphe.coffee.bill.infrastructure.allot.jpa;

import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 调拨单
 *
 * @author ncmao
 * @Date 2017/12/27 12:25
 * @description
 */
public interface JpaAllotBillRepository extends JpaBillRepository<AllotBill> {


}
