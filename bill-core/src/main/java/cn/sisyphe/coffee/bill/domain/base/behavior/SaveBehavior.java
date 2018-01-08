package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.enums.*;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum.UN_REVIEWED;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum.*;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum.UNCOMMITTED;

/**
 * Created by heyong on 2017/12/19 12:03
 * Description: 保存单据动作
 *
 * @author heyong
 */
public class SaveBehavior extends AbstractBillBehavior {

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
        return BillStateEnum.SAVED;
    }

    /**
     * 提交状态
     *
     * @return
     */
    @Override
    public BillSubmitStateEnum submitState() {
        return UNCOMMITTED;
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
        if (BillPurposeEnum.InStorage.equals(getBillService().getBill().getBillPurpose())) {
            return NOT_IN;
        } else if (BillPurposeEnum.OutStorage.equals(getBillService().getBill().getBillPurpose())) {
            return NOT_OUT;
        } else {
            return null;
        }
    }

}
