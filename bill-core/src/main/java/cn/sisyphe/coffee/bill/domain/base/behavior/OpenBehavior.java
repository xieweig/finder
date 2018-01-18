package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.enums.*;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum.AUDIT_ING;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum.NOT_IN;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum.NOT_OUT;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum.SUBMITTED;

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

    /**
     * 提交状态
     *
     * @return
     */
    @Override
    public BillSubmitStateEnum submitState() {
        return SUBMITTED;
    }

    /**
     * 审核状态
     *
     * @return
     */
    @Override
    public BillAuditStateEnum auditState() {
        return AUDIT_ING;
    }

    /**
     * 出入库状态
     *
     * @return
     */
    @Override
    public BillInOrOutStateEnum inOrOutState() {
        if (BillPurposeEnum.IN_STORAGE.equals(getBillService().getBill().getBillPurpose())) {
            return NOT_IN;
        } else if (BillPurposeEnum.OUT_STORAGE.equals(getBillService().getBill().getBillPurpose())) {
            return NOT_OUT;
        }
        return null;
    }

}
