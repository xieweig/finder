package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.framework.ddd.events.BaseDomainEvent;

/**
 * Created by heyong on 2017/12/25 15:38
 * Description: 单据动作事件
 * @author heyong
 */
public class BehaviorEvent<T extends Bill> extends BaseDomainEvent<T> {

    /**
     * 单据
     */
    private T bill;
    /**
     * 状态
     */
    private BillStateEnum billState;

    private BillInOrOutStateEnum billInOrOutState;

    /**
     * 种类
     */
    private BillTypeEnum billType;

    public BehaviorEvent(T bill) {
        this.bill = bill;
        this.billState = bill.getBillState();
        this.billType = bill.getBillType();
        this.billInOrOutState = bill.getInOrOutState();
    }



    public T getBill() {
        return bill;
    }

    public void setBill(T bill) {
        this.bill = bill;
    }

    public BillStateEnum getBillState() {
        return billState;
    }

    public void setBillState(BillStateEnum billState) {
        this.billState = billState;
    }

    public BillTypeEnum getBillType() {
        return billType;
    }

    public void setBillType(BillTypeEnum billType) {
        this.billType = billType;
    }

    public BillInOrOutStateEnum getBillInOrOutState() {
        return billInOrOutState;
    }

    public void setBillInOrOutState(BillInOrOutStateEnum billInOrOutState) {
        this.billInOrOutState = billInOrOutState;
    }

    @Override
    public String toString() {
        return "BehaviorEvent{" +
                "bill=" + bill +
                ", billState=" + billState +
                ", billInOrOutState=" + billInOrOutState +
                ", billType=" + billType +
                "} " + super.toString();
    }
}
