package cn.sisyphe.coffee.bill.application.adjust;

import cn.sisyphe.coffee.bill.application.base.processor.BaseBillEventProcessor;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调拨单据协调层
 * version: 1.0
 *
 * @author XiongJing
 */
@Component
public class AdjustBillEventProcessor extends BaseBillEventProcessor<AdjustBill> {



}
