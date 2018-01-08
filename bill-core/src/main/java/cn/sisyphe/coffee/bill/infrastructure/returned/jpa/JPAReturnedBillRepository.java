package cn.sisyphe.coffee.bill.infrastructure.returned.jpa;

import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ncmao
 * @Date 2017/12/27 16:01
 * @description
 */
@Repository
public interface JPAReturnedBillRepository extends JpaRepository<ReturnedBill, Long>, JpaSpecificationExecutor<ReturnedBill> {

    /**
     * 根据单号查询
     *
     * @param billCode
     * @return
     */
    ReturnedBill findOneByBillCode(String billCode);
}
