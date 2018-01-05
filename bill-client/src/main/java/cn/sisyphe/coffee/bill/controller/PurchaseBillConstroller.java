package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.purchase.PurchaseBillManager;
import cn.sisyphe.coffee.bill.viewmodel.ConditionQueryPurchaseBill;
import cn.sisyphe.coffee.bill.viewmodel.purchase.AddPurchaseBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.purchase.QueryOnePurchaseBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.purchase.QueryPurchaseBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/bill/purchase")
@RestController
@Api(description = "进货入库单相关接口")
@CrossOrigin(origins = "*")
public class PurchaseBillConstroller {
    @Autowired
    private PurchaseBillManager purchaseBillManager;

    /**
     * 保存进货单
     *
     * @param addPurchaseBillDTO
     * @return
     */
    @ApiOperation(value = "保存进货单")
    @RequestMapping(path = "/savePurchaseBill", method = RequestMethod.POST)
    public ResponseResult savePurchaseBill(@RequestBody AddPurchaseBillDTO addPurchaseBillDTO) {
        ResponseResult responseResult = new ResponseResult();
        purchaseBillManager.saveBill(addPurchaseBillDTO);
        return responseResult;
    }

    /**
     * 提交进货单
     *
     * @param addPurchaseBillDTO
     * @return
     */
    @ApiOperation(value = "提交进货单")
    @RequestMapping(path = "/submitPurchaseBill", method = RequestMethod.POST)
    public ResponseResult submitPurchaseBill(@RequestBody AddPurchaseBillDTO addPurchaseBillDTO) {
        ResponseResult responseResult = new ResponseResult();
        purchaseBillManager.submitBill(addPurchaseBillDTO);
        return responseResult;
    }

    /**
     * 多条件查询进货单
     *
     * @param conditionQueryPurchaseBill
     * @return
     */
    @ApiOperation(value = "多条件查询进货单")
    @RequestMapping(path = "/findByConditions", method = RequestMethod.POST)
    public ResponseResult findByConditions(@RequestBody ConditionQueryPurchaseBill conditionQueryPurchaseBill) {
        ResponseResult responseResult = new ResponseResult();

        QueryPurchaseBillDTO billPage = purchaseBillManager.findByConditions(conditionQueryPurchaseBill);
        responseResult.put("content", billPage);
        return responseResult;
    }

    /**
     * 根据进货单编码查询进货单详细信息
     *
     * @param purchaseBillCode 进货单编码
     * @return
     */
    @ApiOperation(value = "根据进货单编码查询进货单详细信息")
    @RequestMapping(path = "/findByPurchaseBillCode", method = RequestMethod.GET)
    public ResponseResult findByPurchaseBillCode(@RequestParam(value = "purchaseBillCode") String purchaseBillCode) {
        ResponseResult responseResult = new ResponseResult();
        QueryOnePurchaseBillDTO billDTO = purchaseBillManager.openBill(purchaseBillCode);
        responseResult.put("purchaseBill", billDTO);
        return responseResult;
    }

    /**
     * 修改进货单据信息
     *
     * @param billDTO
     * @return
     */
    @ApiOperation(value = "修改进货单据信息--保存")
    @RequestMapping(path = "/updatePurchaseBillToSave", method = RequestMethod.POST)
    public ResponseResult updatePurchaseBillToSaved(@RequestBody AddPurchaseBillDTO billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            purchaseBillManager.updateBillToSave(billDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 修改进货单据信息
     *
     * @param billDTO
     * @return
     */
    @ApiOperation(value = "修改进货单据信息--提交审核")
    @RequestMapping(path = "/updatePurchaseBillToSubmit", method = RequestMethod.POST)
    public ResponseResult updatePurchaseBillToSubmit(@RequestBody AddPurchaseBillDTO billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            purchaseBillManager.updateBillToSubmit(billDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 审核不通过
     *
     * @param purchaseBillCode 进货单编码
     * @return
     */
    @ApiOperation(value = "审核不通过")
    @RequestMapping(path = "/auditFailure", method = RequestMethod.POST)
    public ResponseResult auditFailure(@RequestParam String purchaseBillCode, @RequestParam String auditPersonCode) {
        ResponseResult responseResult = new ResponseResult();
        purchaseBillManager.auditBill(purchaseBillCode, auditPersonCode, true);
        return responseResult;
    }

    /**
     * 审核通过
     *
     * @param purchaseBillCode 进货单编码
     * @return
     */
    @ApiOperation(value = "审核通过")
    @RequestMapping(path = "/auditSuccess", method = RequestMethod.POST)
    public ResponseResult auditSuccess(@RequestParam String purchaseBillCode, @RequestParam String auditPersonCode) {
        ResponseResult responseResult = new ResponseResult();
        purchaseBillManager.auditBill(purchaseBillCode, auditPersonCode, false);
        return responseResult;
    }
}
