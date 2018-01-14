package cn.sisyphe.coffee.bill.infrastructure.restock;

import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 *@date: 2018/1/2
 *@description:
 *@author：xieweiguang
 */
public interface RestockBillRepository extends BillRepository<RestockBill>{

    /**
     * 根据多条件查询
     * @param ta
     * @param pageable
     * @return
     */
    Page<RestockBill> findAll(Specification<RestockBill> ta, Pageable pageable);
    /**
     * 根据单据来源单号查询
     */
    RestockBill findOneBySourceCode(String sourceCode);
}
