package cn.sisyphe.coffee.bill.application.delivery;

import cn.sisyphe.coffee.bill.application.base.processor.BaseBillEventProcessor;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * 配送单事件监听
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/5 9:40
 **/
@Component
public class DeliveryBillEventProcessor extends BaseBillEventProcessor<DeliveryBill> {


}
