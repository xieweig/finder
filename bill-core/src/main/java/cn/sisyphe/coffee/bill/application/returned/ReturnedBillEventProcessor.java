package cn.sisyphe.coffee.bill.application.returned;

import cn.sisyphe.coffee.bill.application.base.processor.BaseBillEventProcessor;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @date: 2018/1/2
 * @description:
 * @author：xieweiguang
 */
@Component
public class ReturnedBillEventProcessor extends BaseBillEventProcessor<ReturnedBill> {


}
