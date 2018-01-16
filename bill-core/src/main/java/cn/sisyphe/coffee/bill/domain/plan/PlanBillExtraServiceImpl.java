package cn.sisyphe.coffee.bill.domain.plan;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.enums.OperationStateEnum;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PlanBillExtraServiceImpl extends AbstractBillExtraService<PlanBill, ConditionQueryPlanBill> implements PlanBillExtraService {


    @Autowired
    public PlanBillExtraServiceImpl(BillRepository<PlanBill> billRepository) {
        super(billRepository);
    }

    @Override
    public void updateOperationStateByBill(PlanBill planBill, OperationStateEnum operationState) {
        planBill.setOperationState(operationState);
        getBillRepository().save(planBill);
    }

    @Override
    public Page<PlanBill> findChildPlanBillBy(ConditionQueryPlanBill conditionQueryPlanBill) {
        return null;
    }

    @Override
    public PlanBill findByBillCodeAndType(String billCode, BillTypeEnum billType) {
        return ((PlanBillRepository) getBillRepository()).findByBillCodeAndType(billCode, billType);
    }


}
