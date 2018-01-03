package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayload;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author ncmao
 *         计划单-调剂转成调剂计划单策略
 */

@Service
public class AdjustToAdjustStrategy extends AbstractCastableStrategy {

    @SuppressWarnings("unchecked")
    @Override
    public List<PlanBill> cast(PlanBillPayload planBillPayload) {
        PlanBill adjustBill = generatePlanBill(planBillPayload, BillTypeEnum.ADJUST, planBill -> {
        });
        return Collections.singletonList(adjustBill);
    }
}