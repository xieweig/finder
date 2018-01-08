package cn.sisyphe.coffee.bill.domain.adjust;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：
 * version:
 */
public class AdjustBillService  extends AbstractBillService {

    public AdjustBillService(Bill bill) {
        super(bill);
    }

    /**
     * 某个单据特殊前置动作处理
     */
    @Override
    public void beforeDispose() {
        // TODO: 2017/12/27 在操作之前的一些事情
    }

    /**
     * 某个单据特殊后置动作处理
     */
    @Override
    public void afterDispose() {
        // TODO: 2017/12/27 在操作之后的一些事情
    }
}
