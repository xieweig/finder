package cn.sisyphe.coffee.bill.application.adjust;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
import cn.sisyphe.coffee.bill.viewmodel.plan.child.ChildPlanBillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum.selfPickGoods;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂业务
 * version: 1.0
 *
 * @author XiongJing
 */

@Service
public class AdjustBillManager extends AbstractBillExtraManager<AdjustBill, AdjustBillDTO, ConditionQueryAdjustBill, AdjustBillDetailDTO> {

/*

    @Autowired
    private PlanBillManager planBillManager;
*/

    @Autowired
    public AdjustBillManager(BillRepository<AdjustBill> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<AdjustBill, ConditionQueryAdjustBill> billExtraService, SharedManager sharedManager, PlanBillManager planBillManager) {
        super(billRepository, applicationEventPublisher, billExtraService, sharedManager, planBillManager);
    }

    /**
     * 单据类型
     *
     * @return
     */
    @Override
    public BillTypeEnum billType() {
        return BillTypeEnum.ADJUST;
    }

    @Override
    protected AdjustBillDTO billToDto(AdjustBill bill) {

        AdjustBillDTO adjustBillDTO = super.billToDto(bill);
        if (BasicEnum.BY_MATERIAL.equals(bill.getBasicEnum()) && !selfPickGoods(bill.getSpecificBillType())) {
            ChildPlanBillDTO childPlanBillDTO = planBillManager.findChildPlanBillByBillCode(bill.getSourceCode(), BillTypeEnum.ADJUST);
            if (childPlanBillDTO != null) {
                adjustBillDTO.setChildPlanBillDetailDTOS(childPlanBillDTO.getChildPlanBillDetails());
            }
        }
        return adjustBillDTO;
    }
}
