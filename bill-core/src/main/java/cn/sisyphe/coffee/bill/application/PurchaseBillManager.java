package cn.sisyphe.coffee.bill.application;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.PurposeBehavior;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单协调层
 * version: 1.0
 *
 * @author XiongJing
 */
public class PurchaseBillManager {

    @Autowired
    BillServiceFactory serviceFactory;

    /**
     * 保存进货单
     *
     * @param purchaseBill
     */
    public void saveBill(PurchaseBill purchaseBill) {
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        PurposeBehavior purposeBehavior = new PurposeBehavior();
        purposeBehavior.savePurposeBill();
        purchaseBillService.dispose(purposeBehavior);
    }

}
