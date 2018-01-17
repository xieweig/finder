package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bifenglin
 * @Date 2018/1/12
 * @description
 */
@RestController
@RequestMapping("/api/bill/allot")
@Api(description = "调拨相关接口")
@CrossOrigin(origins = "*")
public class AllotBillController extends BillController<AllotBill, AllotBillDTO, ConditionQueryAllotBill> {

    @Autowired
    public AllotBillController(AbstractBillExtraManager<AllotBill, AllotBillDTO, ConditionQueryAllotBill> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }
}
