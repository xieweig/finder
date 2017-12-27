package cn.sisyphe.coffee.bill.infrastructure.transmit.jpa;

import cn.sisyphe.coffee.bill.domain.transmit.WayBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2017/12/27 17:08
 **/
public interface JPAWayBillRepository extends JpaRepository<WayBill, Long>,
        JpaSpecificationExecutor<WayBill> {

    //
}
