package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayload;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author ncmao
 * 计划单转成配送单策略
 */

public class DeliveryStrategy extends AbstractCastableStrategy {


    /**
     * 将计划单切分为配送单
     *
     * @param planBillPayload 计划单
     */


    @SuppressWarnings("unchecked")
    @Override
    public List<PlanBill> cast(PlanBillPayload planBillPayload) {
        PlanBill planBill = generatePlanBill(planBillPayload, BillTypeEnum.DELIVERY, planBill1 -> {
        });
        return Collections.singletonList(planBill);
    }
}
