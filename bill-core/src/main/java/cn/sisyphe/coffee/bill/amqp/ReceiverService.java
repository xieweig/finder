package cn.sisyphe.coffee.bill.amqp;

import cn.sisyphe.coffee.bill.application.base.purpose.InStorageBillManager;
import cn.sisyphe.coffee.bill.application.purchase.PurchaseBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.util.Constant;
import cn.sisyphe.framework.web.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * Created by Hypier on 2016/10/9.
 * 消息队列接收服务
 */
@Component
public class ReceiverService {


    @Autowired
    private InStorageBillManager inStorageBillManager;

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(ReceiverService.class);

    /**
     * 接收库存系统冲减后的单据消息
     *
     * @param responseResult
     */
    @RabbitListener(queues = "#{'${rabbit.listener.queue}'}")
    public void receiveQueue(ResponseResult responseResult) {

        if (Constant.COMMON_NAME.equals(responseResult.getCommandName())) {
            log.info("接收到库存系统冲减后的单据消息：", responseResult.getResult());
            //purchaseBillManager.doneBill(responseResult);
            return;
        }

        //接收到入库冲减完成将，出库单转存一份入库单
        if (Constant.OUT_STORAGE_OFFSET_DONE.equals(responseResult.getCommandName())) {
            Bill bill = responseResult.toClassObject(responseResult.getResult().get("bill"), Bill.class);
            inStorageBillManager.convertInStorageBill(bill);
        }

        //接收到入库冲减完成，更新入库单和差错单的状态的状态
        if (Constant.IN_STORAGE_OFFSET_DONE.equals(responseResult.getCommandName())) {
            Bill bill = responseResult.toClassObject(responseResult.getResult().get("bill"), Bill.class);
            if (!StringUtils.isEmpty(bill.getSourceCode())) {
                inStorageBillManager.allotedForInStorageBill(bill);
            }
            //TODO 还需要将结果发给唐华玲，她要更改误差单状态
        }
    }
}
