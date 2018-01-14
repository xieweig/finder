package cn.sisyphe.coffee.bill.infrastructure.purchase.jpa;

import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by heyong on 2017/12/22 10:02
 * Description: 进货单JPA
 * @author heyong
 */
public interface JPAPurchaseBillRepository extends JpaRepository<PurchaseBill, Long>,JpaSpecificationExecutor<PurchaseBill> {

    /**
     * 根据单号查询
     * @param billCode
     * @return
     */
    PurchaseBill findOneByBillCode(String billCode);

    @Override
    Page<PurchaseBill> findAll(Specification<PurchaseBill> ta, Pageable pageable );
}
