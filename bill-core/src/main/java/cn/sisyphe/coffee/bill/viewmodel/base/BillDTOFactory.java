package cn.sisyphe.coffee.bill.viewmodel.base;

import ch.lambdaj.function.closure.Switcher;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;

import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.delivery.DeliveryBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.delivery.DeliveryBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.mistake.MistakeBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.mistake.MistakeBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.PlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.PlanBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.purchase.PurchaseBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.purchase.PurchaseBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDetailDTO;

/**
 * Created by heyong on 2017/12/25 18:21
 * Description: 单据工厂类
 *
 * @author heyong
 */
public class BillDTOFactory {

    /**
     * 创建单据
     *
     * @param billType
     * @return
     */
    public BillDTO createBillDTO(BillTypeEnum billType) {
        Switcher<BillDTO> switcher = new Switcher<BillDTO>()
                .addCase(BillTypeEnum.PLAN, new PlanBillDTO())
                .addCase(BillTypeEnum.PURCHASE, new PurchaseBillDTO())
                .addCase(BillTypeEnum.DELIVERY, new DeliveryBillDTO())
                .addCase(BillTypeEnum.RETURNED, new ReturnedBillDTO())
                .addCase(BillTypeEnum.RESTOCK, new RestockBillDTO())
                .addCase(BillTypeEnum.ADJUST, new AdjustBillDTO())
                .addCase(BillTypeEnum.ALLOT, new AllotBillDTO())
                .addCase(BillTypeEnum.MISTAKE, new MistakeBillDTO());

        return switcher.exec(billType);
    }

    public BillDetailDTO createBillDetailDTO(BillTypeEnum billType) {

        Switcher<BillDetailDTO> switcher = new Switcher<BillDetailDTO>()
                .addCase(BillTypeEnum.PLAN, new PlanBillDetailDTO())
                .addCase(BillTypeEnum.PURCHASE, new PurchaseBillDetailDTO())
                .addCase(BillTypeEnum.DELIVERY, new DeliveryBillDetailDTO())
                .addCase(BillTypeEnum.RETURNED, new ReturnedBillDetailDTO())
                .addCase(BillTypeEnum.RESTOCK, new RestockBillDetailDTO())
                .addCase(BillTypeEnum.ADJUST, new AdjustBillDetailDTO())
                .addCase(BillTypeEnum.ALLOT, new AllotBillDetailDTO())
                .addCase(BillTypeEnum.MISTAKE, new MistakeBillDetailDTO());

        return switcher.exec(billType);
    }

}
