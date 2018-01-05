package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
import cn.sisyphe.framework.web.exception.DataException;
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

    /**
     * 可以接受的状态
     *
     * @return
     */
    public abstract BillStateEnum[] allowableStates();

    /**
     * 保存的状态
     *
     * @return
     */
    public abstract BillStateEnum billState();

    /**
     * 提交状态
     */
    public abstract BillSubmitStateEnum submitState();

    /**
     * 审核状态
     */
    public abstract BillAuditStateEnum auditState();

    /**
     * 出入库状态
     */
    public abstract BillInOrOutStateEnum inOrOutState();


    /**
     * 行为处理
     */
    @Override
    public void doAction() {
        Bill bill = getBillService().getBill();

        if (bill == null) {
            throw new DataException("003", "没有找到单据");
        }

        // 判断是否是可接受的状态
        boolean flag = false;
        for (BillStateEnum state : allowableStates()) {
            if (state.equals(bill.getBillState())) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            throw new DataException("004", "当前单据状态不能执行此操作[{0}]", new String[]{bill.getBillState().name()}, bill);
        }
        // 主状态
        bill.setBillState(billState());
        // 提交状态
        bill.setSubmitState(submitState());
        // 审核状态
        bill.setAuditState(auditState());
        // 出入库状态
        bill.setInOrOutState(inOrOutState());
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
