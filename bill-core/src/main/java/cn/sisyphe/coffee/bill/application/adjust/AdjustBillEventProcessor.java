package cn.sisyphe.coffee.bill.application.adjust;

import cn.sisyphe.coffee.bill.domain.adjust.AdjustBill;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调拨单据协调层
 * version: 1.0
 *
 * @author XiongJing
 */
@Service
public class AdjustBillEventProcessor {
    @Autowired
    private AdjustBillManager adjustBillManager;

    /**
     * 已创建事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ADJUST' and #event.billState.toString() == 'SAVED'")
    public void billSave(BehaviorEvent event) {
        System.err.println("SAVED:" + event.getBill());
    }

    /**
     * 已提交事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ADJUST' and #event.billState.toString() == 'SUBMITTED'")
    public void billSubmit(BehaviorEvent event) {
        System.err.println("SUBMITTED:" + event.getBill());
    }

    /**
     * 审核失败事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ADJUST' and #event.billState.toString() == 'AUDIT_FAILURE'")
    public void billFailure(BehaviorEvent event) {
        System.err.println("AUDIT_FAILURE:" + event.getBill());
    }

    /**
     * 审核成功事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ADJUST' and #event.billState.toString() == 'AUDIT_SUCCESS'")
    public void billSuccess(BehaviorEvent event) {
        System.err.println("AUDIT_SUCCESS:" + event.getBill());
        adjustBillManager.purpose((AdjustBill) event.getBill());
    }

    /**
     * 冲减完成事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ADJUST' and #event.billState.toString() == 'DONE'")
    public void billDone(BehaviorEvent event) {
        //冲减完成之后需要生成调剂入库单
        AdjustBill adjustBill = (AdjustBill) event.getBill();
        System.out.println("DONE:" + adjustBill);
    }


}
