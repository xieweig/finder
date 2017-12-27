package cn.sisyphe.coffee.bill.domain.plan;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;

/**
 * @author ncmao
 * @Date 2017/12/25 18:14
 * @description
 */
public class PlanBillService extends AbstractBillService {

    /**
     * 构造方法
     *
     * @param bill
     */
    public PlanBillService(Bill bill) {
        super(bill);
    }

    @Override
    public void beforeDispose() {

    }

    @Override
    public void afterDispose() {

    }
}
