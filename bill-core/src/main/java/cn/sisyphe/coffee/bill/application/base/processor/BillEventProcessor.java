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
 * <p>
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

    /**
     * 出库成功事件
     * 冲减完成之后，收到出库成功事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billInOrOutState.toString() == 'OUT_SUCCESS'")
    public void billOutSuccess(BehaviorEvent<Bill<BillDetail>> event) {
        Bill<BillDetail> bill = event.getBill();
        if (bill != null) {
            AbstractBillManager<Bill> manager = BillManagerFactory.getManager(bill.getBillType());
            //设置出库单出库成功
            manager.outStorageSuccess(bill);
        }
    }

    /**
     * 出库失败事件
     * 冲减完成之后，收到出库失败事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billInOrOutState.toString() == 'OUT_FAILURE'")
    public void billOutFail(BehaviorEvent<Bill<BillDetail>> event) {
        Bill<BillDetail> bill = event.getBill();
        if (bill != null) {
            AbstractBillManager<Bill> manager = BillManagerFactory.getManager(bill.getBillType());
            //设置出库单出库失败
            manager.outStorageFail(bill);
        }
    }
}
