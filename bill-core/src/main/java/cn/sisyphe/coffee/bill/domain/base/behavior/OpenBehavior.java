package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：单据审核中动作
 * version: 1.0
 *
 * @author XiongJing
 */
public class OpenBehavior extends AbstractBillBehavior {

    /**
     * 可以接受的状态
     *
     * @return
     */
    @Override
    public BillStateEnum[] allowableStates() {
        return new BillStateEnum[]{BillStateEnum.SUBMITTED};
    }

    /**
     * 保存的状态
     *
     * @return
     */
    @Override
    public BillStateEnum billState() {
        return BillStateEnum.OPEN;
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
