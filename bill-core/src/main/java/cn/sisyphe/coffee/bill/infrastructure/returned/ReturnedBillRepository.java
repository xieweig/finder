package cn.sisyphe.coffee.bill.infrastructure.returned;

import cn.sisyphe.coffee.bill.domain.returned.ReturnedBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author ncmao
 * @Date 2017/12/27 16:01
 * @description
 */
public interface ReturnedBillRepository extends BillRepository<ReturnedBill> {
    /**
     * 根据多条件查询
     * @param ta
     * @param pageable
     * @return
     */
    Page<ReturnedBill> findAll(Specification<ReturnedBill> ta, Pageable pageable);
    /**
     * 根据单据单号查询
     */
    ReturnedBill findOneByBillCode(String billCode);
    /**
     * 根据单据来源单号查询
     */
    ReturnedBill findOneBySourceCode(String sourceCode);
}
