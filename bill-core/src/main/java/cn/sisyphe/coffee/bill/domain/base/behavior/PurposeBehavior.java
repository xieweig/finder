package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;

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
