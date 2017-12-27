package cn.sisyphe.bill.core.application;

import cn.sisyphe.bill.core.domain.base.behavior.BehaviorEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：
 * version:
 */
@Service
public class PurchaseBillEventProcessor {
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
    public void billCreated1(BehaviorEvent event) {
        System.err.println("SUBMITTED:" + event.getBill());
    }
}
