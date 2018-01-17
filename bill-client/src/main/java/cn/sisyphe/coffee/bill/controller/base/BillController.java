package cn.sisyphe.coffee.bill.controller.base;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.shared.LoginInfo;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.framework.auth.logic.annotation.ScopeAuth;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.ApiOperation;
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
public class BillController<T extends Bill, D extends BillDTO, Q extends ConditionQueryBill> {

    protected AbstractBillExtraManager<T, D, Q> abstractBillExtraManager;
    private PlanBillManager planBillManager;
    private AllotBillManager allotBillManager;

    public BillController(AbstractBillExtraManager<T, D, Q> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        this.abstractBillExtraManager = abstractBillExtraManager;
        this.planBillManager = planBillManager;
        this.allotBillManager = allotBillManager;
    }

    // --------------- 子计划单 begin -----------------------

    /**
     * 子计划列表
     *
     * @return
     */
    @ApiOperation(value = "多条件分页查询计划单据")
    @RequestMapping(path = "/findPlanByConditions", method = RequestMethod.POST)
    public ResponseResult findPlanByConditions(@RequestBody ConditionQueryPlanBill conditionQueryPlanBill, BillTypeEnum billTypeEnum) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", planBillManager.findChildPlanBillByCondition(conditionQueryPlanBill, billTypeEnum));
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
    @ApiOperation(value = "根据单据号查询计划单据详细信息")
    @RequestMapping(path = "/findPlanByBillCode", method = RequestMethod.GET)
    public ResponseResult findPlanByBillCode(@RequestParam("billCode") String billCode, BillTypeEnum billTypeEnum) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", planBillManager.findChildPlanBillByBillCode(billCode, billTypeEnum));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    // --------------- 子计划单 end-----------------------


    // --------------- 出库单 begin -----------------------

    /**
     * 出库单列表
     *
     * @param conditionQueryBill 查询条件DTO
     * @return
     */
    @ApiOperation(value = "多条件分页查询出库单据")
    @RequestMapping(path = "/findOutStorageByConditions", method = RequestMethod.POST)
//    @ScopeAuth(scopes = {"#conditionQueryAdjustBill.outStationCodeArray", "#conditionQueryAdjustBill.inStationCodeArray"}, token = "userCode")
    public ResponseResult findOutStorageByConditions(@RequestBody Q conditionQueryBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", abstractBillExtraManager.findBillByCondition(conditionQueryBill, BillPurposeEnum.OUT_STORAGE));
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
    @ApiOperation(value = "根据单据号查询出库单据详细信息")
    @RequestMapping(path = "/findOutStorageByBillCode", method = RequestMethod.GET)
    public ResponseResult findOutStorageByBillCode(@RequestParam String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", abstractBillExtraManager.findOneByBillCode(billCode));
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
    @ApiOperation(value = "根据源单号查询单据详细信息")
    @RequestMapping(path = "/findBySourceCode", method = RequestMethod.GET)
    public ResponseResult findBySourceCode(@RequestParam("sourceCode") String sourceCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", abstractBillExtraManager.findBySourceCode(sourceCode));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    // --------------- 出库单 end -----------------------


    // --------------- 入库单 begin -----------------------

    /**
     * 入库单列表
     *
     * @param conditionQueryBill 查询条件DTO
     * @return
     */
    @ApiOperation(value = "多条件分页查询入库单据")
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
    @ApiOperation(value = "根据单据号查询入库单据详细信息")
    @RequestMapping(path = "/findInStorageByBillCode", method = RequestMethod.GET)
    public ResponseResult findInStorageByBillCode(@RequestParam String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", abstractBillExtraManager.findOneByBillCode(billCode));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    // --------------- 入库单 end -----------------------


    // --------------- 调拨单 begin -----------------------

    /**
     * 调拨单列表
     *
     * @param conditionQueryAllotBill 查询条件DTO
     * @return
     */
    @ApiOperation(value = "多条件分页查询调拨单据")
    @RequestMapping(path = "/findAllotByConditions", method = RequestMethod.POST)
    @ScopeAuth(scopes = {"#conditionQueryPlanBill.outStationCodeArray", "#conditionQueryPlanBill.inStationCodeArray"}, token = "userCode")
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
    @ApiOperation(value = "根据单据号查询调拨单据详细信息")
    @RequestMapping(path = "/findAllotByBillCode", method = RequestMethod.GET)
    public ResponseResult findAllotByBillCode(@RequestParam String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", allotBillManager.findOneByBillCode(billCode));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    // --------------- 调拨单 end -----------------------


    // --------------- 业务操作 begin -----------------------

    /**
     * 根据单据号打开单据
     *
     * @param billCode 单据号
     * @return
     */
    @ApiOperation(value = "根据单据号打开单据")
    @RequestMapping(path = "/open", method = RequestMethod.GET)
    public ResponseResult open(HttpServletRequest request, @RequestParam(value = "billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
            responseResult.put("adjustBill", abstractBillExtraManager.openBill(billCode, loginInfo.getOperatorCode()));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 保存单据信息
     *
     * @param billDTO 单据DTO
     * @return
     */
    @ApiOperation(value = "保存单据信息")
    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ResponseResult save(HttpServletRequest request, @RequestBody D billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
            billDTO.setOperatorCode(loginInfo.getOperatorCode());
            //测试使用
            //billDTO.setOperatorCode("test0001");
            responseResult.put("billCode", abstractBillExtraManager.saveBill(billDTO));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 保存单据信息
     *
     * @param billDTO 单据DTO
     * @return
     */
    @ApiOperation(value = "保存单据信息--自主拣货保存")
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
    @ApiOperation(value = "提交单据信息")
    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    public ResponseResult submit(HttpServletRequest request, @RequestBody D billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
            billDTO.setOperatorCode(loginInfo.getOperatorCode());
            //addAdjustBillDTO.setOperatorCode("test0001");
            responseResult.put("billCode", abstractBillExtraManager.submitBill(billDTO));
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
    @ApiOperation(value = "提交单据信息--自主拣货保存")
    @RequestMapping(path = "/submitBySelf", method = RequestMethod.POST)
    public ResponseResult submitBySelf(HttpServletRequest request, @RequestBody D billDTO) {

        return submit(request, billDTO);
    }

    /**
     * 审核不通过
     *
     * @param billCode 单据号
     * @return
     */
    @ApiOperation(value = "审核不通过")
    @RequestMapping(path = "/auditFailure", method = RequestMethod.POST)
    public ResponseResult auditFailure(HttpServletRequest request, @RequestBody D billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
            abstractBillExtraManager.auditBill(billDTO.getBillCode(), loginInfo.getOperatorCode(), billDTO.getAuditMemo(), false);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 审核通过
     *
     * @param billCode 单据号
     * @return
     */
    @ApiOperation(value = "审核通过")
    @RequestMapping(path = "/auditSuccess", method = RequestMethod.POST)
    public ResponseResult auditSuccess(HttpServletRequest request,@RequestBody D billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
            abstractBillExtraManager.auditBill(billDTO.getBillCode(), loginInfo.getOperatorCode(), billDTO.getAuditMemo(), true);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    // --------------- 业务操作 begin -----------------------
}
