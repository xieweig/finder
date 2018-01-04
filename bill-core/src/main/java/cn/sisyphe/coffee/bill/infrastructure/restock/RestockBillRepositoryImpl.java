package cn.sisyphe.coffee.bill.infrastructure.restock;

import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.restock.jpa.JPARestockBillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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


    /**
     * 多条件查询
     * @param ta
     * @param pageable
     * @return
     */
    @Override
    public Page<RestockBill> findAll(Specification<RestockBill> ta, Pageable pageable) {
        return jpaRestockBillRepository.findAll(ta, pageable);
    }

    @Override
    public RestockBill findOneByBillCode(String billCode) {
        return null;
    }
}
