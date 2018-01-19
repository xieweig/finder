package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.inoutself.model.InOutSelfBill;
import cn.sisyphe.coffee.bill.viewmodel.inoutself.ConditionQueryInOutSelfBill;
import cn.sisyphe.coffee.bill.viewmodel.inoutself.InOutSelfBillDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2018/1/19
 * @description: 其它出入库
 * @author：mayupeng
 */
@RequestMapping("/api/bill/inOutSelf")
@RestController
@CrossOrigin(origins = "*")
@Api(description = "其它出入库业相关操作")
public class InOutSelfBillController extends BillController<InOutSelfBill, InOutSelfBillDTO, ConditionQueryInOutSelfBill> {

    @Autowired
    public InOutSelfBillController(AbstractBillExtraManager<InOutSelfBill, InOutSelfBillDTO, ConditionQueryInOutSelfBill> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }
}
