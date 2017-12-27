package cn.sisyphe.coffee.bill.infrastructure.purchase;

import cn.sisyphe.coffee.bill.domain.delivery.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.purchase.jpa.JPAPurchaseBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
