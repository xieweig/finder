package cn.sisyphe.coffee.bill.domain.mistake;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;

/**
 * @author Amy on 2018/1/18.
 * describe：
 */
public class MistakeBillService extends AbstractBillService {
    /**
     * 构造方法
     *
     * @param bill
     */
    public MistakeBillService(Bill bill) {
        super(bill);
    }

    @Override
    public void beforeDispose() {

    }

    @Override
    public void afterDispose() {

    }
}
