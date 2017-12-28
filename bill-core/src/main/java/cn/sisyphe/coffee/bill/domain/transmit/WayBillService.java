package cn.sisyphe.coffee.bill.domain.transmit;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.infrastructure.transmit.WayBillRepository;


/**
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2017/12/27 16:57
 **/

public class WayBillService extends AbstractBillService {

    /**
     * 构造方法
     *
     * @param
     * @param bill
     * @param wayBillRepository
     */
    public WayBillService(Bill bill, WayBillRepository wayBillRepository) {
        super(bill);

    }

    @Override
    public void beforeDispose() {

    }

    @Override
    public void afterDispose() {

    }


}
