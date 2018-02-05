package cn.sisyphe.coffee.bill.application.base.handler;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.base.BillManagerFactory;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ncmao
 * @Date 2018/1/29 14:49
 * @description
 */

@Component
public class InStorageOffsetCallbackHandler {

    @Transactional(rollbackFor = RuntimeException.class)
    public void handleInStockSuccess(Bill bill) {
        if (bill != null) {
            AbstractBillManager<Bill> manager = BillManagerFactory.getManager(bill.getBillType());
            //设置入库单出库成功
            Bill foundBill = manager.findOneByBillCode(bill.getBillCode());
            //更新入库时间
            foundBill.setInWareHouseTime(new Date());
            //入库成功
            manager.done(foundBill);
        }
    }
}
