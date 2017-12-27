package cn.sisyphe.coffee.bill.infrastructure.delivery;

import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.delivery.jpa.JPADeliveryBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ncmao
 * @Date 2017/12/27 12:26
 * @description
 */

@Service
public class DeliveryBillRepositoryImpl extends AbstractBillRepository<DeliveryBill> implements DeliveryBillRepository {

    @Autowired
    private JPADeliveryBillRepository jpaDeliveryBillRepository;

}
