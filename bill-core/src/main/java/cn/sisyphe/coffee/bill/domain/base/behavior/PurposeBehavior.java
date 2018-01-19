package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.enums.*;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum.AUDIT_SUCCESS;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum.DONE;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum.SUBMITTED;

/**
 * Created by heyong on 2017/12/19 14:36
 * Description: 某个用途（计划、出库、入库）的动作处理
 *
 * @author heyong
 */
public class PurposeBehavior extends AbstractBillBehavior {

    /**
     * 可以接受的状态
     *
     * @return
     */
    @Override
    public BillStateEnum[] allowableStates() {
        return new BillStateEnum[]{BillStateEnum.AUDIT_SUCCESS};
    }

    /**
     * 保存的状态
     *
     * @return
     */
    @Override
    public BillStateEnum billState() {
        // 只处可不回写，交给handle处理
        return DONE;
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
        return null;
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
