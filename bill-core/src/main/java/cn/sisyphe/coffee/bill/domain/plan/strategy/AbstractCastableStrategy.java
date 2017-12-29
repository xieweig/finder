package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayload;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayloadDetail;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;

import java.util.HashSet;
import java.util.Set;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum.Plan;

/**
 * @author ncmao
 * @Date 2017/12/28 12:24
 * @description
 */
public abstract class AbstractCastableStrategy {


    @SuppressWarnings("unchecked")
    public abstract void cast(PlanBillPayload planBillPayload, BillRepository billRepository);


    PlanBill generatePlanBill(PlanBillPayload planBillPayload, BillTypeEnum billTypeEnum, Executor executor) {
        PlanBill planBill = (PlanBill) new BillFactory().createBill(BillTypeEnum.PLAN);
        planBill.setHqBill(false);
        planBill.setSpecificBillType(billTypeEnum);
        planBill.setInLocation(planBillPayload.getInLocation());
        planBill.setOutLocation(planBillPayload.getOutLocation());
        planBill.setBillPurpose(Plan);
        planBill.setBasicEnum(planBillPayload.getBasicEnum());
        Set<PlanBillDetail> planBillDetails = new HashSet<>();
        for (PlanBillPayloadDetail planBillPayloadDetail : planBillPayload.getGoodDetails()) {
            PlanBillDetail planBillDetail = new PlanBillDetail();
            planBillDetail.setGoods(planBillPayloadDetail.getGoods());
            planBillDetail.setAmount(planBillPayloadDetail.getAmount());
            planBillDetails.add(planBillDetail);
        }
        planBill.setBillDetails(planBillDetails);
        planBill.setBillState(BillStateEnum.SAVED);
        executor.exec(planBill);
        return planBill;
    }

    interface Executor {
        void exec(PlanBill planBill);
    }
}
