package cn.sisyphe.coffee.bill.application.base.handler;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.base.BillManagerFactory;
import cn.sisyphe.coffee.bill.application.base.purpose.InStorageBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ncmao
 * @Date 2018/1/29 14:49
 * @description
 */

@Component
public class OutStorageOffsetCallbackHandler {


    @Autowired
    private InStorageBillManager inStorageBillManager;


    @Transactional(rollbackFor = RuntimeException.class)
    public void handleOutStockSuccess(Bill bill) {
        if (bill != null) {
            AbstractBillManager<Bill> manager = BillManagerFactory.getManager(bill.getBillType());
            //设置出库单出库成功
            Bill foundBill = manager.findOneByBillCode(bill.getBillCode());
            //更新出库时间
            foundBill.setOutWareHouseTime(new Date());
            //出库成功
            manager.done(foundBill);
            //生成入库单
            inStorageBillManager.convertInStorageBill(bill);

        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void handleOutStockFail(Bill bill) {
        if (bill != null) {
            AbstractBillManager<Bill> manager = BillManagerFactory.getManager(bill.getBillType());
            //设置出库单出库失败
            manager.outStorageFail(bill);

        }
    }


}
