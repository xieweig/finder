package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.enums.*;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum.AUDIT_SUCCESS;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum.*;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum.SUBMITTED;

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
        return AUDIT_SUCCESS;
    }

    /**
     * 出入库状态
     *
     * @return
     */
    @Override
    public BillInOrOutStateEnum inOrOutState() {
        if (BillPurposeEnum.IN_STORAGE.equals(getBillService().getBill().getBillPurpose())) {
            return IN_SUCCESS;
        } else if (BillPurposeEnum.OUT_STORAGE.equals(getBillService().getBill().getBillPurpose())) {
            return OUT_SUCCESS;
        }
        return null;
    }
}
