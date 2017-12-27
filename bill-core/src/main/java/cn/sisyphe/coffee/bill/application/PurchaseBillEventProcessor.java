package cn.sisyphe.coffee.bill.application;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.AuditFailureBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
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

    BillServiceFactory serviceFactory = new BillServiceFactory();

    /**
     * 已创建事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'PURCHASE' and #event.billState.toString() == 'SAVED'")
    public void billCreated(BehaviorEvent event) {
        System.err.println("SAVED:" + event.getBill());
    }

    /**
     * 已创建事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'PURCHASE' and event.billState.toString() == 'SUBMITTED'")
    public void billSubmit(BehaviorEvent event) {
        System.err.println("SUBMITTED:" + event.getBill());
    }

    /**
     * 已完成事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'PURCHASE' and #event.billState.toString() == 'DONE'")
    public void billDone(BehaviorEvent event) {
        AbstractBillService purchaseBillService = serviceFactory.createBillService(event.getBill());
        AuditFailureBehavior auditFailureBehavior = new AuditFailureBehavior();
        auditFailureBehavior.doAction();
        purchaseBillService.dispose(auditFailureBehavior);
    }
}
