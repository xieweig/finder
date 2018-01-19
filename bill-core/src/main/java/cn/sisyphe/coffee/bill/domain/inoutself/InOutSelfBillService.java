package cn.sisyphe.coffee.bill.domain.inoutself;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;

/**
 * @date: 2018/1/19
 * @description:
 * @author：mayupeng
 */
public class InOutSelfBillService extends AbstractBillService {
    public InOutSelfBillService(Bill bill) {
        super(bill);
    }

    /**
     * 某个单据特殊前置动作处理
     */
    @Override
    public void beforeDispose() {
    }

    /**
     * 某个单据特殊后置动作处理
     */
    @Override
    public void afterDispose() {
    }
}
