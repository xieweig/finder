package cn.sisyphe.coffee.bill.infrastructure.delivery;

import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * 配送单
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/4 10:04
 **/
public interface DeliveryBillRepository extends BillRepository<DeliveryBill> {


    /**
     * 条件筛选查询
     *
     * @param ta
     * @param pageable
     * @return
     */
    Page<DeliveryBill> findAll(Specification<DeliveryBill> ta, Pageable pageable);

}
