package cn.sisyphe.coffee.bill.domain.base;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillService;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillService;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBillService;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillService;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBillService;
import org.springframework.stereotype.Service;

/**
 * Created by heyong on 2017/12/19 17:19
 * Description: 单据服务工厂类
 *
 * @author heyong
 */
@Service
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
            case RETURNED:
                billService = new ReturnedBillService(bill);
                break;
            case RESTOCK:
                billService = new RestockBillService(bill);
                break;
            default:
                break;
        }

        return billService;
    }

}
