package cn.sisyphe.coffee.bill.infrastructure.restock.jpa;


import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@date: 2018/1/2
 *@description:
 *@author：xieweiguang
 */
@Repository
public interface JPARestockBillRepository extends JpaRepository<RestockBill, Long>, JpaSpecificationExecutor<RestockBill> {
    RestockBill findOneByBillCode(String billCode);

    RestockBill findOneBySourceCode(String sourceCode);

    //do not use it
//    @Query(value = "select * FROM  table a " +
//            "left join table b on a.code=b.code " +
//            "left join table c on b.code=c.code " +
//            "where a.sourceCode = ?1 "
//            ,nativeQuery = true)
//    List<RestockBill> findAllByNothing(String sourceCode);
//

}
