package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import cn.sisyphe.coffee.bill.viewmodel.mistake.ConditionQueryMistakeBill;
import cn.sisyphe.coffee.bill.viewmodel.mistake.MistakeBillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Amy on 2018/1/18.
 *         describe：流转误差单相关接口
 */
@RestController
@RequestMapping("/api/bill/mistake")
@CrossOrigin(origins = "*")
public class MistakeBillController extends BillController<MistakeBill, MistakeBillDTO, ConditionQueryMistakeBill> {

    @Autowired
    public MistakeBillController(AbstractBillExtraManager<MistakeBill, MistakeBillDTO, ConditionQueryMistakeBill> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }


}
