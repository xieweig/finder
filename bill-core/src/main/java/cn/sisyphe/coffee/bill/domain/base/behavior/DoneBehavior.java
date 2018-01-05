package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：出入库完成动作
 * version: 1.0
 *
 * @author XiongJing
 */
public class DoneBehavior extends AbstractBillBehavior {

    /**
     * 可以接受的状态
     *
     * @return
     */
    @Override
    public BillStateEnum[] allowableStates() {
        return new BillStateEnum[]{BillStateEnum.IN_STORAGING, BillStateEnum.OUT_STORAGING};
    }

    /**
     * 保存的状态
     *
     * @return
     */
    @Override
    public BillStateEnum billState() {
        return BillStateEnum.DONE;
    }

    @Override
    public BillSubmitStateEnum submitState() {
        return null;
    }

    @Override
    public BillAuditStateEnum auditState() {
        return null;
    }

    @Override
    public BillInOrOutStateEnum inOrOutState() {
        return null;
    }
}
