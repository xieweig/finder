package cn.sisyphe.coffee.bill.infrastructure.delivery;

import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.delivery.jpa.JPADeliveryBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


/** 配送单
   @company 西西弗文化传播
*  @author yichuan
*  @Date 2018/1/4 10:04
**/
@Service
public class DeliveryBillRepositoryImpl extends AbstractBillRepository<DeliveryBill> implements DeliveryBillRepository {

    @Autowired
    private JPADeliveryBillRepository jpaDeliveryBillRepository;

    /**
     * 按单号查询
     *
     * @param billCode
     * @return
     */
    @Override
    public DeliveryBill findOneByBillCode(String billCode) {
        return jpaDeliveryBillRepository.findOneByBillCode(billCode);
    }


    /**
     * 多条件查询配送单
     *
     * @param ta
     * @param pageable
     * @return
     */
    @Override
    public Page<DeliveryBill> findAll(Specification<DeliveryBill> ta, Pageable pageable) {
        return jpaDeliveryBillRepository.findAll(ta, pageable);
    }


}
