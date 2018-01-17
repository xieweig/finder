package cn.sisyphe.coffee.bill.application.returned;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillExtraService;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.returned.ConditionQueryReturnedBill;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


/**
 * @date: 2018/1/2
 * @description:
 * @author：xieweiguang
 */
@Service
public class ReturnedBillManager extends AbstractBillExtraManager<ReturnedBill, ReturnedBillDTO, ConditionQueryReturnedBill> {


    @Autowired
    public ReturnedBillManager(BillRepository<ReturnedBill> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<ReturnedBill, ConditionQueryReturnedBill> billExtraService, PlanBillExtraService planBillExtraService, SharedManager sharedManager) {
        super(billRepository, applicationEventPublisher, billExtraService, planBillExtraService, sharedManager);
    }

    /**
     * 单据类型
     *
     * @return
     */
    @Override
    public BillTypeEnum billType() {
        return BillTypeEnum.RETURNED;
    }
}
