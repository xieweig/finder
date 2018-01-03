package cn.sisyphe.coffee.bill.domain.base.purpose;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.util.Constant;
import cn.sisyphe.framework.message.core.MessagingHelper;

import java.text.MessageFormat;

/**
 * Created by heyong on 2017/12/19 14:07
 * Description: 入库单用途处理器
 * @author heyong
 */
public class InStoragePurpose extends AbstractBillPurpose {

    /**
     * 用途处理器
     */
    @Override
    public void handle() {
        System.err.println("入库中......");

        Bill bill = getBillService().getBill();
        bill.setBillState(BillStateEnum.INSTORAGING);

        MessagingHelper.messaging().convertAndSend(Constant.BILL_EXCHANGE, getRoutingKey(bill), bill);

    }
    /**
     * bill.{type}.{purpose}.{status}
     *
     * @param bill
     * @return
     */
    private String getRoutingKey(Bill bill) {
        String key = MessageFormat.format("bill.{0}.{1}.{2}", bill.getBillType(), bill.getBillPurpose(), bill.getBillState());

        System.err.println(key);
        return key;
    }

    /**
     * 监控出库行为
     * @param bill
     */
    public void listener(Bill bill){

    }
}
