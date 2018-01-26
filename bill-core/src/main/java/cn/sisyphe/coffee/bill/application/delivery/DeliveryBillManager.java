package cn.sisyphe.coffee.bill.application.delivery;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillExtraService;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.delivery.ConditionQueryDeliveryBill;
import cn.sisyphe.coffee.bill.viewmodel.delivery.DeliveryBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.delivery.DeliveryBillDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 * @date 2018/1/4
 */
@Service
public class DeliveryBillManager extends AbstractBillExtraManager<DeliveryBill, DeliveryBillDTO, ConditionQueryDeliveryBill,DeliveryBillDetailDTO> {


    @Autowired
    public DeliveryBillManager(BillRepository<DeliveryBill> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<DeliveryBill, ConditionQueryDeliveryBill> billExtraService, SharedManager sharedManager, PlanBillManager planBillManager) {
        super(billRepository, applicationEventPublisher, billExtraService, sharedManager,planBillManager);
    }

    /**
     * 单据类型
     *
     * @return
     */
    @Override
    public BillTypeEnum billType() {
        return BillTypeEnum.DELIVERY;
    }
}