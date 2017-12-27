package cn.sisyphe.bill.domain.base.behavior;

import cn.sisyphe.bill.domain.base.AbstractBillService;
import cn.sisyphe.bill.domain.base.model.Bill;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Created by heyong on 2017/12/23 14:31
 * Description: 单据动作抽象
 *
 * @author heyong
 */
public abstract class AbstractBillBehavior implements BillBehavior {

    private AbstractBillService billService;

    public AbstractBillBehavior(AbstractBillService billService) {
        this.billService = billService;
    }

    AbstractBillBehavior() {
    }

    public AbstractBillService getBillService() {
        return billService;
    }

    @Override
    public void setBillService(AbstractBillService billService) {
        this.billService = billService;
    }

    /**
     * 发送完成事件
     *
     * @param applicationEventPublisher
     */
    @Override
    public void sendEvent(ApplicationEventPublisher applicationEventPublisher) {
        Bill bill = billService.getBill();
        applicationEventPublisher.publishEvent(new BehaviorEvent(bill));
    }
}
