package cn.sisyphe.coffee.bill.application.base.handler;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.base.BillManagerFactory;
import cn.sisyphe.coffee.bill.application.base.purpose.InStorageBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ncmao
 * @Date 2018/1/29 14:49
 * @description
 */

@Component
public class OutStorageOffsetCallbackHandler {


    @Autowired
    private InStorageBillManager inStorageBillManager;


    public void handleOutStockSuccess(Bill bill) {
        if (bill != null) {
            AbstractBillManager<Bill> manager = BillManagerFactory.getManager(bill.getBillType());
            //设置出库单出库成功
            manager.done(bill);
            //生成入库单
            inStorageBillManager.convertInStorageBill(bill);

        }
    }
}
