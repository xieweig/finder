package cn.sisyphe.coffee.bill.application.allot;

import cn.sisyphe.coffee.bill.application.base.purpose.InStorageBillManager;
import cn.sisyphe.coffee.bill.application.mistake.MistakeBillManager;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by heyong on 2018/1/16 13:52
 * Description: 调拨事件监听
 *
 * @author heyong
 */
@Component
public class AllotBillEventProcessor {


    @Autowired
    private InStorageBillManager inStorageBillManager;

    @Autowired
    private MistakeBillManager mistakeBillManager;

    /**
     * 已创建事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ALLOT' and #event.billState.toString() == 'SAVED'")
    public void billSave(BehaviorEvent event) {
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
     * 调拨单发送到冲减
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ALLOT' and #event.billState.toString() == 'DONE' and #event.billInOrOutState == null")
    public void billDone(BehaviorEvent event) {

        AllotBill allotBill = (AllotBill) event.getBill();

        //只有计划调拨才会更新入库单的状态为调拨中
        if (!allotBill.getSelf()) {
            inStorageBillManager.committing(allotBill.getSourceCode(), allotBill.getSpecificBillType());
            mistakeBillManager.callBackToAddAllotBillCode(allotBill.getMistakeBillCode(), allotBill.getBillCode());
        }
    }

}
