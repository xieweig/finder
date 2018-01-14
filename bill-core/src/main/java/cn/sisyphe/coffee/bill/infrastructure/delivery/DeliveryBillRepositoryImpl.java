package cn.sisyphe.coffee.bill.infrastructure.delivery;

import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.delivery.jpa.JpaDeliveryBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


/** 配送单
   @company 西西弗文化传播
*  @author yichuan
*  @Date 2018/1/4 10:04
**/
@Repository
public class DeliveryBillRepositoryImpl extends AbstractBillRepository<DeliveryBill> implements DeliveryBillRepository {

    @Autowired
    public DeliveryBillRepositoryImpl(JpaBillRepository<DeliveryBill> jpaBillRepository) {
        super(jpaBillRepository);
    }
}
