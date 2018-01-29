package cn.sisyphe.coffee.bill.application.base.handler;

import cn.sisyphe.coffee.bill.application.base.purpose.InStorageBillManager;
import cn.sisyphe.coffee.bill.application.mistake.MistakeBillManager;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ncmao
 * @Date 2018/1/29 14:49
 * @description
 */

@Component
public class InStorageOffsetCallbackHandler {


    @Autowired
    private InStorageBillManager inStorageBillManager;

    @Autowired
    private MistakeBillManager mistakeBillManager;


    public void handleInStockSuccess(Bill bill) {
        if (bill != null) {
            if (!bill.getSelf()) {
                //更改入库状态为已调拨
                inStorageBillManager.allotedForInStorageBill(bill.getSourceCode(), bill.getSpecificBillType());
                //更改误差单状态
                mistakeBillManager.callbackMistakeBill(((AllotBill) bill).getMistakeBillCode());

            }

        }
    }
}
