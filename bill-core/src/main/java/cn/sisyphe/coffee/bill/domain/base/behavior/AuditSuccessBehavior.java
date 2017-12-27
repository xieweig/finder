package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：单据审核成功动作
 * version: 1.0
 *
 * @author XiongJing
 */
public class AuditSuccessBehavior extends AbstractBillBehavior {

    /**
     * 执行动作
     */
    @Override
    public void doAction() {
        // 判断状态
        // ...
        Bill bill = getBillService().getBill();
        bill.setBillState(BillStateEnum.AUDITSUCCESS);
    }
}
