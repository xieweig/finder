package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.AbstractGoods;
import cn.sisyphe.coffee.bill.domain.plan.ItemPayload;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillDetail;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ncmao
 * @Date 2017/12/28 12:24
 * @description
 */
public abstract class AbstractCastableStrategy {


    @SuppressWarnings("unchecked")
    public abstract void cast(ItemPayload itemPayload, BillRepository billRepository);


    protected PlanBill generatePlanBill(ItemPayload itemPayload, BillTypeEnum billTypeEnum, BillPurposeEnum billPurposeEnum) {
        PlanBill planBill = (PlanBill) new BillFactory().createBill(BillTypeEnum.PLAN);
        planBill.setSpecificBillType(billTypeEnum);
        planBill.setInLocation(itemPayload.getInLocation());
        planBill.setOutLocation(itemPayload.getOutLocation());
        planBill.setBillPurpose(billPurposeEnum);
        Set<PlanBillDetail> planBillDetails = new HashSet<>();
        for (AbstractGoods goods : itemPayload.getGoods()) {
            PlanBillDetail planBillDetail = new PlanBillDetail();
            planBillDetail.setGoods(goods);
            planBillDetails.add(planBillDetail);
        }
        planBill.setBillDetails(planBillDetails);
        return planBill;
    }
}
