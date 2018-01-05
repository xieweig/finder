package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;

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
