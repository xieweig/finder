package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.enums.*;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum.UN_REVIEWED;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum.*;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum.SUBMITTED;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：提交单据动作
 * version: 1.0
 *
 * @author XiongJing
 */
public class SubmitBehavior extends AbstractBillBehavior {

    /**
     * 可以接受的状态
     *
     * @return
     */
    @Override
    public BillStateEnum[] allowableStates() {
        return new BillStateEnum[]{BillStateEnum.SAVED, BillStateEnum.AUDIT_FAILURE};
    }

    /**
     * 保存的状态
     *
     * @return
     */
    @Override
    public BillStateEnum billState() {
        return BillStateEnum.SUBMITTED;
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
        return UN_REVIEWED;
    }

    /**
     * 出入库状态
     *
     * @return
     */
    @Override
    public BillInOrOutStateEnum inOrOutState() {
        if (BillPurposeEnum.InStorage.equals(super.getBillService().getBill().getBillPurpose())) {
            return NOT_IN;
        } else if (BillPurposeEnum.OutStorage.equals(super.getBillService().getBill().getBillPurpose())) {
            return NOT_OUT;
        } else {
            return null;
        }
    }

}
