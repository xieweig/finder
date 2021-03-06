package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum.*;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum.SUBMITTED;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum.UNCOMMITTED;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：单据审核动作
 * version: 1.0
 *
 * @author XiongJing
 */
public class AuditBehavior extends AbstractBillBehavior {

    private boolean isSuccess;

    /**
     * 可以接受的状态
     *
     * @return
     */
    @Override
    public BillStateEnum[] allowableStates() {
        return new BillStateEnum[]{BillStateEnum.SUBMITTED, BillStateEnum.OPEN};
    }

    /**
     * 保存的状态
     *
     * @return
     */
    @Override
    public BillStateEnum billState() {
        return isSuccess ? BillStateEnum.AUDIT_SUCCESS : BillStateEnum.AUDIT_FAILURE;
    }

    /**
     * 提交状态
     *
     * @return
     */
    @Override
    public BillSubmitStateEnum submitState() {
        return isSuccess ? SUBMITTED : UNCOMMITTED;
    }

    /**
     * 审核状态
     *
     * @return
     */
    @Override
    public BillAuditStateEnum auditState() {
        return isSuccess ? BillAuditStateEnum.AUDIT_SUCCESS : BillAuditStateEnum.AUDIT_FAILURE;
    }

    /**
     * 出入库状态
     *
     * @return
     */
    @Override
    public BillInOrOutStateEnum inOrOutState() {
        // 审核成功并且是入库单据，那么就是入库中
        if (isSuccess && BillPurposeEnum.IN_STORAGE.equals(getBillService().getBill().getBillPurpose())) {
            return NOT_IN;
        }
        // 审核失败并且是入库单，那么就是出库失败
        else if (!isSuccess && BillPurposeEnum.IN_STORAGE.equals(getBillService().getBill().getBillPurpose())) {
            return IN_FAILURE;
        }
        // 审核成功并且是出库单，那么就是出库中
        else if (isSuccess && BillPurposeEnum.OUT_STORAGE.equals(getBillService().getBill().getBillPurpose())) {
            return NOT_OUT;
        }
        // 审核失败并且是出库单，那么就是未出库
        else if (!isSuccess && BillPurposeEnum.OUT_STORAGE.equals(getBillService().getBill().getBillPurpose())) {
            return NOT_OUT;
        }
        return null;
    }

    public AuditBehavior(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

}
