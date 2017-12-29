package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.transmit.WayBillManager;
import cn.sisyphe.coffee.bill.viewmodel.waybill.ConditionQueryWayBill;
import cn.sisyphe.coffee.bill.viewmodel.waybill.EditWayBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 运单
 */

@RequestMapping("/api/bill/waybill")
@RestController
@CrossOrigin(origins = "*")
@Api(description = "运单信息相关操作")
public class WayBillController {

    @Autowired
    private WayBillManager wayBillManager;

    /**
     * 创建运单
     */
    @ApiOperation(value = "创建运单")
    @RequestMapping(path = "/createWayBill", method = RequestMethod.POST)
    public ResponseResult createWayBill(@RequestBody EditWayBillDTO editWayBillDTO) {

        ResponseResult responseResult = new ResponseResult();
        responseResult.put("wayBill", wayBillManager.createWayBillWithDTO(editWayBillDTO));
        return responseResult;

    }

    /**
     * 修改运单
     */
    @ApiOperation(value = "修改运单")
    @RequestMapping(path = "/updateWayBill", method = RequestMethod.POST)
    public ResponseResult updateWayBill(@RequestBody EditWayBillDTO editWayBillDTO) {
        ResponseResult responseResult = new ResponseResult();

        responseResult.put("wayBill", wayBillManager.updateWayBillWithDTO(editWayBillDTO));

        return responseResult;
    }

    /**
     * 删除单个运单(不同权限的操作)
     */
    @ApiOperation(value = "删除单个运单，不同权限的操作")
    @RequestMapping(path = "/deleteWayBillById", method = RequestMethod.POST)
    public ResponseResult deleteWayBillById(@RequestParam String wayBillItemId) {
        ResponseResult responseResult = new ResponseResult();

        return responseResult;
    }

    /**
     * 批量删除运单(不同权限的操作)
     */
    @ApiOperation(value = "批量删除运单，不同权限的操作")
    @RequestMapping(path = "/deleteWayBillByIds", method = RequestMethod.POST)
    public ResponseResult deleteWayBillByIds(@RequestParam List<String> wayBillItemIds) {
        ResponseResult responseResult = new ResponseResult();

        return responseResult;
    }


    /**
     * 运单多条件分页查询
     *
     * @return
     */
    @ApiOperation(value = "运单多条件分页查询")
    @RequestMapping(path = "/findWayBillByConditions", method = RequestMethod.POST)
    public ResponseResult findWayBillByConditions(ConditionQueryWayBill conditionQueryWayBill) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("wayBillList", wayBillManager.findPageByCondition(conditionQueryWayBill));
        return responseResult;
    }


}
