package cn.sisyphe.coffee.bill.controller.base;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.shared.LoginInfo;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import cn.sisyphe.coffee.bill.viewmodel.plan.ConditionQueryPlanBill;
import cn.sisyphe.framework.auth.logic.annotation.ScopeAuth;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂业务控制层
 * version: 1.0
 *
 * @author XiongJing
 */
@CrossOrigin(origins = "*")
public class BillController<T extends Bill, D extends BillDTO, Q extends ConditionQueryBill> {

    protected AbstractBillExtraManager<T, D, Q> abstractBillExtraManager;
    private PlanBillManager planBillManager;
    private AllotBillManager allotBillManager;

    public BillController(AbstractBillExtraManager<T, D, Q> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        this.abstractBillExtraManager = abstractBillExtraManager;
        this.planBillManager = planBillManager;
        this.allotBillManager = allotBillManager;
    }


    //region --------------- 子计划单 ------------

    /**
     * 子计划列表
     *
     * @return
     */
    @ApiOperation(value = "子计划单列表")
//    @ScopeAuth(scopes = "#conditionQueryPlanBill.outStationCodes", token = "userCode")
    @RequestMapping(path = "/findPlanByConditions", method = RequestMethod.POST)
    public ResponseResult findPlanByConditions(@RequestBody ConditionQueryPlanBill conditionQueryPlanBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("billList", planBillManager.findChildPlanBillByCondition(conditionQueryPlanBill, abstractBillExtraManager.billType()));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 子计划明细
     *
     * @param billCode 单据号
     * @return
     */
    @ApiOperation(value = "子计划明细")
    @RequestMapping(path = "/findPlanByBillCode", method = RequestMethod.GET)
    public ResponseResult findPlanByBillCode(@RequestParam("billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", planBillManager.findChildPlanBillByBillCode(billCode, abstractBillExtraManager.billType()));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    //endregion


    //region --------------- 出库单 ------------

    /**
     * 出库单列表
     *
     * @param conditionQueryBill 查询条件DTO
     * @return
     */
    @ApiOperation(value = "出库单列表")
//    @ScopeAuth(scopes = "#conditionQueryBill.outStationCodes", token = "userCode")
    @RequestMapping(path = "/findOutStorageByConditions", method = RequestMethod.POST)
    public ResponseResult findOutStorageByConditions(@RequestBody Q conditionQueryBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("billList", abstractBillExtraManager.findBillByCondition(conditionQueryBill, BillPurposeEnum.OUT_STORAGE));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 出库单明细
     *
     * @param billCode 单据号
     * @return
     */
    @ApiOperation(value = "出库单明细")
    @RequestMapping(path = "/findOutStorageByBillCode", method = RequestMethod.GET)
    public ResponseResult findOutStorageByBillCode(@RequestParam String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", abstractBillExtraManager.findBillDtoByBillCode(billCode));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 拣货明细
     *
     * @param sourceCode
     * @return
     */
    @ApiOperation(value = "拣货明细")
    @RequestMapping(path = "/findBySourceCode", method = RequestMethod.GET)
    public ResponseResult findBySourceCode(@RequestParam("sourceCode") String sourceCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", abstractBillExtraManager.findBillDtoBySourceCode(sourceCode));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    //endregion


    //region --------------- 入库单 ------------

    /**
     * 入库单列表
     *
     * @param conditionQueryBill 查询条件DTO
     * @return
     */
    @ApiOperation(value = "入库单列表")
//    @ScopeAuth(scopes = "#conditionQueryBill.inStationCodes", token = "userCode")
    @RequestMapping(path = "/findInStorageByConditions", method = RequestMethod.POST)
//    @ScopeAuth(scopes = {"#conditionQueryPlanBill.outStationCodeArray", "#conditionQueryPlanBill.inStationCodeArray"}, token = "userCode")
    public ResponseResult findInStorageByConditions(@RequestBody Q conditionQueryBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", abstractBillExtraManager.findBillByCondition(conditionQueryBill, BillPurposeEnum.IN_STORAGE));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 入库单明细
     *
     * @param billCode 单据号
     * @return
     */
    @ApiOperation(value = "入库单明细")
    @RequestMapping(path = "/findInStorageByBillCode", method = RequestMethod.GET)
    public ResponseResult findInStorageByBillCode(@RequestParam String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", abstractBillExtraManager.findBillDtoByBillCode(billCode));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    //endregion


    //region --------------- 调拨单 ------------

    /**
     * 调拨单保存
     *
     * @param billDTO 调拨单DTO
     * @return
     */
//    @ScopeAuth(scopes = "#AllotBillDTO.inStationCodes", token = "userCode")
    @ApiOperation(value = "调拨单列表")
    @RequestMapping(path = "/allotSave", method = RequestMethod.POST)
    //@ScopeAuth(scopes = {"#conditionQueryPlanBill.outStationCodeArray", "#conditionQueryPlanBill.inStationCodeArray"}, token = "userCode")
    public ResponseResult allotSave(@RequestBody AllotBillDTO billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", allotBillManager.saveBill(billDTO));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 调拨单列表
     *
     * @param conditionQueryAllotBill 查询条件DTO
     * @return
     */
    @ApiOperation(value = "调拨单列表")
//    @ScopeAuth(scopes = "#conditionQueryAllotBill.inStationCodes", token = "userCode")
    @RequestMapping(path = "/findAllotByConditions", method = RequestMethod.POST)
    //@ScopeAuth(scopes = {"#conditionQueryPlanBill.outStationCodeArray", "#conditionQueryPlanBill.inStationCodeArray"}, token = "userCode")
    public ResponseResult findAllotByConditions(@RequestBody ConditionQueryAllotBill conditionQueryAllotBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", allotBillManager.findBillByCondition(conditionQueryAllotBill, BillPurposeEnum.MOVE_STORAGE));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 调拨单明细
     *
     * @param billCode 单据号
     * @return
     */
    @ApiOperation(value = "调拨单明细")
    @RequestMapping(path = "/findAllotByBillCode", method = RequestMethod.GET)
    public ResponseResult findAllotByBillCode(@RequestParam String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", allotBillManager.findBillDtoByBillCode(billCode));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    //endregion


    //region --------------- 业务操作 ------------


    /**
     * 保存单据信息
     *
     * @param billDTO 单据DTO
     * @return
     */
    @ApiOperation(value = "保存单据")
//    @ScopeAuth(scopes = "#billDTO.outStationCodes", token = "userCode")
    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ResponseResult save(HttpServletRequest request, @RequestBody D billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
//            //若是修改则判断录单人有没有权限
//            if (billDTO.getBillCode() != null && !loginInfo.getOperatorCode().equals(billDTO.getOperatorCode())) {
//                throw new DataException("100001", "录单人无权限");
//            }
            billDTO.setOperatorCode(loginInfo.getOperatorCode());
            responseResult.put("billCode", abstractBillExtraManager.saveBill(billDTO).getBillCode());
        } catch (DataException date) {
            responseResult.putException(date);
        }

        return responseResult;
    }

    /**
     * 保存单据信息
     *
     * @param billDTO 单据DTO
     * @return
     */
    @ApiOperation(value = "保存单据--自主拣货保存")
//    @ScopeAuth(scopes = "#billDTO.outStationCodes", token = "userCode")
    @RequestMapping(path = "/saveBySelf", method = RequestMethod.POST)
    public ResponseResult saveBySelf(HttpServletRequest request, @RequestBody D billDTO) {

        return save(request, billDTO);
    }


    /**
     * 提交单据信息
     *
     * @param billDTO 单据DTO
     * @return
     */
    @ApiOperation(value = "提交单据")
    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    public ResponseResult submit(HttpServletRequest request, @RequestBody D billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
            billDTO.setOperatorCode(loginInfo.getOperatorCode());
            responseResult.put("billCode", abstractBillExtraManager.submitBill(billDTO).getBillCode());
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 提交单据信息
     *
     * @param billDTO 单据DTO
     * @return
     */
    @ApiOperation(value = "提交单据--自主拣货")
    @RequestMapping(path = "/submitBySelf", method = RequestMethod.POST)
    public ResponseResult submitBySelf(HttpServletRequest request, @RequestBody D billDTO) {

        return submit(request, billDTO);
    }

    /**
     * 根据单据号打开单据
     *
     * @param billCode 单据号
     * @return
     */
    @ApiOperation(value = "打开单据")
    @RequestMapping(path = "/open", method = RequestMethod.GET)
    public ResponseResult open(HttpServletRequest request, @RequestParam(value = "billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
            responseResult.put("bill", abstractBillExtraManager.openBill(billCode, loginInfo.getOperatorCode()));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 审核不通过
     *
     * @param billDTO 单据号
     * @return
     */
    @ApiOperation(value = "审核不通过")
//    @ScopeAuth(scopes = "#billDTO.outStationCodes", token = "userCode")
    @RequestMapping(path = "/auditFailure", method = RequestMethod.POST)
    public ResponseResult auditFailure(HttpServletRequest request, @RequestBody D billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
            responseResult.put("bill", abstractBillExtraManager.auditBill(billDTO.getBillCode(), loginInfo.getOperatorCode(), billDTO.getAuditMemo(), false));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 审核通过
     *
     * @param billDTO 单据号
     * @return
     */
    @ApiOperation(value = "审核通过")
//    @ScopeAuth(scopes = "#billDTO.outStationCodes", token = "userCode")
    @RequestMapping(path = "/auditSuccess", method = RequestMethod.POST)
    public ResponseResult auditSuccess(HttpServletRequest request, @RequestBody D billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
            responseResult.put("bill", abstractBillExtraManager.auditBill(billDTO.getBillCode(), loginInfo.getOperatorCode(), billDTO.getAuditMemo(), true));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;

    }

    //endregion
}
