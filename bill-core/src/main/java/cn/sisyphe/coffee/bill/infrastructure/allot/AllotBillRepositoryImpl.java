package cn.sisyphe.coffee.bill.infrastructure.allot;

import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.infrastructure.allot.jpa.JPAAllotBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


/**
 * 调拨单
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/4 10:04
 **/
@Service
public class AllotBillRepositoryImpl extends AbstractBillRepository<AllotBill> implements AllotBillRepository {

    @Autowired
    private JPAAllotBillRepository jpaAllotBillRepository;

    /**
     * 按单号查询
     *
     * @param billCode
     * @return
     */
    @Override
    public AllotBill findOneByBillCode(String billCode) {
        return jpaAllotBillRepository.findOneByBillCode(billCode);
    }


    /**
     * 多条件查询调拨单
     *
     * @param ta
     * @param pageable
     * @return
     */
    @Override
    public Page<AllotBill> findAll(Specification<AllotBill> ta, Pageable pageable) {
        return jpaAllotBillRepository.findAll(ta, pageable);
    }


}
