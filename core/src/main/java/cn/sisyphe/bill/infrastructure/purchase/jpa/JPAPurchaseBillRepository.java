package cn.sisyphe.bill.infrastructure.purchase.jpa;


import cn.sisyphe.bill.domain.delivery.purchase.PurchaseBill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by heyong on 2017/12/22 10:02
 * Description: 进货单JPA
 * @author heyong
 */
public interface JPAPurchaseBillRepository extends JpaRepository<PurchaseBill, Long> {

    /**
     * 根据单号查询
     * @param billCode
     * @return
     */
    PurchaseBill findOneByBillCode(String billCode);
}
