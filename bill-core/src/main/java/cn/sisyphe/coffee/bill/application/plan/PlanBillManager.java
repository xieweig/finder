package cn.sisyphe.coffee.bill.application.plan;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillExtraService;
import cn.sisyphe.coffee.bill.domain.plan.enums.OperationStateEnum;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.coffee.bill.viewmodel.planbill.PlanBillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 计划单据manager
 *
 * @author ncmao
 */
@Service
public class PlanBillManager extends AbstractBillExtraManager<PlanBill, PlanBillDTO, ConditionQueryPlanBill> {


    @Autowired
    public PlanBillManager(BillRepository<PlanBill> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<PlanBill, ConditionQueryPlanBill> billExtraService, PlanBillExtraService planBillExtraService, SharedManager sharedManager) {
        super(billRepository, applicationEventPublisher, billExtraService, planBillExtraService, sharedManager);
    }

    /**
     * 单据类型
     *
     * @return
     */
    @Override
    public BillTypeEnum billType() {
        return BillTypeEnum.PLAN;
    }

    /**
     * @param billCode
     * @param operationState
     */
    public void operationPickGood(String billCode, OperationStateEnum operationState) {
        PlanBill planBill = getBillExtraService().findByBillCode(billCode);

        if (planBill == null || OperationStateEnum.OPERATION.equals(planBill.getOperationState())) {
            return;
        }

        if (getBillExtraService() instanceof PlanBillExtraService) {
            ((PlanBillExtraService) getBillExtraService()).updateOperationStateByBill(planBill, operationState);
        }
    }
}
