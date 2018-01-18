package cn.sisyphe.coffee.bill.application.allot;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.mistake.MistakeBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 调拨单管理
 *
 * @author ncmao
 */
@Service
public class AllotBillManager extends AbstractBillExtraManager<AllotBill, AllotBillDTO, ConditionQueryAllotBill> {


    @Autowired
    public AllotBillManager(BillRepository<AllotBill> billRepository, ApplicationEventPublisher applicationEventPublisher, BillExtraService<AllotBill, ConditionQueryAllotBill> billExtraService, SharedManager sharedManager) {
        super(billRepository, applicationEventPublisher, billExtraService, sharedManager);
    }
    @Autowired
    private MistakeBillManager mistakeBillManager;

    /**
     * 单据类型
     *
     * @return
     */
    @Override
    public BillTypeEnum billType() {
        return BillTypeEnum.ALLOT;
    }


    @Override
    public AllotBillDTO saveBill(AllotBillDTO billDTO) {
        AllotBill allotBill = prepareBill(billDTO);
        allotBill = dtoToBill(allotBill, billDTO);
        //生成误差单
        MistakeBill mistakeBill = mistakeBillManager.submitByAllotBill(allotBill);
        allotBill.setMistakeBill(mistakeBill);

        allotBill.setMistakeBillCode(mistakeBill.getBillCode());
        allotBill.setBillState(BillStateEnum.AUDIT_SUCCESS);
        purpose(allotBill);

        return billToDto(allotBill);
    }


}

