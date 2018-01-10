package cn.sisyphe.coffee.bill.application.restock;

import cn.sisyphe.coffee.bill.application.planbill.PlanBillManager;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import cn.sisyphe.coffee.bill.domain.plan.enums.OperationStateEnum;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
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
    private PlanBillManager planBillManager;

    /**
     *
     *notes :
     *  退库出库单的保存行为的回调函数
     */
    @EventListener(condition = "#event.billType.toString() ==  'RESTOCK' and #event.billState.toString() == 'SAVED'")
    public void restockBillSave(BehaviorEvent event) {
        //修改子计划重捡状态
        planBillManager.Operation(event.getBill().getBillCode(), OperationStateEnum.OPERATION);
        System.err.println("Event Callback SAVED: === " + event.getBill());
    }
    /**
     *
     *notes :
     *  退库出库单的提交行为的回调函数
     */
    @EventListener(condition = "#event.billType.toString() ==  'RESTOCK' and #event.billState.toString() == 'SUBMITTED'")
    public void restockBillCommit(BehaviorEvent event) {
        //修改子计划重捡状态
        planBillManager.Operation(event.getBill().getBillCode(), OperationStateEnum.OPERATION);
        System.err.println("Event Callback SUBMITTED: === " + event.getBill());

    }
}
