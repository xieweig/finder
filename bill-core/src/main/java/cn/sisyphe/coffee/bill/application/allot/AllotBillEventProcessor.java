package cn.sisyphe.coffee.bill.application.allot;

import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by heyong on 2018/1/16 13:52
 * Description: 调拨事件监听
 * @author heyong
 */
@Component
public class AllotBillEventProcessor {

    /**
     * 已创建事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ALLOT' and #event.billState.toString() == 'SAVED'")
    public void billSave(BehaviorEvent event) {
        System.err.println("SAVED:" + event.getBill());
    }

    /**
     * 已提交事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ALLOT' and #event.billState.toString() == 'SUBMITTED'")
    public void billSubmit(BehaviorEvent event) {
        System.err.println("SUBMITTED:" + event.getBill());
    }

    /**
     * 审核失败事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ALLOT' and #event.billState.toString() == 'AUDIT_FAILURE'")
    public void billFailure(BehaviorEvent event) {
        System.err.println("AUDIT_FAILURE:" + event.getBill());
    }

    /**
     * 审核成功事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ALLOT' and #event.billState.toString() == 'AUDIT_SUCCESS'")
    public void billSuccess(BehaviorEvent event) {

    }

    /**
     * 冲减完成事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ALLOT' and #event.billState.toString() == 'DONE'")
    public void billDone(BehaviorEvent event) {
        //冲减完成之后需要生成调剂入库单
        AdjustBill adjustBill = (AdjustBill) event.getBill();
        System.out.println("DONE:" + adjustBill);
    }

}
