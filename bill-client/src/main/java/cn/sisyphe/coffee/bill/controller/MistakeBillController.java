package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.mistake.MistakeBillManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBillDetail;
import cn.sisyphe.coffee.bill.domain.shared.LoginInfo;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import cn.sisyphe.coffee.bill.viewmodel.mistake.ConditionQueryMistakeBill;
import cn.sisyphe.coffee.bill.viewmodel.mistake.MistakeBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.mistake.MistakeBillDetailDTO;
import cn.sisyphe.framework.auth.logic.annotation.ScopeAuth;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amy on 2018/1/18.
 *         describe：误差单相关接口
 *         包括流转误差，日常（无计划）误差，报溢，报损
 */
@RestController
@Api(description = "误差相关接口")
@RequestMapping("/api/bill/mistake")
@CrossOrigin(origins = "*")
public class MistakeBillController extends BillController<MistakeBill, MistakeBillDTO, ConditionQueryMistakeBill, MistakeBillDetailDTO> {

    @Autowired
    public MistakeBillController(AbstractBillExtraManager<MistakeBill, MistakeBillDTO, ConditionQueryMistakeBill, MistakeBillDetailDTO> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }

    @Autowired
    private MistakeBillManager mistakeBillManager;

    /**
     * 流转误差单的条件筛选
     * @param conditionQueryAllotBill 查询条件DTO
     * @return
     */
//    @ScopeAuth(scope = "#conditionQueryAllotBill.inStationCodes", token = "userCode")
    @RequestMapping(path = "/findMistakeByConditions", method = RequestMethod.POST)
    public ResponseResult findMistakeByConditions(@RequestBody ConditionQueryAllotBill conditionQueryAllotBill) {
        conditionQueryAllotBill.setMistakeBillQuery(true);
        return super.findAllotByConditions(conditionQueryAllotBill);
    }

    /**
     * 查询流转误差单详情（实质为调拨单信息）
     *
     * @param billCode 单据号
     * @return
     */
    @Override
    public ResponseResult findAllotByBillCode(String billCode) {
        //查询调拨单DTO
        ResponseResult responseResult = super.findAllotByBillCode(billCode);
        //过滤掉无误差的明细
        responseResult = mistakeBillManager.allotBillFilter(responseResult);
        return responseResult;
    }

    /**
     * 提交报溢单
     *
     * @param billDTO
     * @param request
     * @return
     */
    @ScopeAuth(scope = "#billDTO.inLocation.stationCode", token = "userCode")
    @RequestMapping(path = "/submitOverFlow", method = RequestMethod.POST)
    public ResponseResult submitOverFlow(@RequestBody MistakeBillDTO billDTO,HttpServletRequest request) {
        ResponseResult responseResult = new ResponseResult();
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        billDTO.setOperatorCode(loginInfo.getOperatorCode());
        billDTO.setBelongStationCode(loginInfo.getStationCode());
        try {
            responseResult.put("billCode", mistakeBillManager.submitOverFlow(billDTO));
        } catch (DataException date) {
            responseResult.putException(date);
        }

        return responseResult;
    }

    /**
     * 提交报损单
     *
     * @param request
     * @param billDTO
     * @return
     */
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    @RequestMapping(path = "/submitLoss", method = RequestMethod.POST)
    public ResponseResult submitLoss(@RequestBody MistakeBillDTO billDTO,HttpServletRequest request) {
        ResponseResult responseResult = new ResponseResult();
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        billDTO.setOperatorCode(loginInfo.getOperatorCode());
        billDTO.setBelongStationCode(loginInfo.getStationCode());
        try {
            responseResult.put("billCode", mistakeBillManager.submitLoss(billDTO));
        } catch (DataException date) {
            responseResult.putException(date);
        }

        return responseResult;
    }

    /**
     * 提交日常误差单
     *
     * @param request
     * @param billDTO
     * @return
     */
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    @RequestMapping(path = "/submitDayMistake", method = RequestMethod.POST)
    public ResponseResult submitDayMistake(@RequestBody MistakeBillDTO billDTO,HttpServletRequest request) {
        ResponseResult responseResult = new ResponseResult();
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        billDTO.setOperatorCode(loginInfo.getOperatorCode());
        billDTO.setBelongStationCode(loginInfo.getStationCode());
        try {
            responseResult.put("billCode", mistakeBillManager.submitDayMistake(billDTO));
        } catch (DataException date) {
            responseResult.putException(date);
        }
        return responseResult;
    }

    /**
     * 条件筛选报溢单
     *
     * @param conditionQueryMistakeBill
     * @return
     */
    @ScopeAuth(scope = "#conditionQueryMistakeBill.inStationCodes", token = "userCode")
    @RequestMapping(path = "/findOverFlowByConditions", method = RequestMethod.POST)
    public ResponseResult findOverFlowByConditions(@RequestBody ConditionQueryMistakeBill conditionQueryMistakeBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("billList", mistakeBillManager.findOverFlowByConditions(conditionQueryMistakeBill));
        } catch (DataException date) {
            responseResult.putException(date);
        }
        return responseResult;
    }

    /**
     * 条件筛选报损单
     *
     * @param conditionQueryMistakeBill
     * @return
     */
    @ScopeAuth(scope = "#conditionQueryMistakeBill.outStationCodes", token = "userCode")
    @RequestMapping(path = "/findLossByConditions", method = RequestMethod.POST)
    public ResponseResult findLossByConditions(@RequestBody ConditionQueryMistakeBill conditionQueryMistakeBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("billList", mistakeBillManager.findLossByConditions(conditionQueryMistakeBill));
        } catch (DataException date) {
            responseResult.putException(date);
        }
        return responseResult;
    }

    /**
     * 条件筛选日常误差单
     *
     * @param conditionQueryMistakeBill
     * @return
     */
    @ScopeAuth(scope = "#conditionQueryMistakeBill.outStationCodes", token = "userCode")
    @RequestMapping(path = "/findDayMistakeByConditions", method = RequestMethod.POST)
    public ResponseResult findDayMistakeByConditions(@RequestBody ConditionQueryMistakeBill conditionQueryMistakeBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("billList", mistakeBillManager.findDayMistakeByConditions(conditionQueryMistakeBill));
        } catch (DataException date) {
            responseResult.putException(date);
        }
        return responseResult;
    }

    /**
     * 根据单号查询报溢单详情
     *
     * @param billCode
     * @return
     */
    @RequestMapping(path = "/findOverFlowByBillCode", method = RequestMethod.GET)
    public ResponseResult findOverFlowByBillCode(@RequestParam("billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", mistakeBillManager.findMistakeBillDTOByBillCode(billCode, BillPurposeEnum.IN_STORAGE));
        } catch (DataException date) {
            responseResult.putException(date);
        }
        return responseResult;
    }

    /**
     * 根据单号查询报损单详情
     *
     * @param billCode
     * @return
     */
    @RequestMapping(path = "/findLossByBillCode", method = RequestMethod.GET)
    public ResponseResult findLossByBillCode(@RequestParam("billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", mistakeBillManager.findMistakeBillDTOByBillCode(billCode, BillPurposeEnum.OUT_STORAGE));
        } catch (DataException date) {
            responseResult.putException(date);
        }
        return responseResult;
    }

    /**
     * 根据单号查询日常误差单的详情
     *
     * @param billCode
     * @return
     */
    @RequestMapping(path = "/findDayMistakeByBillCode", method = RequestMethod.GET)
    public ResponseResult findDayMistakeByBillCode(@RequestParam("billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", mistakeBillManager.findMistakeBillDTOByBillCode(billCode, BillPurposeEnum.MOVE_STORAGE));
        } catch (DataException date) {
            responseResult.putException(date);
        }
        return responseResult;
    }
}
