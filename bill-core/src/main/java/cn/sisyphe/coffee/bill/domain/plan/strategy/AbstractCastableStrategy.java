package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayload;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayloadDetail;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum.UN_REVIEWED;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum.PLAN;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum.UNCOMMITTED;

/**
 * @author ncmao
 * @Date 2017/12/28 12:24
 * @description
 */
public abstract class AbstractCastableStrategy {


    @SuppressWarnings("unchecked")
    public abstract List<PlanBill> cast(PlanBillPayload planBillPayload);


    PlanBill generatePlanBill(PlanBillPayload planBillPayload, BillTypeEnum billTypeEnum, Executor executor) {
        PlanBill planBill = (PlanBill) new BillFactory().createBill(BillTypeEnum.PLAN);
        planBill.setHqBill(false);
        planBill.setSpecificBillType(billTypeEnum);
        planBill.setInLocation(planBillPayload.getInLocation());
        planBill.setOutLocation(planBillPayload.getOutLocation());
        planBill.setBillPurpose(PLAN);
        planBill.setOperatorCode(planBillPayload.getOperatorCode());
        planBill.setBasicEnum(planBillPayload.getBasicEnum());
        planBill.setRootCode(planBillPayload.getParentBillCode());
        planBill.setSourceCode(planBillPayload.getParentBillCode());
        planBill.setBillCode(planBillPayload.getBillCode());
        planBill.setPlanMemo(planBillPayload.getMemo());
        Set<PlanBillDetail> planBillDetails = new HashSet<>();
        for (PlanBillPayloadDetail planBillPayloadDetail : planBillPayload.getGoodDetails()) {
            PlanBillDetail planBillDetail = new PlanBillDetail();
            planBillDetail.setGoods(planBillPayloadDetail.getGoods());
            planBillDetail.setShippedAmount(planBillPayloadDetail.getAmount());
            planBillDetails.add(planBillDetail);
        }
        planBill.setBillDetails(planBillDetails);
        planBill.setBillState(BillStateEnum.SAVED);
        planBill.setSubmitState(UNCOMMITTED);
        planBill.setAuditState(UN_REVIEWED);
        executor.exec(planBill);
        return planBill;
    }

    interface Executor {
        void exec(PlanBill planBill);
    }
}
