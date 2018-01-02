package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayload;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.stereotype.Service;

/**
 * @author ncmao
 *         计划单转成退库单策略
 */

@Service
public class RestockStrategy extends AbstractCastableStrategy {
    @SuppressWarnings("unchecked")
    @Override
    public void cast(PlanBillPayload planBillPayload, BillRepository billRepository) {
        PlanBill planBill = generatePlanBill(planBillPayload, BillTypeEnum.RESTOCK, BillPurposeEnum.Plan);
        billRepository.save(planBill);
    }
}
