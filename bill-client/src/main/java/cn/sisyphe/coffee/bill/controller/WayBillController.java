package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.transmit.WayBillManager;
import cn.sisyphe.coffee.bill.viewmodel.waybill.ConditionQueryWayBill;
import cn.sisyphe.coffee.bill.viewmodel.waybill.EditWayBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 运单
 */

@RequestMapping("/api/bill/waybill")
@RestController
@CrossOrigin(origins = "*")
public class WayBillController {

    @Autowired
    private WayBillManager wayBillManager;

    /**
     * 创建运单
     */
    @RequestMapping(path = "/createWayBill", method = RequestMethod.POST)
    public ResponseResult createWayBill(@RequestBody EditWayBillDTO editWayBillDTO) {

        ResponseResult responseResult = new ResponseResult();

        // responseResult.put("wayBill", wayBillManager.createWayBill(editWayBillDTO));

        return responseResult;

    }

    /**
     * 修改运单
     */
    @RequestMapping(path = "/updateWayBill", method = RequestMethod.POST)
    public ResponseResult updateWayBill(@RequestBody EditWayBillDTO editWayBillDTO) {
        ResponseResult responseResult = new ResponseResult();

        return responseResult;
    }

    /**
     * 删除单个运单(不同权限的操作)
     */
    @RequestMapping(path = "/deleteWayBillById", method = RequestMethod.POST)
    public ResponseResult deleteWayBillById(@RequestParam String wayBillCode) {
        ResponseResult responseResult = new ResponseResult();

        return responseResult;
    }

    /**
     * 批量删除运单(不同权限的操作)
     */
    @RequestMapping(path = "/deleteWayBillByIds", method = RequestMethod.POST)
    public ResponseResult deleteWayBillByIds(@RequestParam List<String> wayBillCodes) {
        ResponseResult responseResult = new ResponseResult();

        return responseResult;
    }


    /**
     * 运单多条件分页查询
     *
     * @return
     */
    @RequestMapping(path = "/findWayBillByConditions", method = RequestMethod.POST)
    public ResponseResult findWayBillByConditions(ConditionQueryWayBill conditionQueryWayBill) {
        ResponseResult responseResult = new ResponseResult();

        return responseResult;
    }


}
