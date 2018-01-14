package cn.sisyphe.coffee.bill.application.purchase;

import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Created by heyong on 2017/12/25 15:55
 * Description: 进货单事件监听
 *
 * @author heyong
 */
@Service
public class PurchaseBillEventProcessor {
    @Autowired
    private PurchaseBillManager purchaseBillManager;

    /**
     * 已创建事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'PURCHASE' and #event.billState.toString() == 'SAVED'")
    public void billSave(BehaviorEvent event) {
        System.err.println("SAVED:" + event.getBill());
    }

    /**
     * 已提交事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'PURCHASE' and #event.billState.toString() == 'SUBMITTED'")
    public void billSubmit(BehaviorEvent event) {
        System.err.println("SUBMITTED:" + event.getBill());
    }

    /**
     * 审核失败事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'PURCHASE' and #event.billState.toString() == 'AUDIT_FAILURE'")
    public void billFailure(BehaviorEvent event) {
        System.err.println("AUDIT_FAILURE:" + event.getBill());
    }

    /**
     * 审核成功事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'PURCHASE' and #event.billState.toString() == 'AUDIT_SUCCESS'")
    public void billSuccess(BehaviorEvent event) {
        System.err.println("AUDIT_SUCCESS:" + event.getBill());
        purchaseBillManager.purpose((PurchaseBill) event.getBill());
    }

    /**
     * 冲减完成事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'PURCHASE' and #event.billState.toString() == 'DONE'")
    public void billDone(BehaviorEvent event) {
        System.err.println("DONE:" + event.getBill());
    }
}
