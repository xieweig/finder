package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.transmit.WayBillManager;
import cn.sisyphe.coffee.bill.viewmodel.waybill.ConditionQueryWayBill;
import cn.sisyphe.coffee.bill.viewmodel.waybill.EditWayBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        try {
            wayBillManager.updateWayBillWithDTO(editWayBillDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }


    /**
     * * 查询单个运单跟踪信息
     * *
     * * @param billCode
     * * @param billId
     * * @return
     */
    @ApiOperation(value = "根据运单code查询单个运单跟踪信息")
    @RequestMapping(path = "/findOneWayBill", method = RequestMethod.GET)
    public ResponseResult findOneWayBill(@RequestParam(required = false) String id) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("wayBill", wayBillManager.findOneWayBill(id));
        return responseResult;
    }


    /**
     * 运单多条件分页查询
     *
     * @return
     */
    @ApiOperation(value = "运单多条件分页查询")
    @RequestMapping(path = "/findWayBillByConditions", method = RequestMethod.POST)
    public ResponseResult findWayBillByConditions(@RequestBody ConditionQueryWayBill conditionQueryWayBill) {
        ResponseResult responseResult = new ResponseResult();

        responseResult.put("wayBillList", wayBillManager.findPageByCondition(conditionQueryWayBill));
        return responseResult;
    }


    /**
     * 确认收货
     *
     * @param wayBillCode
     * @return
     */
    @ApiOperation(value = "运单确认收货")
    @RequestMapping(path = "/confirmReceiptBill", method = RequestMethod.GET)
    public ResponseResult confirmReceiptBill(@RequestParam String wayBillCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            wayBillManager.confirmReceiptBill(wayBillCode);
        } catch (DataException data) {
            responseResult.putException(data);
        }

        return responseResult;
    }

}
