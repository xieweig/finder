package cn.sisyphe.coffee.bill.controller;


import cn.sisyphe.coffee.bill.application.deliverybill.DeliveryBillManager;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 配送单
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/4 10:25
 **/
@RestController
@RequestMapping("/api/bill/deliverybill")
@Api(description = "配送计划相关接口")
@CrossOrigin(origins = "*")
public class DeliveryBillController {


    @Autowired
    private DeliveryBillManager deliveryBillManager;


    @ApiOperation(value = "根据单据编号查询单据信息")
    @RequestMapping(path = "/findOneByBillCode", method = RequestMethod.GET)
    public ResponseResult findOneByBillCode(@RequestParam("billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("deliveryBill", deliveryBillManager.findOneByBillCode(billCode));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }
}
