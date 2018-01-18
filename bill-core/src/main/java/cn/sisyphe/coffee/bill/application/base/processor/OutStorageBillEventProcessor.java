package cn.sisyphe.coffee.bill.application.base.processor;

import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.plan.enums.OperationStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 出库单监听事件
 * @author bifenglin
 */
@Component
public class OutStorageBillEventProcessor {

    @Autowired
    private PlanBillManager planBillManager;
    /**
     *
     *notes :
     *  出库单的保存行为的回调函数
     */
    @EventListener(condition = "#event.billState.toString() == 'SAVED' and #event.bill.billPurpose.OUT_STORAGE.toString() == 'OUT_STORAGE'")
    public void billSave(BehaviorEvent event) {
        //修改子计划重捡状态
        Bill bill = event.getBill();
        if (!StringUtils.isEmpty(bill.getSourceCode())){
            planBillManager.operationPickGood(bill.getSourceCode(), OperationStateEnum.OPERATION);
        }
        System.err.println("Event Callback SAVED: === " + event.getBill());
    }

    /**
     *
     *notes :
     *  出库单的提交行为的回调函数
     * @param event
     */
    @EventListener(condition = "#event.billState.toString() == 'SUBMITTED' and #event.bill.billPurpose.OUT_STORAGE.toString() == 'OUT_STORAGE'")
    public void billSubmit(BehaviorEvent event) {
        //修改子计划重捡状态
        Bill bill = event.getBill();
        if (!StringUtils.isEmpty(bill.getSourceCode())){
            planBillManager.operationPickGood(bill.getSourceCode(), OperationStateEnum.OPERATION);
        }
        System.err.println("Event Callback SUBMITTED: === " + event.getBill());
    }



}

