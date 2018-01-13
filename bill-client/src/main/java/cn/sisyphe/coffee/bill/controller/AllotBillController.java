package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.allot.AddAllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author bifenglin
 * @Date 2018/1/12
 * @description
 */
@RestController
@RequestMapping("/api/bill/allotBill")
@Api(description = "调拨相关接口")
@CrossOrigin(origins = "*")
public class AllotBillController {

    @Autowired
    AllotBillManager allotBillManager;

    /**
     * 调拨单据多条件分页查询
     *
     * @return
     */
    @ApiOperation(value = "调拨单据多条件分页查询")
    @RequestMapping(path = "/findAllotBillByConditions", method = RequestMethod.POST)
    public ResponseResult findAllotBillByConditions(@RequestBody ConditionQueryAllotBill conditionQueryAllotBill) {
        ResponseResult responseResult = new ResponseResult();
        System.err.print("调拨单据多条件分页查询");
        try {
            Page<AllotBillDTO> allotBillDTOS = allotBillManager.findAllotBillByCondition(conditionQueryAllotBill, BillTypeEnum.ALLOT);

            for (AllotBillDTO allotBillDTO : allotBillDTOS) {
                //测试使用
                allotBillDTO.setOperatorName("操作人：懒羊羊");
            }
            responseResult.put("content", allotBillDTOS);
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 调拨单查询
     *
     * @return
     */
    @ApiOperation(value = "调拨单单个查询")
    @RequestMapping(path = "/findAllotBillByBillCode", method = RequestMethod.GET)
    public ResponseResult findAllotBillByBillCode(@RequestParam String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("allotBill", allotBillManager.findAllotBillByBillCode(billCode));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    @ApiOperation(value = "调拨")
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseResult allot(@RequestBody AddAllotBillDTO allotDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
//            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
//            addAdjustBillDTO.setOperatorCode(loginInfo.getOperatorCode());
            allotBillManager.createAllotBill(allotDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }
}
