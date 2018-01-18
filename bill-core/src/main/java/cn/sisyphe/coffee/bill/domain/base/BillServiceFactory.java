package cn.sisyphe.coffee.bill.domain.base;

import ch.lambdaj.function.closure.Switcher;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillService;
import cn.sisyphe.coffee.bill.domain.allot.AllotBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillService;
import cn.sisyphe.coffee.bill.domain.mistake.MistakeBillService;
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

        Switcher<AbstractBillService> switcher = new Switcher<AbstractBillService>()
                .addCase(BillTypeEnum.PLAN, new PlanBillService(bill))
                .addCase(BillTypeEnum.PURCHASE, new PurchaseBillService(bill))
                .addCase(BillTypeEnum.DELIVERY, new DeliveryBillService(bill))
                .addCase(BillTypeEnum.RETURNED, new ReturnedBillService(bill))
                .addCase(BillTypeEnum.RESTOCK, new RestockBillService(bill))
                .addCase(BillTypeEnum.ADJUST, new AdjustBillService(bill))
                .addCase(BillTypeEnum.ALLOT, new AllotBillService(bill))
                .addCase(BillTypeEnum.MISTAKE, new MistakeBillService(bill));

        return switcher.exec(bill.getBillType());

    }

}
