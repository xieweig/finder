package cn.sisyphe.coffee.bill.infrastructure.restock;

import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.restock.jpa.JPARestockBillRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 *@date: 2018/1/2
 *@description:
 *@author：xieweiguang
 */
@Repository
public class RestockBillRepositoryImpl extends AbstractBillRepository<RestockBill> implements RestockBillRepository{

    @Resource
    private JPARestockBillRepository jpaRestockBillRepository;
    // 外界调用从此门面类调用

}
