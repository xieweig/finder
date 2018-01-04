package cn.sisyphe.coffee.bill.application.restock;

import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
/**
 *@date: 2018/1/2
 *@description: 
 *@author：xieweiguang
 */
@Service
public class RestockBillEventProcessor  {
    /**
     *
     *notes :
     *  退库出库单的保存行为的回调函数
     */
    @EventListener(condition = "#event.billType.toString() ==  'RESTOCK' and #event.billState.toString() == 'SAVED'")
    public void restockBillSave(BehaviorEvent event) {
        System.err.println("Event Callback SAVED: === " + event.getBill());
    }
    /**
     *
     *notes :
     *  退库出库单的提交行为的回调函数
     */
    @EventListener(condition = "#event.billType.toString() ==  'RESTOCK' and #event.billState.toString() == 'SUBMITTED'")
    public void restockBillCommit(BehaviorEvent event) {
        System.err.println("Event Callback SUBMITTED: === " + event.getBill());
    }
}
