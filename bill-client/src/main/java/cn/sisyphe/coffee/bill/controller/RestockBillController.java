package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.purchase.PurchaseBillManager;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.viewmodel.restock.SaveByCargoDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.SaveByRawMaterialDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.SaveBySelfDTO;
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
        restockBillManager.saveBySelf(saveBySelfDTO);
        return responseResult;
    }


    @ApiOperation(value = "站点计划退库按货物拣货")
    @PostMapping(path ="/saveByCargo")
    public ResponseResult saveByCargo(@RequestBody SaveByCargoDTO saveByCargoDTO) {

        ResponseResult responseResult = new ResponseResult();
        restockBillManager.saveByCargo(saveByCargoDTO);
        return responseResult;
    }


    @ApiOperation(value = "站点计划退库按原料拣货")
    @PostMapping(path ="/saveByRawMaterial")
    public ResponseResult saveByRawMaterial(@RequestBody SaveByRawMaterialDTO saveByRawMaterialDTO) {

        ResponseResult responseResult = new ResponseResult();
        restockBillManager.saveByRawMaterial(saveByRawMaterialDTO);
        return responseResult;
    }

    @ApiOperation(value = "可退库货物查询")
    @PostMapping(path ="/queryCargo")
    public ResponseResult queryCargo(@RequestParam String cargoName, @RequestParam String cargoCode){
        ResponseResult responseResult = new ResponseResult();
        restockBillManager.queryCargo(cargoName, cargoCode);

        return responseResult;
    }


}
