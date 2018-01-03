package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayload;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.stereotype.Service;

/**
 * @author ncmao
 *         计划单-调剂转成配送计划单策略
 */

@Service
public class AdjustToDeliveryStrategy extends AbstractCastableStrategy {
    @SuppressWarnings("unchecked")
    @Override
    public void cast(PlanBillPayload planBillPayload, BillRepository billRepository) {
        PlanBill adjustBill = generatePlanBill(planBillPayload, BillTypeEnum.DELIVERY, planBill -> {
        });
        billRepository.save(adjustBill);
    }
}
