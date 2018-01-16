package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heyong on 2018/1/16 14:20
 * Description:
 */
@RestController
@RequestMapping("/api/bill/wx")
@Api(description = "---调剂计划相关接口")
@CrossOrigin(origins = "*")
public class WaBillController extends BillController<AdjustBill, BillDTO, ConditionQueryAdjustBill>{


    @Autowired
    public WaBillController(AbstractBillExtraManager<AdjustBill, ConditionQueryAdjustBill> abstractBillExtraManager) {
        super(abstractBillExtraManager);
    }
}
