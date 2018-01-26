package cn.sisyphe.coffee.bill.controller;


import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBillDetail;
import cn.sisyphe.coffee.bill.viewmodel.delivery.ConditionQueryDeliveryBill;
import cn.sisyphe.coffee.bill.viewmodel.delivery.DeliveryBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.delivery.DeliveryBillDetailDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配送单
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/4 10:25
 **/
@RestController
@RequestMapping("/api/bill/delivery")
@Api(description = "配送相关接口")
@CrossOrigin(origins = "*")
public class DeliveryBillController extends BillController<DeliveryBill, DeliveryBillDTO, ConditionQueryDeliveryBill, DeliveryBillDetailDTO> {

    @Autowired
    public DeliveryBillController(AbstractBillExtraManager<DeliveryBill, DeliveryBillDTO, ConditionQueryDeliveryBill, DeliveryBillDetailDTO> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }
}
