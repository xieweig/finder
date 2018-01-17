package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
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
@RequestMapping("/api/bill/restock")
@RestController
@CrossOrigin(origins = "*")
@Api(description = "退库相关操作")
public class RestockBillController extends BillController<RestockBill, RestockBillDTO, ConditionQueryRestockBill> {


    @Autowired
    public RestockBillController(AbstractBillExtraManager<RestockBill, RestockBillDTO, ConditionQueryRestockBill> abstractBillExtraManager) {
        super(abstractBillExtraManager);
    }
}
