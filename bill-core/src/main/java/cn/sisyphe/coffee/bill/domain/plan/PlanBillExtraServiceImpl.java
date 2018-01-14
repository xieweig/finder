package cn.sisyphe.coffee.bill.domain.plan;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.db.DbGoods;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.enums.OperationStateEnum;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBillDetail;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.user.repo.UserRepository;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PlanBillExtraServiceImpl extends AbstractBillExtraService<PlanBill> implements PlanBillExtraService {


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
