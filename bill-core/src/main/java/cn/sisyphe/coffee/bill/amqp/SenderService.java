package cn.sisyphe.coffee.bill.amqp;


import cn.sisyphe.coffee.bill.amqp.config.RabbitMQConfig;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.framework.web.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;


/**
 * Created by Hypier on 2016/10/9.
 * 消息队列发送服务
 */
@Component
public class SenderService {

    private final RabbitMessagingTemplate rabbitMessagingTemplate;

    @Autowired
    public SenderService(RabbitMessagingTemplate rabbitMessagingTemplate) {
        this.rabbitMessagingTemplate = rabbitMessagingTemplate;
    }

//    @Value(value = "${local.station.code}")
//    private String stationClass;

    private final static Logger log = LoggerFactory.getLogger(SenderService.class);

    /**
     * 向库存系统发送出入库信息
     *
     * @param bill
     */
    public void sendBillToStockOffsetRabbitMQ(Bill bill) {

        ResponseResult responseResult = new ResponseResult();
        responseResult.setCommandName("bill-stock-done");
        responseResult.put("bill", bill);

        rabbitMessagingTemplate.setMessageConverter(new MappingJackson2MessageConverter());
        this.rabbitMessagingTemplate.convertAndSend(RabbitMQConfig.BILL_EXCHANGE_NAME,
                "bill.stock.in.out.offset", responseResult);
    }

}
