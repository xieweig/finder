package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.framework.web.exception.DataException;

/**
 * Created by heyong on 2017/12/19 12:03
 * Description: 保存单据动作
 *
 * @author heyong
 */
public class SaveBehavior extends AbstractBillBehavior {

    /**
     * 执行动作
     */
    @Override
    public void doAction() {
        Bill bill = getBillService().getBill();
        if (bill != null) {
            if (bill.getBillState().equals(BillStateEnum.SAVED.name())
                    || bill.getBillState().equals(BillStateEnum.OPEN.name())) {
                bill.setBillState(BillStateEnum.SAVED);
            } else {

                throw new DataException("20001", "当前状态不能保存");
            }
        } else {
            throw new DataException("20404", "单据为空");
        }
    }
}
