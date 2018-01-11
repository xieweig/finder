package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum.AUDIT_SUCCESS;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum.ALLOT;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum.SUBMITTED;

/**
 * @Date 2018/1/11 15:53
 * @description
 */
public class AllotBehavior extends AbstractBillBehavior {
    @Override
    public BillStateEnum[] allowableStates() {
        return new BillStateEnum[]{BillStateEnum.SAVED, BillStateEnum.UN_ALLOT};
    }

    /**
     * 保存的状态
     *
     * @return
     */
    @Override
    public BillStateEnum billState() {
        // 只处可不回写，交给handle处理
        return ALLOT;
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
        return BillInOrOutStateEnum.NOT_OUT;
    }

    /**
     * 执行动作，可调用单据动作的处理
     */
    @Override
    public void doAction() {

        super.doAction();

        getBillService().getBillPurpose().handle();
    }
}
