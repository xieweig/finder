package cn.sisyphe.coffee.bill.amqp;

import cn.sisyphe.coffee.bill.application.base.handler.InStorageOffsetCallbackHandler;
import cn.sisyphe.coffee.bill.application.base.handler.OutStorageOffsetCallbackHandler;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.util.Constant;
import cn.sisyphe.coffee.bill.util.ResponseResultMapUtil;
import cn.sisyphe.framework.web.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by Hypier on 2016/10/9.
 * 消息队列接收服务
 */
@Component
public class ReceiverService {


    @Autowired
    private OutStorageOffsetCallbackHandler outStorageOffsetCallbackHandler;

    @Autowired
    private InStorageOffsetCallbackHandler inStorageOffsetCallbackHandler;

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(ReceiverService.class);

    /**
     * 接收库存系统冲减后的单据消息
     *
     * @param responseResult
     */
    @RabbitListener(queues = "${rabbit.listener.queue}")
    public void receiveQueue(ResponseResult responseResult) throws InterruptedException {

        if (Constant.COMMON_NAME.equals(responseResult.getCommandName())) {
            log.info("接收到库存系统冲减后的单据消息：", responseResult.getResult());
            //purchaseBillManager.doneBill(responseResult);
            return;
        }

        Bill bill = new ResponseResultMapUtil().convertBillFromResponse(responseResult);
        //接收到入库冲减完成将，出库单转存一份入库单
        if (Constant.OUT_STORAGE_OFFSET_DONE.equals(responseResult.getCommandName())) {
            //TODO 这里的出入库状态应该在冲减系统变成出库成功或者失败
            outStorageOffsetCallbackHandler.handleOutStockSuccess(bill);

        }

        //接收到入库冲减完成，更新入库单和差错单的状态的状态
        if (Constant.IN_STORAGE_OFFSET_DONE.equals(responseResult.getCommandName())) {
            //TODO 这里的出入库状态应该在冲减系统变成入库成功或者失败
            //---------------------------------------------------
            inStorageOffsetCallbackHandler.handleInStockSuccess(bill);

        }
    }
}
