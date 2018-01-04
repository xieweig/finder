package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.*;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *@date: 2018/1/3
 *@description:
 *@author：bifenglin
 */
@RequestMapping("/api/bill/restock")
@RestController
@CrossOrigin(origins = "*")
@Api(description = "退库相关操作")
public class RestockBillController {
    @Resource
    private RestockBillManager restockBillManager;

    /**
     * 保存退库单
     *
     * @param addRestockBillDTO
     * @return
     */
    @ApiOperation(value = "保存退库单")
    @RequestMapping(path = "/saveRestockBill", method = RequestMethod.POST)
    public ResponseResult saveRestockBill(@RequestBody AddRestockBillDTO addRestockBillDTO) {
        ResponseResult responseResult = new ResponseResult();
        restockBillManager.saveBill(addRestockBillDTO);
        return responseResult;
    }

    /**
     * 提交退库单
     *
     * @param addRestockBillDTO
     * @return
     */
    @ApiOperation(value = "提交退库单")
    @RequestMapping(path = "/submitRestockBill", method = RequestMethod.POST)
    public ResponseResult submitRestockBill(@RequestBody AddRestockBillDTO addRestockBillDTO) {
        ResponseResult responseResult = new ResponseResult();
        restockBillManager.submitBill(addRestockBillDTO);
        return responseResult;
    }

    /**
     * 多条件查询退库入库单
     *
     * @param conditionQueryRestockBill
     * @return
     */
    @ApiOperation(value = "多条件查询退库入库单")
    @RequestMapping(path = "/findByConditions", method = RequestMethod.POST)
    public ResponseResult findByConditions(@RequestBody ConditionQueryRestockBill conditionQueryRestockBill) {
        ResponseResult responseResult = new ResponseResult();

        QueryRestockBillDTO billPage = restockBillManager.findByConditions(conditionQueryRestockBill);
        responseResult.put("content", billPage);
        return responseResult;
    }
    /**
     *
     *
     * @param RestockBillCode
     * @return
     */
    @ApiOperation(value = "根据入库单编码查询图库出库单详细信息")
    @RequestMapping(path = "/findByRestockBillCode", method = RequestMethod.GET)
    public ResponseResult findByRestockBillCode(@RequestParam String RestockBillCode) {
        ResponseResult responseResult = new ResponseResult();
        QueryOneRestockBillDTO billDTO = restockBillManager.openBill(RestockBillCode);
        responseResult.put("RestockBill", billDTO);
        return responseResult;
    }

    /**
     * 修改入库单据信息
     *
     * @param billDTO
     * @return
     */
    @ApiOperation(value = "修改入库单据信息--保存")
    @RequestMapping(path = "/updateRestockBillToSave", method = RequestMethod.POST)
    public ResponseResult updateRestockBillToSaved(@RequestBody AddRestockBillDTO billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            restockBillManager.updateBillToSave(billDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 修改入库单据信息
     *
     * @param billDTO
     * @return
     */
    @ApiOperation(value = "修改入库单据信息--提交审核")
    @RequestMapping(path = "/updateRestockBillToSubmit", method = RequestMethod.POST)
    public ResponseResult updateRestockBillToSubmit(@RequestBody AddRestockBillDTO billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            restockBillManager.updateBillToSubmit(billDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 审核不通过
     *
     * @param RestockBillCode 入库单编码
     * @return
     */
    @ApiOperation(value = "审核不通过")
    @RequestMapping(path = "/auditFailure", method = RequestMethod.POST)
    public ResponseResult auditFailure(@RequestParam String RestockBillCode, @RequestParam String auditPersonCode) {
        ResponseResult responseResult = new ResponseResult();
        restockBillManager.auditBill(RestockBillCode, auditPersonCode, true);
        return responseResult;
    }

    /**
     * 审核通过
     *
     * @param RestockBillCode 入库单编码
     * @return
     */
    @ApiOperation(value = "审核通过")
    @RequestMapping(path = "/auditSuccess", method = RequestMethod.POST)
    public ResponseResult auditSuccess(@RequestParam String RestockBillCode, @RequestParam String auditPersonCode) {
        ResponseResult responseResult = new ResponseResult();
        restockBillManager.auditBill(RestockBillCode, auditPersonCode, false);
        return responseResult;
    }

}
