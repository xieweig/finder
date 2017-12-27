package cn.sisyphe.bill.domain.base.behavior;


import cn.sisyphe.bill.domain.base.model.Bill;
import cn.sisyphe.bill.domain.base.model.enums.BillStateEnum;

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
        bill.setBillState(BillStateEnum.SAVED);
    }


}
