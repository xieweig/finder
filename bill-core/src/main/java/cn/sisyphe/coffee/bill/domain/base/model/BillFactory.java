package cn.sisyphe.coffee.bill.domain.base.model;

import ch.lambdaj.function.closure.Switcher;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillService;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.domain.allot.AllotBillService;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.purpose.*;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillService;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillService;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBillService;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillService;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBillService;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBill;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum.*;

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
        return new Switcher<Bill>()
                .addCase(BillTypeEnum.PLAN, new PlanBill())
                .addCase(BillTypeEnum.PURCHASE, new PurchaseBill())
                .addCase(BillTypeEnum.DELIVERY, new DeliveryBill())
                .addCase(BillTypeEnum.ADJUST, new AdjustBill())
                .addCase(BillTypeEnum.RETURNED, new ReturnedBill())
                .addCase(BillTypeEnum.RESTOCK, new RestockBill())
                .addCase(BillTypeEnum.ALLOT, new AllotBill())
                .exec(billType);
    }
}
