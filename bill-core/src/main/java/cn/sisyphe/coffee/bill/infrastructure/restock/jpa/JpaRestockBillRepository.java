package cn.sisyphe.coffee.bill.infrastructure.restock.jpa;


import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;
import org.springframework.stereotype.Repository;

/**
 * @date: 2018/1/2
 * @description:
 * @author：xieweiguang
 */
public interface JpaRestockBillRepository extends JpaBillRepository<RestockBill> {

}
