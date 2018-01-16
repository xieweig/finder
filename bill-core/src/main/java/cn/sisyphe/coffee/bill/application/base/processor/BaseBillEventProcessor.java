package cn.sisyphe.coffee.bill.application.base.processor;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.base.BillManagerFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import org.springframework.context.event.EventListener;

/**
 * Created by heyong on 2018/1/16 11:31
 * Description: 基础单据事件
 * @author heyong
 */
public class BaseBillEventProcessor<T extends Bill> {

    /**
     * 已创建事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billState.toString() == 'SAVED'")
    public void billSave(BehaviorEvent<T> event) {
        System.err.println("SAVED:" + event.getBill());
    }

    /**
     * 已提交事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billState.toString() == 'SUBMITTED'")
    public void billSubmit(BehaviorEvent<T> event) {
        System.err.println("SUBMITTED:" + event.getBill());
    }

    /**
     * 审核失败事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billState.toString() == 'AUDIT_FAILURE'")
    public void billFailure(BehaviorEvent<T> event) {
        System.err.println("AUDIT_FAILURE:" + event.getBill());
    }

    /**
     * 审核成功事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billState.toString() == 'AUDIT_SUCCESS'")
    public void billSuccess(BehaviorEvent<T> event) {
        System.err.println("AUDIT_SUCCESS:" + event.getBill());

        T bill = event.getBill();
        if (bill != null) {
            AbstractBillManager<Bill> manager = BillManagerFactory.getManager(bill.getBillType());
            manager.purpose(bill);
        }
    }

    /**
     * 处理完成事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billState.toString() == 'DONE'")
    public void billDone(BehaviorEvent<T> event) {
        System.err.println("DONE:" + event.getBill());
    }
}
