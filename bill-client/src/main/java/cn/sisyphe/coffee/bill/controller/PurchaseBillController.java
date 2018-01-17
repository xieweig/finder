package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.share.storage.TempStorage;
import cn.sisyphe.coffee.bill.viewmodel.purchase.ConditionQueryPurchaseBill;
import cn.sisyphe.coffee.bill.viewmodel.purchase.PurchaseBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author heyong
 */
@RequestMapping("/api/bill/purchase")
@RestController
@Api(description = "进货入库单相关接口")
@CrossOrigin(origins = "*")
public class PurchaseBillController extends BillController<PurchaseBill, PurchaseBillDTO, ConditionQueryPurchaseBill> {


    @Autowired
    public PurchaseBillController(AbstractBillExtraManager<PurchaseBill, PurchaseBillDTO, ConditionQueryPurchaseBill> abstractBillExtraManager) {
        super(abstractBillExtraManager);
    }



}
