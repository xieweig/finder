package cn.sisyphe.coffee.bill.domain.headquarters;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;

/**
 * @author ncmao
 * @Date 2017/12/25 18:14
 * @description
 */
public class HeadQuarterBillService extends AbstractBillService {

    /**
     * 构造方法
     *
     * @param bill
     */
    public HeadQuarterBillService(Bill bill) {
        super(bill);
    }

    @Override
    public void beforeDispose() {

    }

    @Override
    public void afterDispose() {

    }
}
