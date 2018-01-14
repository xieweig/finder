package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayload;

import java.util.ArrayList;
import java.util.List;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum.RETURNED;


/**
 * @author ncmao
 * 退货计划单策略
 */

public class ReturnedStrategy extends AbstractCastableStrategy {

    @SuppressWarnings("unchecked")
    @Override
    public List<PlanBill> cast(PlanBillPayload planBillPayload) {
        List<PlanBill> planBills = new ArrayList<>();
        //出站点为物流则直接生成退货单
        AbstractLocation outLocation = planBillPayload.getOutLocation();
        if (outLocation instanceof Station && StationType.LOGISTICS.equals(((Station) outLocation).getStationType())) {
            PlanBill returnedBill = generatePlanBill(planBillPayload, RETURNED, planBill -> {
                planBill.setInLocation(planBillPayload.getInLocation());
                planBill.setOutLocation(planBillPayload.getOutLocation());
            });
            planBills.add(returnedBill);
        }
        //出站点为门店则先生成退库计划，再生成退货计划
        if (outLocation instanceof Station && StationType.STORE.equals(((Station) outLocation).getStationType())) {
            PlanBill restockBill = generatePlanBill(planBillPayload, BillTypeEnum.RESTOCK, planBill -> {
                planBill.setInLocation(planBillPayload.getTransferLocation());
                planBill.setOutLocation(planBillPayload.getInLocation());
                planBill.setBillCode(planBillPayload.getBillCode() + "_0");
            });

            PlanBill returnedBill = generatePlanBill(planBillPayload, BillTypeEnum.RETURNED, planBill -> {
                planBill.setInLocation(planBillPayload.getOutLocation());
                planBill.setOutLocation(planBillPayload.getTransferLocation());
                planBill.setBillCode(planBillPayload.getBillCode() + "_1");
            });
            planBills.add(restockBill);
            planBills.add(returnedBill);
        }
        return planBills;
    }


}
