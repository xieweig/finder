package cn.sisyphe.coffee.bill.infrastructure.purchase;

import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.purchase.jpa.JPAPurchaseBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;


/**
 * Created by heyong on 2017/12/22 10:02
 * Description: 进货单数据库实现
 *
 * @author heyong
 */
@Repository
public class PurchaseBillRepositoryImpl extends AbstractBillRepository<PurchaseBill> implements PurchaseBillRepository {

    @Autowired
    private JPAPurchaseBillRepository jpaPurchaseBillRepository;

    @Override
    public PurchaseBill findOneByBillCode(String billCode) {
        return jpaPurchaseBillRepository.findOneByBillCode(billCode);
    }

    @Override
    public Page<PurchaseBill> findAll(Specification<PurchaseBill> ta, Pageable pageable) {
        return jpaPurchaseBillRepository.findAll(ta, pageable);
    }
}
