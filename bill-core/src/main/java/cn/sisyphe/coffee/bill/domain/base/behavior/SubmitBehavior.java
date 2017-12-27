package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：提交单据动作
 * version: 1.0
 *
 * @author XiongJing
 */
public class SubmitBehavior extends AbstractBillBehavior {

    /**
     * 执行动作
     */
    @Override
    public void doAction() {

        Bill bill = getBillService().getBill();
        if(bill.getBillState().equals(BillStateEnum.SAVED)){
            bill.setBillState(BillStateEnum.SUBMITTED);
        }
    }
}
