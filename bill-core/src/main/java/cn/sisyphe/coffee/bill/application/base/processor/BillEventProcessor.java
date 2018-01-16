package cn.sisyphe.coffee.bill.application.base.processor;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.base.BillManagerFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author heyong
 *
 * 基础事件监听
 */
@Component
public class BillEventProcessor {

    /**
     * 审核成功事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billState.toString() == 'AUDIT_SUCCESS'")
    public void billSuccess(BehaviorEvent<Bill<BillDetail>> event) {

        System.err.println("AUDIT_SUCCESS:" + event.getBill());

        Bill<BillDetail> bill = event.getBill();
        if (bill != null) {
            AbstractBillManager<Bill> manager = BillManagerFactory.getManager(bill.getBillType());
            manager.purpose(bill);
        }
    }
}
