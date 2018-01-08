package cn.sisyphe.coffee.bill.application.returned;


import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author mayupeng
 * @Date 2018/01/07
 * @description 退货单事件监听
 */
@Service
public class ReturnedEventBillProcessor {
    /**
     * 已创建事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'RETURNED' and #event.billState.toString() == 'SAVED'")
    public void billSave(BehaviorEvent event) {
        System.err.println("SAVED:" + event.getBill());
    }

    /**
     * 已提交事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'RETURNED' and #event.billState.toString() == 'SUBMITTED'")
    public void billSubmit(BehaviorEvent event) {
        System.err.println("SUBMITTED:" + event.getBill());
    }
}
