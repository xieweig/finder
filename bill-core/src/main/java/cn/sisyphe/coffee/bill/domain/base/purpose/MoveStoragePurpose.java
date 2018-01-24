package cn.sisyphe.coffee.bill.domain.base.purpose;


import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.util.Constant;
import cn.sisyphe.framework.message.core.MessagingHelper;
 import cn.sisyphe.framework.web.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * @author ncmao
 * 调拨单用途处理器
 */
public class MoveStoragePurpose extends AbstractBillPurpose {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoveStoragePurpose.class);

    @Override
    public void handle() {
        LOGGER.info("调拨中");
        AllotBill allotBill = (AllotBill) getBillService().getBill();
        //将调拨单和误差单发送到冲减
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("bill", allotBill);
        //TODO 这里需要更改，应该由冲减系统设置值
        responseResult.setCommandName(Constant.IN_STORAGE_OFFSET_DONE);
        MessagingHelper.messaging().convertAndSend(Constant.BILL_EXCHANGE, getRoutingKey(allotBill), responseResult);

    }


    /**
     * bill.{type}.{purpose}.{status}
     *
     * @param bill
     * @return
     */
    private String getRoutingKey(Bill bill) {
        String key = MessageFormat.format("bill.{0}.{1}.{2}", bill.getBillType(), bill.getBillPurpose(), bill.getBillState());

        LOGGER.info("routing key {}", key);
        return key;
    }
}
