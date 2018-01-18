package cn.sisyphe.coffee.bill.application.base.processor;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.base.BillManagerFactory;
import cn.sisyphe.coffee.bill.application.base.purpose.InStorageBillManager;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author heyong
 * <p>
 * 基础事件监听
 */
@Component
public class BillEventProcessor {

    @Autowired
    private InStorageBillManager inStorageBillManager;

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
    @EventListener(condition = "#event.billInOrOutState != null && #event.billInOrOutState.toString() == 'OUT_SUCCESS'")
    public void billOutSuccess(BehaviorEvent<Bill<BillDetail>> event) {
        Bill<BillDetail> bill = event.getBill();
        if (bill != null) {
            AbstractBillManager<Bill> manager = BillManagerFactory.getManager(bill.getBillType());
            //设置出库单出库成功
            manager.outStorageSuccess(bill);
            //生成入库单
            inStorageBillManager.convertInStorageBill(bill);

        }
    }

    /**
     * 出库失败事件
     * 冲减完成之后，收到出库失败事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billInOrOutState != null && #event.billInOrOutState.toString() == 'OUT_FAILURE'")
    public void billOutFail(BehaviorEvent<Bill<BillDetail>> event) {
        Bill<BillDetail> bill = event.getBill();
        if (bill != null) {
            AbstractBillManager<Bill> manager = BillManagerFactory.getManager(bill.getBillType());
            //设置出库单出库失败
            manager.outStorageFail(bill);
        }
    }

    /**
     * 入库成功事件
     * 冲减完成之后，收到入库成功事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billInOrOutState != null && #event.billInOrOutState.toString() == 'IN_SUCCESS'")
    public void billInSuccess(BehaviorEvent<Bill<BillDetail>> event) {
        Bill<BillDetail> bill = event.getBill();
        if (bill != null) {
            if (!StringUtils.isEmpty(bill.getSourceCode())) {
                //更改入库状态为已调拨
                inStorageBillManager.allotedForInStorageBill(bill);

            }

        }
    }

    /**
     * 入库失败事件
     * 冲减完成之后，入库失败事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billInOrOutState != null && #event.billInOrOutState.toString() == 'IN_FAILURE'")
    public void billInFail(BehaviorEvent<Bill<BillDetail>> event) {
        Bill<BillDetail> bill = event.getBill();
    }
}
