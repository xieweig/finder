package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：单据审核中动作
 * version: 1.0
 *
 * @author XiongJing
 */
public class AuditingBehavior extends AbstractBillBehavior {
    @Override
    public void doAction() {
        // TODO 判断状态
        Bill bill = getBillService().getBill();
        if(bill.getBillState().equals(BillStateEnum.SUBMITTED)){
            bill.setBillState(BillStateEnum.AUDITING);
        }
    }
}
