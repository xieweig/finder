package cn.sisyphe.coffee.bill.domain.delivery;


import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;

/**
 * Created by heyong on 2017/12/19 17:34
 * Description: 配送单服务
 * @author heyong
 */
public class DeliveryBillService extends AbstractBillService {

    public DeliveryBillService(Bill bill) {
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
