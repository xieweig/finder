package cn.sisyphe.coffee.bill.controller;


import cn.sisyphe.coffee.bill.application.planbill.PlanBillManager;
import cn.sisyphe.coffee.bill.application.returned.ReturnedBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.coffee.bill.viewmodel.returned.AddReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ConditionQueryReturnedBill;
import cn.sisyphe.coffee.bill.viewmodel.returned.QueryOneReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.QueryReturnedBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private PlanBillManager planBillManager;

    /**
     * 计划单据多条件分页查询
     *
     * @return
     */
    @ApiOperation(value = "子计划多条件查询")
    @RequestMapping(path = "/findPlanBillByConditions", method = RequestMethod.POST)
    public ResponseResult findChildPlanBillByConditions(@RequestBody ConditionQueryPlanBill conditionQueryPlanBill) {
        ResponseResult responseResult = new ResponseResult();
        System.err.print("子计划多条件查询开始");
        //设定查询退货分片
        conditionQueryPlanBill.setSpecificBillType(BillTypeEnum.RETURNED);
        try {
            responseResult.put("content", planBillManager.findChildPlanBillByCondition(conditionQueryPlanBill));

        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    @ApiOperation(value = "子计划单个查询")
    @RequestMapping(path = "/findPlanBillByBillCode", method = RequestMethod.POST)
    public ResponseResult findByBillCode(@RequestParam("billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("planBill", planBillManager.findChildPlanBillByBillCodeAndType(billCode, BillTypeEnum.RETURNED));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

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
     * 多条件查询退库入库单
     *
     * @param conditionQueryReturnedBill
     * @return
     */
    @ApiOperation(value = "多条件查询退库出库单")
    @RequestMapping(path = "/findByConditions", method = RequestMethod.POST)
    public ResponseResult findByConditions(@RequestBody ConditionQueryReturnedBill conditionQueryReturnedBill) {
        ResponseResult responseResult = new ResponseResult();

        QueryReturnedBillDTO billPage = returnedBillManager.findByConditions(conditionQueryReturnedBill);
        responseResult.put("content", billPage);
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
        //从head中查看
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
