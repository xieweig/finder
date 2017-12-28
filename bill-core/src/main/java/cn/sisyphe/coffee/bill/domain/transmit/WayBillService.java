package cn.sisyphe.coffee.bill.domain.transmit;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;


/**
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2017/12/27 16:57
 **/
public class WayBillService extends AbstractBillService {


    public WayBillService(Bill bill) {
        super(bill);
    }



    @Override
    public void beforeDispose() {

    }

    @Override
    public void afterDispose() {

    }
}
