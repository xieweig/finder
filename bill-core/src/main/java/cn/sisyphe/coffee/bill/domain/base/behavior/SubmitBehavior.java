package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.framework.web.exception.DataException;

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
        if (bill != null) {
            if (bill.getBillState().equals(BillStateEnum.OPEN)
                    || bill.getBillState().equals(BillStateEnum.SAVED)
                    || bill.getBillState().equals(BillStateEnum.SUBMITTED)) {
                bill.setBillState(BillStateEnum.SUBMITTED);
            } else {
                throw new DataException("20002", "当前状态不能提交");
            }
        } else {
            throw new DataException("20404", "单据为空");
        }

    }
}
