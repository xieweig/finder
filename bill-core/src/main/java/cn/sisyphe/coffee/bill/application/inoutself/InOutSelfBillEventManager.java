package cn.sisyphe.coffee.bill.application.inoutself;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.inoutself.model.InOutSelfBill;
import cn.sisyphe.coffee.bill.domain.inoutself.model.InOutSelfBillDetail;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.inoutself.ConditionQueryInOutSelfBill;
import cn.sisyphe.coffee.bill.viewmodel.inoutself.InOutSelfBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.inoutself.InOutSelfBillDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @date: 2018/1/19
 * @description: 其它出入库
 * @author：mayupeng
 */
@Service
public class InOutSelfBillEventManager extends AbstractBillExtraManager<InOutSelfBill, InOutSelfBillDTO, ConditionQueryInOutSelfBill,InOutSelfBillDetailDTO> {
    @Autowired
    public InOutSelfBillEventManager(BillRepository<InOutSelfBill> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<InOutSelfBill, ConditionQueryInOutSelfBill> billExtraService, SharedManager sharedManager, PlanBillManager planBillManager) {
        super(billRepository, applicationEventPublisher, billExtraService, sharedManager,planBillManager);
    }

    /**
     * 单据类型
     *
     * @return
     */
    @Override
    public BillTypeEnum billType() {
        return BillTypeEnum.IN_OUT_SELF_BILL;
    }
}
