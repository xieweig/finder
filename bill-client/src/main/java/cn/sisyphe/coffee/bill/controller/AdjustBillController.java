package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂业务控制层
 * version: 1.0
 *
 * @author XiongJing
 */
@RestController
@RequestMapping("/api/bill/adjust")
@Api(description = "调剂计划相关接口")
@CrossOrigin(origins = "*")
public class AdjustBillController extends BillController<AdjustBill, AdjustBillDTO, ConditionQueryAdjustBill> {


    @Autowired
    public AdjustBillController(AbstractBillExtraManager<AdjustBill, AdjustBillDTO, ConditionQueryAdjustBill> abstractBillExtraManager) {
        super(abstractBillExtraManager);
    }
}
