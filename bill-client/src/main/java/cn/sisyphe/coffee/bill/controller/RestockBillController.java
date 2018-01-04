package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.viewmodel.ConditionQueryPurchaseBill;
import cn.sisyphe.coffee.bill.viewmodel.purchase.QueryPurchaseBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.*;
import cn.sisyphe.framework.web.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 *@date: 2018/1/3
 *@description:
 *@author：xieweiguang
 */
@RequestMapping("/api/bill/restock")
@RestController
@CrossOrigin(origins = "*")
@Api(description = "退库相关操作")
public class RestockBillController {
    @Resource
    private RestockBillManager restockBillManager;

/**
 *
 *notes :
 *
 */
    @ApiOperation(value = "站点自主退库拣货保存")
    @PostMapping(path ="/saveBySelf")
    public ResponseResult saveBySelf(@RequestBody SaveBySelfDTO saveBySelfDTO) {

        ResponseResult responseResult = new ResponseResult();
//        restockBillManager.saveBySelf(saveBySelfDTO);
        return responseResult;
    }


    @ApiOperation(value = "站点计划退库按货物拣货第一步保存")
    @PostMapping(path ="/saveByCargo")
    public ResponseResult saveByCargo(@RequestBody SaveByRestockBillDTO saveByCargoDTO) {

        ResponseResult responseResult = new ResponseResult();
        saveByCargoDTO.setReadyByCargo(true);
//        restockBillManager.saveByRestockBill(saveByCargoDTO);
        return responseResult;
    }


    @ApiOperation(value = "站点计划退库按原料拣货第一步保存")
    @PostMapping(path ="/saveByRawMaterial")
    public ResponseResult saveByRawMaterial(@RequestBody SaveByRestockBillDTO saveByRawMaterialDTO) {

        ResponseResult responseResult = new ResponseResult();
        saveByRawMaterialDTO.setReadyByCargo(false);
//        restockBillManager.saveByRestockBill(saveByRawMaterialDTO);
        return responseResult;
    }
    @ApiOperation(value = "站点计划退库按货物拣货第二步提交")
    @PostMapping(path ="/submitByCargo")
    public ResponseResult submitByCargo(@RequestBody SaveByRestockBillDTO saveByRawMaterialDTO) {

        ResponseResult responseResult = new ResponseResult();
        saveByRawMaterialDTO.setReadyByCargo(true);
//        restockBillManager.submitByRestockBill(saveByRawMaterialDTO);
        return responseResult;
    }
    @ApiOperation(value = "站点计划退库按原料拣货第二步提交")
    @PostMapping(path ="/submitByRawMaterial")
    public ResponseResult submitByRawMaterial(@RequestBody SaveByRestockBillDTO saveByRawMaterialDTO) {

        ResponseResult responseResult = new ResponseResult();
        saveByRawMaterialDTO.setReadyByCargo(false);
//        restockBillManager.submitByRestockBill(saveByRawMaterialDTO);
        return responseResult;
    }


    @ApiOperation(value = "可退库货物查询")
    @PostMapping(path ="/queryCargo")
    public ResponseResult queryCargo(@RequestParam String cargoName, @RequestParam String cargoCode){
        ResponseResult responseResult = new ResponseResult();
        restockBillManager.queryCargo(cargoName, cargoCode);

        return responseResult;
    }
    /**
     * 多条件查询退库入库单
     *
     * @param conditionQueryPurchaseBill
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
    @ApiOperation(value = "根据进货单编码查询图库出库单详细信息")
    @RequestMapping(path = "/findByRestockBillCode", method = RequestMethod.GET)
    public ResponseResult findByRestockBillCode(@RequestParam String RestockBillCode) {
        ResponseResult responseResult = new ResponseResult();
        QueryOneRestockBillDTO billDTO = RestockBillManager.openBill(RestockBillCode);
        responseResult.put("RestockBill", billDTO);
        return responseResult;
    }

}
