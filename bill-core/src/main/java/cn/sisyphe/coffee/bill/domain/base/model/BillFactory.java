package cn.sisyphe.coffee.bill.domain.base.model;

import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBill;

/**
 * Created by heyong on 2017/12/25 18:21
 * Description: 单据工厂类
 *
 * @author heyong
 */
public class BillFactory {

    /**
     * 创建单据
     *
     * @param billType
     * @return
     */
    public Bill createBill(BillTypeEnum billType) {
        Bill bill = null;

        switch (billType) {
            case PURCHASE:
                bill = new PurchaseBill();
                break;
            case DELIVERY:
                bill = new DeliveryBill();
                break;
            case PLAN:
                bill = new PlanBill();
                break;
            case RETURNED:
                bill = new ReturnedBill();
                break;
            case RESTOCK:
                bill = new RestockBill();
                break;
            case ADJUST:
                bill = new AdjustBill();
                break;
            case ALLOT:
                bill = new AllotBill();
                break;
            default:
                break;
        }

        return bill;
    }
}
