package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.planbill.PlanBillManager;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.coffee.bill.viewmodel.planbill.QueryPlanBillDTO;
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
 * @author ncmao
 * @Date 2017/12/27 11:31
 * @description
 */
@RestController
@RequestMapping("/api/bill/planbill")
@Api(description = "总部计划中心")
@CrossOrigin(origins = "*")
public class PlanBillController {


    @Autowired
    private PlanBillManager planBillManager;


    @ApiOperation(value = "新建总部计划，暂存")
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseResult createPlanBill(@RequestBody PlanBillDTO planBillDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("billCode", planBillManager.create(planBillDTO));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    @ApiOperation(value = "提交总部计划")
    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    public ResponseResult submitPlanBill(@RequestBody PlanBillDTO planBillDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("billCode", planBillManager.submit(planBillDTO));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    @ApiOperation(value = "审核总部计划")
    @RequestMapping(path = "/open", method = RequestMethod.POST)
    public ResponseResult openPlanBill(@RequestParam("billCode") String billCode) {
        planBillManager.open(billCode);
        return null;
    }

    @ApiOperation(value = "审核不通过")
    @RequestMapping(path = "/unpass", method = RequestMethod.GET)
    public ResponseResult unpass(@RequestParam("billCode") String billCode) {
        planBillManager.unPass(billCode);
        return null;
    }

    @ApiOperation(value = "审核通过")
    @RequestMapping(path = "/pass", method = RequestMethod.GET)
    public ResponseResult pass(@RequestParam("billCode") String billCode) {
        planBillManager.pass(billCode);
        return null;
    }

    /**
     * 计划单据多条件分页查询
     *
     * @return
     */
    @ApiOperation(value = "计划单据多条件分页查询")
    @RequestMapping(path = "/findPlanBillByConditions", method = RequestMethod.POST)
    public ResponseResult findPlanBillByConditions(@RequestBody ConditionQueryPlanBill conditionQueryPlanBill) {
        ResponseResult responseResult = new ResponseResult();
        QueryPlanBillDTO billPage = planBillManager.findPageByCondition(conditionQueryPlanBill);
        responseResult.put("content", billPage);
        return responseResult;
    }

    /**
     * 计划单据根据billCode查询
     *
     * @return
     */
    @ApiOperation(value = "计划单据根据billCode查询")
    @RequestMapping(path = "/findPlanBillByBillCode", method = RequestMethod.POST)
    public ResponseResult findPlanBillByBillCode(@RequestBody String billCode) {
        ResponseResult responseResult = new ResponseResult();
        PlanBill planBill = planBillManager.findPlanBillByBillCode(billCode);
        responseResult.put("content", planBill);
        return responseResult;
    }

    @ApiOperation(value = "根据单据编号查询单据明细")
    @RequestMapping(path = "/findByBillCode", method = RequestMethod.GET)
    public ResponseResult findByBillCode(@RequestParam("billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("planBill",planBillManager.findByBillCode(billCode));
        return responseResult;
    }
}
