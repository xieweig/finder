package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Created by heyong on 2017/12/19 11:06
 * Description: 单据动作
 * @author heyong
 */
public interface BillBehavior {

    /**
     * 单据服务
     * @param billService
     */
    void setBillService(AbstractBillService billService);

    /**
     * 执行动作
     */
    void doAction();

    /**
     * 发送完成事件
     * @param applicationEventPublisher
     */
    void sendEvent(ApplicationEventPublisher applicationEventPublisher);
}
