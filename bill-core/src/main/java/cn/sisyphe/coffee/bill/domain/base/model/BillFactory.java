package cn.sisyphe.coffee.bill.domain.base.model;

import ch.lambdaj.function.closure.Switcher;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBillDetail;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBill;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBillDetail;

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
        Switcher<Bill> switcher = new Switcher<Bill>()
                .addCase(BillTypeEnum.PLAN, new PlanBill())
                .addCase(BillTypeEnum.PURCHASE, new PurchaseBill())
                .addCase(BillTypeEnum.DELIVERY, new DeliveryBill())
                .addCase(BillTypeEnum.RETURNED, new ReturnedBill())
                .addCase(BillTypeEnum.RESTOCK, new RestockBill())
                .addCase(BillTypeEnum.ADJUST, new AdjustBill())
                .addCase(BillTypeEnum.ALLOT, new AllotBill());

        return switcher.exec(billType);
    }

    public BillDetail createBillDetail(BillTypeEnum billType) {

        Switcher<BillDetail> switcher = new Switcher<BillDetail>()
                .addCase(BillTypeEnum.PLAN, new PlanBillDetail())
                .addCase(BillTypeEnum.PURCHASE, new PurchaseBillDetail())
                .addCase(BillTypeEnum.DELIVERY, new DeliveryBillDetail())
                .addCase(BillTypeEnum.RETURNED, new ReturnedBillDetail())
                .addCase(BillTypeEnum.RESTOCK, new RestockBillDetail())
                .addCase(BillTypeEnum.ADJUST, new AdjustBillDetail())
                .addCase(BillTypeEnum.ALLOT, new AllotBillDetail());

        return switcher.exec(billType);
    }

}
