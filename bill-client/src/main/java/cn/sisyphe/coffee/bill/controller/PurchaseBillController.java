package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBillDetail;
import cn.sisyphe.coffee.bill.viewmodel.purchase.ConditionQueryPurchaseBill;
import cn.sisyphe.coffee.bill.viewmodel.purchase.PurchaseBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.purchase.PurchaseBillDetailDTO;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyong
 */
@RequestMapping("/api/bill/purchase")
@RestController
@Api(description = "进货相关接口")
@CrossOrigin(origins = "*")
public class PurchaseBillController extends BillController<PurchaseBill, PurchaseBillDTO, ConditionQueryPurchaseBill, PurchaseBillDetailDTO> {

    @Autowired
    public PurchaseBillController(AbstractBillExtraManager<PurchaseBill, PurchaseBillDTO, ConditionQueryPurchaseBill, PurchaseBillDetailDTO> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }


    /**
     * 多条件查询进货列表
     *
     * @param conditionQueryPurchaseBill
     * @return
     */
    @ApiOperation(value = "多条件查询进货列表")
    @RequestMapping(path = "/findPurchaseByConditions", method = RequestMethod.POST)
    public ResponseResult findPurchaseByConditions(@RequestBody ConditionQueryPurchaseBill conditionQueryPurchaseBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            Page<PurchaseBillDTO> billDTOPage = abstractBillExtraManager.findBillByCondition(conditionQueryPurchaseBill, BillPurposeEnum.IN_STORAGE);
            responseResult.put("purchaseList", billDTOPage);
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 查询单个进货单明细--修改货物资料
     */
    @ApiOperation(value = "查询单个进货单明细--修改货物资料")
    @RequestMapping(path = "/findPurchaseBill", method = RequestMethod.GET)
    public ResponseResult findPurchaseBill(@RequestParam(value = "billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            PurchaseBillDTO purchaseBillDTO = abstractBillExtraManager.findBillDtoByBillCode(billCode);
            responseResult.put("bill", purchaseBillDTO);
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

}
