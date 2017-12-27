package cn.sisyphe.coffee.bill.domain.base.purpose;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;

/**
 * Created by heyong on 2017/12/23 14:36
 * Description:
 * @author heyong
 */
public abstract class AbstractBillPurpose implements BillPurpose{

    private AbstractBillService billService;

    public AbstractBillPurpose(AbstractBillService billService) {
        this.billService = billService;
    }

    AbstractBillPurpose(){}

    public AbstractBillService getBillService() {
        return billService;
    }

    @Override
    public void setBillService(AbstractBillService billService) {
        this.billService = billService;
    }
}
