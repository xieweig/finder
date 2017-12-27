package cn.sisyphe.coffee.bill.domain.returned;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;

/**
 * @author ncmao
 * @Date 2017/12/27 16:51
 * @description
 */
public class ReturnedBillService extends AbstractBillService {

    /**
     * 构造方法
     *
     * @param bill
     */
    public ReturnedBillService(Bill bill) {
        super(bill);
    }

    @Override
    public void beforeDispose() {

    }

    @Override
    public void afterDispose() {

    }
}
