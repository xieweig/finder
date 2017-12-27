package cn.sisyphe.bill.domain.base;


import cn.sisyphe.bill.domain.base.model.Bill;
import cn.sisyphe.bill.domain.delivery.DeliveryBillService;
import cn.sisyphe.bill.domain.delivery.purchase.PurchaseBillService;
import cn.sisyphe.bill.domain.plan.PlanBillService;

/**
 * Created by heyong on 2017/12/19 17:19
 * Description: 单据服务工厂类
 *
 * @author heyong
 */
public class BillServiceFactory {


    /**
     * 创建单据服务
     *
     * @param bill
     * @return
     */
    public AbstractBillService createBillService(Bill bill) {
        AbstractBillService billService = null;

        switch (bill.getBillType()) {
            case PURCHASE:
                billService = new PurchaseBillService(bill);
                break;
            case DELIVERY:
                billService = new DeliveryBillService(bill);
                break;
            case PLAN:
                billService = new PlanBillService(bill);
                break;
            default:
                break;
        }

        return billService;
    }

}
