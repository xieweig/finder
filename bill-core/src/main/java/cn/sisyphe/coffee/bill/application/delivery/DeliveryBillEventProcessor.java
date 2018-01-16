package cn.sisyphe.coffee.bill.application.delivery;

import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * 配送单事件监听
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/5 9:40
 **/
@Component
public class DeliveryBillEventProcessor {

    /**
     * 已创建事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'DELIVERY' and #event.billState.toString() == 'SAVED'")
    public void billSave(BehaviorEvent event) {
        System.err.println("SAVED:" + event.getBill());
    }

    /**
     * 已提交事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'DELIVERY' and #event.billState.toString() == 'SUBMITTED'")
    public void billSubmit(BehaviorEvent event) {
        System.err.println("SUBMITTED:" + event.getBill());
    }

    /**
     * 审核失败事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'DELIVERY' and #event.billState.toString() == 'AUDIT_FAILURE'")
    public void billFailure(BehaviorEvent event) {
        System.err.println("AUDIT_FAILURE:" + event.getBill());
    }

    /**
     * 审核成功事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'DELIVERY' and #event.billState.toString() == 'AUDIT_SUCCESS'")
    public void billSuccess(BehaviorEvent event) {

    }

    /**
     * 冲减完成事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'DELIVERY' and #event.billState.toString() == 'DONE'")
    public void billDone(BehaviorEvent event) {
        //冲减完成之后需要生成调剂入库单
        AdjustBill adjustBill = (AdjustBill) event.getBill();
        System.out.println("DONE:" + adjustBill);
    }
}
