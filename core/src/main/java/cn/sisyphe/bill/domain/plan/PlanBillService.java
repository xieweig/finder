package cn.sisyphe.bill.domain.plan;


import cn.sisyphe.bill.domain.base.AbstractBillService;
import cn.sisyphe.bill.domain.base.model.Bill;

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

    //先保存单据
    @Override
    public void beforeDispose() {
        getBillRepository().save(getBill());
    }

    @Override
    public void afterDispose() {

    }
}
