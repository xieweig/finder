package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.adjust.AdjustBillManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.shared.LoginInfo;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AddAdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
import cn.sisyphe.coffee.bill.viewmodel.plan.child.ChildPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.framework.auth.logic.annotation.ScopeAuth;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂业务控制层
 * version: 1.0
 *
 * @author XiongJing
 */
@RestController
@RequestMapping("/api/bill/adjustBill")
@Api(description = "调剂计划相关接口")
@CrossOrigin(origins = "*")
public class AdjustBillController {

    @Autowired
    private PlanBillManager planBillManager;
    @Autowired
    private AdjustBillManager adjustBillManager;

    /**
     * 计划单据多条件分页查询
     *
     * @return
     */
    @ApiOperation(value = "子计划多条件查询")
    @RequestMapping(path = "/findPlanBillByConditions", method = RequestMethod.POST)
    public ResponseResult findPlanBillByConditions(@RequestBody ConditionQueryPlanBill conditionQueryPlanBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            Page<ChildPlanBillDTO> planBillDTOS = planBillManager.findChildPlanBillByCondition(conditionQueryPlanBill,
                    BillTypeEnum.ADJUST, BillPurposeEnum.OutStorage);

            List<String> restockCodeList = new ArrayList<>();
            for (ChildPlanBillDTO childPlanBillDTO : planBillDTOS) {
                restockCodeList.add(childPlanBillDTO.getBillCode());
            }
            responseResult.put("content", planBillDTOS);
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 子计划直接查看已保存的拣货单
     *
     * @param sourceCode
     * @return
     */
    @ApiOperation(value = "子计划直接查看已保存的拣货单")
    @RequestMapping(path = "/findAdjustBillBySourceCode", method = RequestMethod.GET)
    public ResponseResult findAdjustBillBySourceCode(@RequestParam("sourceCode") String sourceCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("restockBill", adjustBillManager.findAdjustBillBySourceCode(sourceCode));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 查询子计划单个查询
     *
     * @param billCode
     * @return
     */
    @ApiOperation(value = "查询子计划单个查询")
    @RequestMapping(path = "/findPlanBillByBillCode", method = RequestMethod.GET)
    public ResponseResult findPlanBillByBillCode(@RequestParam("billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("planBill", planBillManager.findChildPlanBillByBillCodeAndType(billCode, BillTypeEnum.ADJUST));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 多条件查询调剂出库单据
     *
     * @param conditionQueryAdjustBill 查询条件
     * @return
     */
    @ApiOperation(value = "多条件查询调剂出库单据")
    @RequestMapping(path = "/findByConditionsToOut", method = RequestMethod.POST)
    @ScopeAuth(scopes = {"#conditionQueryAdjustBill.outStationCodeArray", "#conditionQueryAdjustBill.inStationCodeArray"},  token = "userCode")
    public ResponseResult findByConditionsToOut(@RequestBody ConditionQueryAdjustBill conditionQueryAdjustBill) {
        ResponseResult responseResult = new ResponseResult();
        Page<AdjustBillDTO> dtoPage = adjustBillManager.findByConditionsToOut(conditionQueryAdjustBill);
        responseResult.put("content", dtoPage);
        return responseResult;
    }

    /**
     * 多条件查询调剂入库单据
     *
     * @param conditionQueryAdjustBill 查询条件
     * @return
     */
    @ApiOperation(value = "多条件查询调剂入库单据")
    @RequestMapping(path = "/findByConditionsToIn", method = RequestMethod.POST)
    @ScopeAuth(scopes = {"#conditionQueryPlanBill.outStationCodeArray", "#conditionQueryPlanBill.inStationCodeArray"},  token = "userCode")
    public ResponseResult findByConditionsToIn(@RequestBody ConditionQueryAdjustBill conditionQueryAdjustBill) {
        ResponseResult responseResult = new ResponseResult();
        Page<AdjustBillDTO> dtoPage = adjustBillManager.findByConditionsToIn(conditionQueryAdjustBill);
        responseResult.put("content", dtoPage);
        return responseResult;
    }

    /**
     * 根据调剂单号查询详细信息
     *
     * @param billCode 调剂单号
     * @return
     */
    @ApiOperation(value = "根据调剂单号查询详细信息")
    @RequestMapping(path = "/findByAdjustBillCode", method = RequestMethod.GET)
    public ResponseResult findByAdjustBillCode(@RequestParam(value = "billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("adjustBill", adjustBillManager.findByBillCode(billCode));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 审核按钮--打开操作
     *
     * @param billCode 调剂单号
     * @return
     */
    @ApiOperation(value = "根据调剂单号查询详细信息")
    @RequestMapping(path = "/openBill", method = RequestMethod.GET)
    public ResponseResult openBill(HttpServletRequest request,@RequestParam(value = "billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        try {
            responseResult.put("adjustBill", adjustBillManager.openBill(billCode,loginInfo.getOperatorCode()));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 保存调剂单据信息
     *
     * @param addAdjustBillDTO
     * @return
     */
    @ApiOperation(value = "保存调剂单据信息  ")
    @RequestMapping(path = "/saveAdjustBill", method = RequestMethod.POST)
    public ResponseResult saveAdjustBill(HttpServletRequest request, @RequestBody AddAdjustBillDTO addAdjustBillDTO) {

        ResponseResult responseResult = new ResponseResult();
        try {
//            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
//            addAdjustBillDTO.setOperatorCode(loginInfo.getOperatorCode());
            addAdjustBillDTO.setOperatorCode("test0001");
            responseResult.put("billCode", adjustBillManager.create(addAdjustBillDTO));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 提交调剂单据信息
     *
     * @param addAdjustBillDTO
     * @return
     */
    @ApiOperation(value = "提交调剂单据信息")
    @RequestMapping(path = "/submitAdjustBill", method = RequestMethod.POST)
    public ResponseResult submitRestockBill(HttpServletRequest request, @RequestBody AddAdjustBillDTO addAdjustBillDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
//            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
//            addAdjustBillDTO.setOperatorCode(loginInfo.getOperatorCode());
            addAdjustBillDTO.setOperatorCode("test0001");
            responseResult.put("billCode", adjustBillManager.submit(addAdjustBillDTO));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 审核不通过
     *
     * @param adjustBillCode 调剂单编码
     * @return
     */
    @ApiOperation(value = "审核不通过")
    @RequestMapping(path = "/auditFailure", method = RequestMethod.POST)
    public ResponseResult auditFailure(HttpServletRequest request, @RequestParam(value = "adjustBillCode") String adjustBillCode) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        ResponseResult responseResult = new ResponseResult();
        adjustBillManager.audit(adjustBillCode, "auditPerson001", false);
        return responseResult;
    }

    /**
     * 审核通过
     *
     * @param adjustBillCode 调剂单编码
     * @return
     */
    @ApiOperation(value = "审核通过")
    @RequestMapping(path = "/auditSuccess", method = RequestMethod.POST)
    public ResponseResult auditSuccess(HttpServletRequest request, @RequestParam(value = "adjustBillCode") String adjustBillCode) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        ResponseResult responseResult = new ResponseResult();
        adjustBillManager.audit(adjustBillCode, "auditPerson001", true);
        return responseResult;
    }

    @ApiOperation(value = "调拨")
    @RequestMapping(path = "/allot", method = RequestMethod.POST)
    public ResponseResult allot(@RequestBody AllotDTO allotDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
//            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
//            addAdjustBillDTO.setOperatorCode(loginInfo.getOperatorCode());
            adjustBillManager.createAllotBill(allotDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

}
