package cn.sisyphe.coffee.bill.controller.base;

import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.purpose.BillPurpose;
import cn.sisyphe.coffee.bill.domain.shared.LoginInfo;
import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
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
public abstract class BillController<T extends Bill, D extends BillDTO, Q extends ConditionQueryBill> {

    private AbstractBillExtraManager<T, D, Q> abstractBillExtraManager;

    public BillController(AbstractBillExtraManager<T, D, Q> abstractBillExtraManager) {
        this.abstractBillExtraManager = abstractBillExtraManager;
    }

    /**
     * 多条件分页查询计划单据
     *
     * @return
     */
    @ApiOperation(value = "多条件分页查询计划单据")
    @RequestMapping(path = "/findPlanByConditions", method = RequestMethod.POST)
    public ResponseResult findPlanByConditions(@RequestBody ConditionQueryPlanBill conditionQueryPlanBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", abstractBillExtraManager.findBillPlanByCondition(conditionQueryPlanBill));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 根据单据号查询计划单据详细信息
     *
     * @param billCode 单据号
     * @return
     */
    @ApiOperation(value = "根据单据号查询计划单据详细信息")
    @RequestMapping(path = "/findPlanByBillCode", method = RequestMethod.GET)
    public ResponseResult findPlanByBillCode(@RequestParam("billCode") String billCode, BillTypeEnum billTypeEnum) {
        ResponseResult responseResult = new ResponseResult();
        try {
            //responseResult.put("planBill", planBillManager.findChildPlanBillByBillCodeAndType(billCode, BillTypeEnum.ADJUST));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 多条件分页查询出库单据
     *
     * @param conditionQueryAdjustBill 查询条件DTO
     * @return
     */
    @ApiOperation(value = "多条件分页查询出库单据")
    @RequestMapping(path = "/findOutStorageByConditions", method = RequestMethod.POST)
    @ScopeAuth(scopes = {"#conditionQueryAdjustBill.outStationCodeArray", "#conditionQueryAdjustBill.inStationCodeArray"}, token = "userCode")
    public ResponseResult findOutStorageByConditions(@RequestBody ConditionQueryAdjustBill conditionQueryAdjustBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            //responseResult.put("adjustBill", abstractBillExtraManager.findOneByBillCode(billCode));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 根据单据号查询出库单据详细信息
     *
     * @param billCode 单据号
     * @return
     */
    @ApiOperation(value = "根据单据号查询出库单据详细信息")
    @RequestMapping(path = "/findOutStorageByBillCode", method = RequestMethod.GET)
    public ResponseResult findOutStorageByBillCode(@RequestParam String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 多条件分页查询入库单据
     *
     * @param conditionQueryAdjustBill 查询条件DTO
     * @return
     */
    @ApiOperation(value = "多条件分页查询入库单据")
    @RequestMapping(path = "/findInStorageByConditions", method = RequestMethod.POST)
    @ScopeAuth(scopes = {"#conditionQueryPlanBill.outStationCodeArray", "#conditionQueryPlanBill.inStationCodeArray"}, token = "userCode")
    public ResponseResult findInStorageByConditions(@RequestBody ConditionQueryAdjustBill conditionQueryAdjustBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
//            responseResult.put("adjustBill", adjustBillManager.findByBillCode(billCode));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 根据单据号查询入库单据详细信息
     *
     * @param billCode 单据号
     * @return
     */
    @ApiOperation(value = "根据单据号查询入库单据详细信息")
    @RequestMapping(path = "/findInStorageByBillCode", method = RequestMethod.GET)
    public ResponseResult findInStorageByBillCode(@RequestParam String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 根据源单号查询单据详细信息
     *
     * @param sourceCode
     * @return
     */
    @ApiOperation(value = "根据源单号查询单据详细信息")
    @RequestMapping(path = "/findBySourceCode", method = RequestMethod.GET)
    public ResponseResult findBySourceCode(@RequestParam("sourceCode") String sourceCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            //responseResult.put("restockBill", abstractBillManager.findOneByBillCode(sourceCode));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 根据单据号查询单据详细信息
     *
     * @param billCode 单据号
     * @return
     */
    @ApiOperation(value = "根据单据号查询详细信息")
    @RequestMapping(path = "/findByBillCode", method = RequestMethod.GET)
    public ResponseResult findByBillCode(@RequestParam(value = "billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("adjustBill", abstractBillExtraManager.findOneByBillCode(billCode));
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

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
    public ResponseResult auditFailure(HttpServletRequest request, @RequestParam(value = "billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
            abstractBillExtraManager.auditBill(billCode, loginInfo.getOperatorCode(), false);
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
    public ResponseResult auditSuccess(HttpServletRequest request, @RequestParam(value = "billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
            abstractBillExtraManager.auditBill(billCode, loginInfo.getOperatorCode(), true);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

}
