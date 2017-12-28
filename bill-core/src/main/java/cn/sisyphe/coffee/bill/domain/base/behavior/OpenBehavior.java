package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.framework.web.exception.DataException;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：单据审核中动作
 * version: 1.0
 *
 * @author XiongJing
 */
public class OpenBehavior extends AbstractBillBehavior {
    @Override
    public void doAction() {
        // TODO 判断状态
        Bill bill = getBillService().getBill();
        if (bill != null) {
            if (bill.getBillState().equals(BillStateEnum.OPEN)) {
                bill.setBillState(BillStateEnum.OPEN);
            } else {
                throw new DataException("20003", "当前状态不能更改为打开状态");
            }
        } else {
            throw new DataException("20404", "单据为空");
        }

    }
}
