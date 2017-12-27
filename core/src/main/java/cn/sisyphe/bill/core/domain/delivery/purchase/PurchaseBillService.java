package cn.sisyphe.bill.core.domain.delivery.purchase;

import cn.sisyphe.bill.core.domain.base.AbstractBillService;
import cn.sisyphe.bill.core.domain.base.model.Bill;

/**
 * @author heyong
 */
public class PurchaseBillService extends AbstractBillService {

    public PurchaseBillService(Bill bill) {
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
