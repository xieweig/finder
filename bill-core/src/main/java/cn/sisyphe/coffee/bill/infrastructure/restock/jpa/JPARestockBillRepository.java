package cn.sisyphe.coffee.bill.infrastructure.restock.jpa;

import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *@date: 2018/1/2
 *@description:
 *@author：xieweiguang
 */
@Repository
public interface JPARestockBillRepository extends JpaRepository<RestockBill, Long>, JpaSpecificationExecutor<RestockBill> {

}
