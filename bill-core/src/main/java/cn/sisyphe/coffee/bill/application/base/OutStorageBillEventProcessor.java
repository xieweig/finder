package cn.sisyphe.coffee.bill.application.base;

import cn.sisyphe.coffee.bill.application.planbill.PlanBillManager;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.plan.enums.OperationStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class OutStorageBillEventProcessor {

    @Autowired
    private PlanBillManager planBillManager;
    /**
     *
     *notes :
     *  退库出库单的保存行为的回调函数
     */
    @EventListener(condition = "#event.billState.toString() == 'SAVED'")
    public void restockBillSave(BehaviorEvent event) {
        //修改子计划重捡状态
        Bill bill = event.getBill();
        if (!StringUtils.isEmpty(bill.getSourceCode())){
            planBillManager.operation(bill.getSourceCode(), OperationStateEnum.OPERATION);
        }
        System.err.println("Event Callback SAVED: === " + event.getBill());
    }
}

