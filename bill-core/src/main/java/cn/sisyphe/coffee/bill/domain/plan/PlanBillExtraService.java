package cn.sisyphe.coffee.bill.domain.plan;

import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.enums.OperationStateEnum;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import org.springframework.data.domain.Page;

public interface PlanBillExtraService extends BillExtraService<PlanBill, ConditionQueryPlanBill> {

    /**
     * 修改拣货状态
     *
     * @param planBill 查询条件 operationState拣货状态
     * @return
     */
    void updateOperationStateByBill(PlanBill planBill, OperationStateEnum operationState);


    Page<PlanBill> findChildPlanBillBy(ConditionQueryPlanBill conditionQueryPlanBill);

    PlanBill findByBillCodeAndSpecificBillType(String billCode, BillTypeEnum billType);
}
