package cn.sisyphe.coffee.bill.infrastructure.restock;

import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.restock.jpa.JpaRestockBillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @date: 2018/1/2
 * @description:
 * @author：xieweiguang
 */
@Repository
public class RestockBillRepositoryImpl extends AbstractBillRepository<RestockBill> implements RestockBillRepository {

    @Autowired
    public RestockBillRepositoryImpl(JpaBillRepository<RestockBill> jpaBillRepository) {
        super(jpaBillRepository);
    }
}
