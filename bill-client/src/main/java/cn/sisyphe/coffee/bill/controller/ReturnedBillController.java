package cn.sisyphe.coffee.bill.controller;


import cn.sisyphe.coffee.bill.application.returned.ReturnedBillManager;
import cn.sisyphe.coffee.bill.viewmodel.returned.AddReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.QueryOneReturnedBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author mayupeng
 * @Date 2018/01/07
 * @description 退货计划单
 */

@RequestMapping("/api/bill/returned")
@RestController
@Api(description = "站点退货拣货相关接口")
@CrossOrigin(origins = "*")
public class ReturnedBillController {
    @Autowired
    private ReturnedBillManager returnedBillManager;

    /**
     * 保存退货单
     *
     * @param addReturnedBillDTO
     * @return
     */
    @ApiOperation(value = "保存退货单")
    @RequestMapping(path = "/saveReturnedBill", method = RequestMethod.POST)
    public ResponseResult saveRuturnedBill(@RequestBody AddReturnedBillDTO addReturnedBillDTO) {
        ResponseResult responseResult = new ResponseResult();
        returnedBillManager.saveBill(addReturnedBillDTO);
        return responseResult;
    }

    /**
     * 提交退货单
     *
     * @param addReturnedBillDTO
     * @return
     */
    @ApiOperation(value = "提交退货单")
    @RequestMapping(path = "/submitReturnedBill", method = RequestMethod.POST)
    public ResponseResult submitReturnedBill(@RequestBody AddReturnedBillDTO addReturnedBillDTO) {
        ResponseResult responseResult = new ResponseResult();
        returnedBillManager.submitBill(addReturnedBillDTO);
        return responseResult;
    }

    /**
     * 根据入库单编码查询退货单详细信息
     *
     * @param ReturnedBillCode
     * @return
     */
    @ApiOperation(value = "根据入库单编码查询退货单详细信息")
    @RequestMapping(path = "/findByReturnedBillCode", method = RequestMethod.GET)
    public ResponseResult findByReturnedBillCode(@RequestParam String ReturnedBillCode) {
        ResponseResult responseResult = new ResponseResult();
        QueryOneReturnedBillDTO billDTO = returnedBillManager.openBill(ReturnedBillCode);
        responseResult.put("ReturnedBill", billDTO);
        return responseResult;
    }

    /**
     * 修改退货单单据信息
     *
     * @param billDTO
     * @return
     */
    @ApiOperation(value = "修改退货单单据信息--保存")
    @RequestMapping(path = "/updateReturnedBillToSave", method = RequestMethod.POST)
    public ResponseResult updateReturnedBillToSaved(@RequestBody AddReturnedBillDTO billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            returnedBillManager.updateBillToSave(billDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 修改退货单单据信息
     *
     * @param billDTO
     * @return
     */
    @ApiOperation(value = "修改退货单单据信息--提交审核")
    @RequestMapping(path = "/updateReturnedBillToSubmit", method = RequestMethod.POST)
    public ResponseResult updateReturnedBillToSubmit(@RequestBody AddReturnedBillDTO billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            returnedBillManager.updateBillToSubmit(billDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 审核不通过
     *
     * @param ReturnedBillCode *
     * @return
     */
    @ApiOperation(value = "审核不通过")
    @RequestMapping(path = "/auditFailure", method = RequestMethod.POST)
    public ResponseResult auditFailure(@RequestParam String ReturnedBillCode, @RequestParam String auditPersonCode) {
        ResponseResult responseResult = new ResponseResult();
        returnedBillManager.auditBill(ReturnedBillCode, auditPersonCode, true);
        return responseResult;
    }

    /**
     * 审核通过
     *
     * @param ReturnedBillCode 退货单编码
     * @return
     */
    @ApiOperation(value = "审核通过")
    @RequestMapping(path = "/auditSuccess", method = RequestMethod.POST)
    public ResponseResult auditSuccess(@RequestParam String ReturnedBillCode, @RequestParam String auditPersonCode) {
        ResponseResult responseResult = new ResponseResult();
        returnedBillManager.auditBill(ReturnedBillCode, auditPersonCode, false);
        return responseResult;
    }
}
