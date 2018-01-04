package cn.sisyphe.coffee.bill.amqp;

import cn.sisyphe.coffee.bill.application.purchase.PurchaseBillManager;
import cn.sisyphe.coffee.bill.util.Constant;
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
    private PurchaseBillManager purchaseBillManager;
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
            log.info("接收到库存系统冲减后的单据消息：",responseResult.getResult());
            purchaseBillManager.doneBill(responseResult);
        }
    }
}
