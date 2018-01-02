package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.payload.PlanBillPayload;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;

/**
 * @author ncmao
 * 计划单转成配送单策略
 */

public class DeliveryStrategy extends AbstractCastableStrategy {


    /**
     * 将计划单切分为配送单
     *
     * @param planBillPayload 计划单
     * @param billRepository  仓库
     */


    @SuppressWarnings("unchecked")
    @Override
    public void cast(PlanBillPayload planBillPayload, BillRepository billRepository) {
        PlanBill planBill = generatePlanBill(planBillPayload, BillTypeEnum.DELIVERY, planBill1 -> {
        });
        billRepository.save(planBill);
    }
}
