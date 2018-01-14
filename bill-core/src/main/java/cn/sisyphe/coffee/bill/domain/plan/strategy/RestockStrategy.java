package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayload;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author ncmao
 * 计划单转成退库单策略
 */

@Service
public class RestockStrategy extends AbstractCastableStrategy {


    @SuppressWarnings("unchecked")
    @Override
    public List<PlanBill> cast(PlanBillPayload planBillPayload) {
        PlanBill planBill = generatePlanBill(planBillPayload, BillTypeEnum.RESTOCK, planBill1 -> {
        });
        return Collections.singletonList(planBill);
    }
}
