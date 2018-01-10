package cn.sisyphe.coffee.bill.controller;


import cn.sisyphe.coffee.bill.application.planbill.PlanBillManager;
import cn.sisyphe.coffee.bill.application.returned.ReturnedBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBill;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBill;
import cn.sisyphe.coffee.bill.domain.shared.LoginInfo;
import cn.sisyphe.coffee.bill.viewmodel.plan.child.ChildPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.AddReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ConditionQueryReturnedBill;
import cn.sisyphe.coffee.bill.viewmodel.returned.QueryReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @Autowired
    private SharedManager sharedManager;

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
        //设定查询退库分片
        conditionQueryPlanBill.setSpecificBillType(BillTypeEnum.RETURNED);
        try {
            Page<ChildPlanBillDTO> planBillDTOS = planBillManager.findChildPlanBillByCondition(conditionQueryPlanBill);
            //测试使用
            for (ChildPlanBillDTO childPlanBillDTO : planBillDTOS) {
                childPlanBillDTO.setOperatorName("操作人：懒羊羊");
            }
            responseResult.put("content", planBillDTOS);

        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    @ApiOperation(value = "子计划直接查看已保存的拣货单")
    @RequestMapping(path = "/findReturnedBillBySourceCode", method = RequestMethod.GET)
    public ResponseResult findReturnedBillBySourceCode(@RequestParam("sourceCode") String sourceCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("returnedBill", returnedBillManager.findReturnedBillBySourceCode(sourceCode));
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
    public ResponseResult saveRuturnedBill(HttpServletRequest request, @RequestBody AddReturnedBillDTO addReturnedBillDTO) {
        //addReturnedBillDTO.setOperatorCode(LoginInfo.getLoginInfo(request).getOperatorCode());
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
    public ResponseResult submitReturnedBill(HttpServletRequest request, @RequestBody AddReturnedBillDTO addReturnedBillDTO) {
        //addReturnedBillDTO.setOperatorCode(LoginInfo.getLoginInfo(request).getOperatorCode());
        ResponseResult responseResult = new ResponseResult();
        returnedBillManager.submitBill(addReturnedBillDTO);
        return responseResult;
    }

    /**
     * 多条件查询退货单
     *
     * @param conditionQueryReturnedBill
     * @return
     */
    @ApiOperation(value = "多条件查询退货单")
    @RequestMapping(path = "/findByConditions", method = RequestMethod.POST)
    public ResponseResult findByConditions(@RequestBody ConditionQueryReturnedBill conditionQueryReturnedBill) {
        ResponseResult responseResult = new ResponseResult();
        QueryReturnedBillDTO billPage = returnedBillManager.findByConditions(conditionQueryReturnedBill);
        //测试使用
        List<ReturnedBillDTO> list = billPage.getContent();
        for (ReturnedBillDTO returnedBillDTO: list) {
            returnedBillDTO.setAuditPersonName("审核人：海绵宝宝");
            returnedBillDTO.setOperatorName("操作人：派大星");
        }
        responseResult.put("content", billPage);
        return responseResult;
    }
    /**
     * @param returnedBillCode
     * @return
     */
    @ApiOperation(value = "根据退库出库单编码查询图库出库单详细信息")
    @RequestMapping(path = "/openByReturnedBillCode", method = RequestMethod.GET)
    public ResponseResult openByReturnedBillCode(@RequestParam String returnedBillCode) {
        ResponseResult responseResult = new ResponseResult();
//        QueryOneReturnedBillDTO billDTO = returnedBillManager.openBill(ReturnedBillCode);
        ReturnedBill billDTO = returnedBillManager.openBill(returnedBillCode);
        responseResult.put("ReturnedBill", billDTO);
        return responseResult;
    }
    /**
     * 根据退货单编码查询退货单详细信息
     *
     * @param ReturnedBillCode
     * @return
     */
    @ApiOperation(value = "根据退货单编码查询退货单详细信息")
    @RequestMapping(path = "/findByReturnedBillCode", method = RequestMethod.GET)
    public ResponseResult findByReturnedBillCode(@RequestParam String returnedBillCode) {
        ResponseResult responseResult = new ResponseResult();
        ReturnedBill billDTO = returnedBillManager.findByReturnedBillCode(returnedBillCode);
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
    public ResponseResult updateReturnedBillToSaved(HttpServletRequest request, @RequestBody AddReturnedBillDTO billDTO) {
        //billDTO.setOperatorCode(LoginInfo.getLoginInfo(request).getOperatorCode());
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
    public ResponseResult updateReturnedBillToSubmit(HttpServletRequest request, @RequestBody AddReturnedBillDTO billDTO) {
        //billDTO.setOperatorCode(LoginInfo.getLoginInfo(request).getOperatorCode());
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
     * @param ReturnedBillCode 退货单编码
     * @return
     */
    @ApiOperation(value = "审核不通过")
    @RequestMapping(path = "/auditFailure", method = RequestMethod.POST)
    public ResponseResult auditFailure(HttpServletRequest request, @RequestParam String ReturnedBillCode) {
        //LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        ResponseResult responseResult = new ResponseResult();
        //returnedBillManager.auditBill(ReturnedBillCode, loginInfo.getOperatorCode(), false);
        returnedBillManager.auditBill(ReturnedBillCode, "001", false);
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
    public ResponseResult auditSuccess(HttpServletRequest request, @RequestParam String ReturnedBillCode) {
        //LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        ResponseResult responseResult = new ResponseResult();
        //returnedBillManager.auditBill(ReturnedBillCode, loginInfo.getOperatorCode(), true);
        returnedBillManager.auditBill(ReturnedBillCode, "001", true);
        return responseResult;
    }


}
