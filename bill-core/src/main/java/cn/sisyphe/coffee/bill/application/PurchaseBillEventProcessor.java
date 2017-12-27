package cn.sisyphe.coffee.bill.application;

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

    /**
     * 已创建事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'PURCHASE' and #event.billState.toString() == 'SAVED'")
    public void billCreated(BehaviorEvent event) {
        System.err.println("SAVED:" + event.getBill());
    }

//    /**
//     * 已创建事件
//     *
//     * @param event
//     */
//    @EventListener(condition = "#event.billType.toString() ==  'PURCHASE' and event.billState.toString() == 'SUBMITTED'")
//    public void billCreated1(BehaviorEvent event) {
//        System.err.println("SUBMITTED:" + event.getBill());
//    }
}
