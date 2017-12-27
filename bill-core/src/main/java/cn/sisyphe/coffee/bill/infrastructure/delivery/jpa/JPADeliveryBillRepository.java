package cn.sisyphe.coffee.bill.infrastructure.delivery.jpa;

import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ncmao
 * @Date 2017/12/27 12:25
 * @description
 */
@Repository
public interface JPADeliveryBillRepository extends JpaRepository<DeliveryBill, Long>, JpaSpecificationExecutor<DeliveryBill> {
}
