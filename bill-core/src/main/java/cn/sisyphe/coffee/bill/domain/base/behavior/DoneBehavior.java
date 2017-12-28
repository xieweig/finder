package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.framework.web.exception.DataException;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：出入库完成动作
 * version: 1.0
 *
 * @author XiongJing
 */
public class DoneBehavior extends AbstractBillBehavior {
    @Override
    public void doAction() {
        Bill bill = getBillService().getBill();
        if (bill != null) {
            if (bill.getBillState().equals(BillStateEnum.AUDITSUCCESS)) {
                bill.setBillState(BillStateEnum.DONE);
            } else {
                throw new DataException("20005", "当前状态不能出入库完成");
            }
        } else {
            throw new DataException("20404", "单据为空");
        }

    }
}
