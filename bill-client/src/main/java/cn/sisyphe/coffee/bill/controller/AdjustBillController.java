package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
import cn.sisyphe.framework.web.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂业务控制层
 * version: 1.0
 *
 * @author XiongJing
 */
public class AdjustBillController {


    /**
     * 多条件查询调剂单据
     *
     * @param conditionQueryAdjustBill 查询条件
     * @return
     */
    @ApiOperation(value = "多条件查询调剂单据")
    @RequestMapping(path = "/findByConditions", method = RequestMethod.POST)
    public ResponseResult findByPurchaseBillCode(@RequestBody ConditionQueryAdjustBill conditionQueryAdjustBill) {
        ResponseResult responseResult = new ResponseResult();

        return responseResult;
    }

    /**
     * 根据调剂单号查询详细信息
     *
     * @param adjustBillCode 调剂单号
     * @return
     */
    @ApiOperation(value = "根据调剂单号查询详细信息")
    @RequestMapping(path = "/findByAdjustBillCode", method = RequestMethod.POST)
    public ResponseResult findByAdjustBillCode(@RequestParam String adjustBillCode) {
        ResponseResult responseResult = new ResponseResult();

        return responseResult;
    }

    @ApiOperation(value = "保存调剂单据信息")
    @RequestMapping(path = "/saveAdjustBill", method = RequestMethod.POST)
    public ResponseResult saveAdjustBill(@RequestParam String adjustBillCode) {
        ResponseResult responseResult = new ResponseResult();

        return responseResult;
    }

    @ApiOperation(value = "提交调剂单据信息")
    @RequestMapping(path = "/submitAdjustBill", method = RequestMethod.POST)
    public ResponseResult submitAdjustBill(@RequestParam String adjustBillCode) {
        ResponseResult responseResult = new ResponseResult();

        return responseResult;
    }

}
