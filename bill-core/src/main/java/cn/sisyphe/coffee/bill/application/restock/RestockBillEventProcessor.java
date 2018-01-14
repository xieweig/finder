package cn.sisyphe.coffee.bill.application.restock;

import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 *@date: 2018/1/2
 *@description: 
 *@author：xieweiguang
 */
@Service
public class RestockBillEventProcessor  {

    @Autowired
    private RestockBillManager restockBillManager;

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

    /**
     * 审核失败事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'RESTOCK' and #event.billState.toString() == 'AUDIT_FAILURE'")
    public void billFailure(BehaviorEvent event) {
        System.err.println("AUDIT_FAILURE:" + event.getBill());
    }

    /**
     * 审核成功事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'RESTOCK' and #event.billState.toString() == 'AUDIT_SUCCESS'")
    public void billSuccess(BehaviorEvent event) {
        System.err.println("AUDIT_SUCCESS:" + event.getBill());
        restockBillManager.purpose((RestockBill) event.getBill());
    }

    /**
     * 冲减完成事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'RESTOCK' and #event.billState.toString() == 'DONE'")
    public void billDone(BehaviorEvent event) {
        System.err.println("DONE:" + event.getBill());
    }
}
