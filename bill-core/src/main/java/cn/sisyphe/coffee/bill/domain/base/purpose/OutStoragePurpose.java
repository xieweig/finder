package cn.sisyphe.coffee.bill.domain.base.purpose;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.util.Constant;
import cn.sisyphe.framework.message.core.MessagingHelper;
import cn.sisyphe.framework.web.ResponseResult;

import java.text.MessageFormat;

/**
 * Created by heyong on 2017/12/19 12:22
 * Description: 出库用途处理器
 *
 * @author heyong
 */
public class OutStoragePurpose extends AbstractBillPurpose {

    /**
     * 用途处理器
     */
    @Override
    public void handle() {

        System.err.println("out Storage");

        Bill bill = getBillService().getBill();
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("bill", bill);
        //TODO 这里需要更改，应该由冲减系统设置值
        responseResult.setCommandName(Constant.OUT_STORAGE_OFFSET_DONE);
        MessagingHelper.messaging().convertAndSend(Constant.BILL_EXCHANGE, getRoutingKey(bill), responseResult);

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
}
