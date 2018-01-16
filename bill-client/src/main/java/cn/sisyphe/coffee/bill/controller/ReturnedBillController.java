package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.application.returned.ReturnedBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBill;
import cn.sisyphe.coffee.bill.domain.shared.LoginInfo;
import cn.sisyphe.coffee.bill.viewmodel.plan.child.ChildPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.coffee.bill.viewmodel.returned.AddReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ConditionQueryReturnedBill;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.waybill.ScanFillBillDTO;
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
 * @date: 2018/1/3
 * @description:
 * @author：bifenglin
 */
@RequestMapping("/api/bill/returned")
@RestController
@CrossOrigin(origins = "*")
@Api(description = "退库相关操作")
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
        try {
            Page<ChildPlanBillDTO> planBillDTOS = planBillManager.findChildPlanBillByCondition(conditionQueryPlanBill, BillTypeEnum.RETURNED, BillPurposeEnum.OUT_STORAGE);

//            for (ChildPlanBillDTO childPlanBillDTO : planBillDTOS) {
//                //测试使用
//                childPlanBillDTO.setOperatorName("操作人：懒羊羊");
////                returnedCodeList.add(childPlanBillDTO.getBillCode());
//            }

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
     * 保存退库出库单
     *
     * @param addReturnedBillDTO
     * @return
     */
    @ApiOperation(value = "保存退库出库单（计划）")
    @RequestMapping(path = "/saveReturnedBill", method = RequestMethod.POST)
    public ResponseResult saveReturnedBill(HttpServletRequest request, @RequestBody AddReturnedBillDTO addReturnedBillDTO) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        addReturnedBillDTO.setOperatorCode(loginInfo.getOperatorCode());
//        addReturnedBillDTO.setOperatorCode("001");
        ResponseResult responseResult = new ResponseResult();
        returnedBillManager.saveBill(addReturnedBillDTO);
        return responseResult;
    }

    @ApiOperation(value = "保存退库出库单 (站点自主)")
    @RequestMapping(path = "/saveReturnedBillBySelf", method = RequestMethod.POST)
    public ResponseResult saveReturnedBillBySelf(HttpServletRequest request, @RequestBody AddReturnedBillDTO addReturnedBillDTO) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        addReturnedBillDTO.setOperatorCode(loginInfo.getOperatorCode());
//        addReturnedBillDTO.setOperatorCode("001");
        ResponseResult responseResult = new ResponseResult();
        returnedBillManager.saveBill(addReturnedBillDTO);
        return responseResult;
    }

    /**
     * 提交退库单
     *
     * @param addReturnedBillDTO
     * @return
     */
    @ApiOperation(value = "提交退库出库单")
    @RequestMapping(path = "/submitReturnedBill", method = RequestMethod.POST)
    public ResponseResult submitReturnedBill(HttpServletRequest request, @RequestBody AddReturnedBillDTO addReturnedBillDTO) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        addReturnedBillDTO.setOperatorCode(loginInfo.getOperatorCode());
//        addReturnedBillDTO.setOperatorCode("001");
        ResponseResult responseResult = new ResponseResult();
        returnedBillManager.submitBill(addReturnedBillDTO);

        return responseResult;
    }

    @ApiOperation(value = "提交退库出库单")
    @RequestMapping(path = "/submitReturnedBillBySelf", method = RequestMethod.POST)
    public ResponseResult submitReturnedBillBySelf(HttpServletRequest request, @RequestBody AddReturnedBillDTO addReturnedBillDTO) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        addReturnedBillDTO.setOperatorCode(loginInfo.getOperatorCode());
//        addReturnedBillDTO.setOperatorCode("001");
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

        Page<ReturnedBillDTO> billPage =  returnedBillManager.findByConditions(conditionQueryReturnedBill, BillTypeEnum.RETURNED, BillPurposeEnum.OUT_STORAGE);
        List<ReturnedBillDTO> list = billPage.getContent();
        //测试使用
//        for (ReturnedBillDTO returnedBillDTO :
//                list) {
//            returnedBillDTO.setAuditPersonName("审核人：海绵宝宝");
//            returnedBillDTO.setOperatorName("操作人：派大星");
//        }
        responseResult.put("content", billPage);
        return responseResult;
    }

    /**
     * @param returnedBillCode
     * @return
     */
    @ApiOperation(value = "入库单审核")
    @RequestMapping(path = "/openByReturnedBillCode", method = RequestMethod.GET)
    public ResponseResult openByReturnedBillCode(@RequestParam String returnedBillCode) {
        ResponseResult responseResult = new ResponseResult();
        ReturnedBill billDTO = returnedBillManager.openBill(returnedBillCode);
        responseResult.put("ReturnedBill", billDTO);
        return responseResult;
    }

    /**
     * @param returnedBillCode
     * @return
     */
    @ApiOperation(value = "据退库出库单编码查详细信息以查询")
    @RequestMapping(path = "/findByReturnedBillCode", method = RequestMethod.GET)
    public ResponseResult findByReturnedBillCode(@RequestParam String returnedBillCode) {
        ResponseResult responseResult = new ResponseResult();
        ReturnedBill billDTO = returnedBillManager.findByReturnedBillCode(returnedBillCode);
        responseResult.put("ReturnedBill", billDTO);
        return responseResult;
    }

    /**
     * @param returnedBillCode
     * @return
     */
    @ApiOperation(value = "据退库出库单编码查详细信息以修改")
    @RequestMapping(path = "/findByReturnedBillCodeToEdit", method = RequestMethod.GET)
    public ResponseResult findByReturnedBillCodeToEdit(@RequestParam String returnedBillCode) {
        ResponseResult responseResult = new ResponseResult();
        ReturnedBill billDTO = returnedBillManager.findByReturnedBillCode(returnedBillCode);
        responseResult.put("ReturnedBill", billDTO);
        return responseResult;
    }

    /**
     * @param returnedBillCode
     * @return
     */
    @ApiOperation(value = "据退库出库单编码查详细信息以审核")
    @RequestMapping(path = "/findByReturnedBillCodeToAudit", method = RequestMethod.GET)
    public ResponseResult findByReturnedBillCodeToAudit(@RequestParam String returnedBillCode) {
        ResponseResult responseResult = new ResponseResult();
        ReturnedBill billDTO = returnedBillManager.findByReturnedBillCode(returnedBillCode);
        responseResult.put("ReturnedBill", billDTO);
        return responseResult;
    }

    /**
     * 修改退库出库单单据信息
     *
     * @param billDTO
     * @return
     */
    @ApiOperation(value = "修改退库出库单单据信息--保存")
    @RequestMapping(path = "/updateReturnedBillToSave", method = RequestMethod.POST)
    public ResponseResult updateReturnedBillToSaved(HttpServletRequest request, @RequestBody AddReturnedBillDTO billDTO) {
        ResponseResult responseResult = new ResponseResult();
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        billDTO.setOperatorCode(loginInfo.getOperatorCode());
        try {
            returnedBillManager.updateBillToSave(billDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 修改入库单据信息
     *
     * @param billDTO
     * @return
     */
    @ApiOperation(value = "修改退库出库单单据信息--提交审核")
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
     * @param returnedBillCode 退库出库单单编码
     * @return
     */
    @ApiOperation(value = "审核不通过")
    @RequestMapping(path = "/auditFailure", method = RequestMethod.POST)
    public ResponseResult auditFailure(@RequestParam String returnedBillCode, HttpServletRequest request) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        ResponseResult responseResult = new ResponseResult();
        returnedBillManager.auditBill(returnedBillCode, loginInfo.getOperatorCode(), false);
        return responseResult;
    }

    /**
     * 审核通过
     *
     * @param returnedBillCode 退库出库单单编码
     * @return
     */
    @ApiOperation(value = "审核通过")
    @RequestMapping(path = "/auditSuccess", method = RequestMethod.POST)
    public ResponseResult auditSuccess(@RequestParam String returnedBillCode, HttpServletRequest request) {

        ResponseResult responseResult = new ResponseResult();
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);

        returnedBillManager.auditBill(returnedBillCode, loginInfo.getOperatorCode(), true);
        return responseResult;
    }

    /**
     * 通过单据号billCode汇总查询出打包的信息
     *
     * @param billCode
     * @return responseResult
     */
    @ApiOperation(value = "通过单据号billCode汇总查询出打包的信息")
    @RequestMapping(path = "/findPackageInfoByBillCode", method = RequestMethod.GET)
    public ResponseResult findPackageInfoByBillCode(@RequestParam String billCode) {

        ResponseResult responseResult = new ResponseResult();
        try {
            ScanFillBillDTO scanFillBillDTO = returnedBillManager.findPackageInfoByBillCode(billCode);
            responseResult.put("content", scanFillBillDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

}
