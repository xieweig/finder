package cn.sisyphe.coffee.bill.domain.allot;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAllotStatusEnum;

/**
 * @author heyong
 */
public class AllotBillService extends AbstractBillService {

    public AllotBillService(Bill bill) {
        super(bill);
    }

    /**
     * 某个单据特殊前置动作处理
     */
    @Override
    public void beforeDispose() {

        AllotBill allotBill = (AllotBill) getBill();
        allotBill.setAllotStatus(BillAllotStatusEnum.ALLOTTING);
    }

    /**
     * 某个单据特殊后置动作处理
     */
    @Override
    public void afterDispose() {
        // TODO: 2017/12/27 在操作之后的一些事情
    }
}
