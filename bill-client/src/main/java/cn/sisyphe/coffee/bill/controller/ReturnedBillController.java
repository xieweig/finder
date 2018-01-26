package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBill;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBillDetail;
import cn.sisyphe.coffee.bill.viewmodel.returned.ConditionQueryReturnedBill;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDetailDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2018/1/3
 * @description:
 * @author：bifenglin
 */
@RequestMapping("/api/bill/returned")
@RestController
@CrossOrigin(origins = "*")
@Api(description = "退货相关操作")
public class ReturnedBillController extends BillController<ReturnedBill, ReturnedBillDTO, ConditionQueryReturnedBill, ReturnedBillDetailDTO> {

    @Autowired
    public ReturnedBillController(AbstractBillExtraManager<ReturnedBill, ReturnedBillDTO, ConditionQueryReturnedBill, ReturnedBillDetailDTO> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }
}
