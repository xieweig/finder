package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.coffee.bill.viewmodel.planbill.PlanBillDTO;
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
@RequestMapping("/api/bill/plan")
@Api(description = "总部计划中心")
@CrossOrigin(origins = "*")
public class PlanBillController extends BillController<PlanBill, PlanBillDTO, ConditionQueryPlanBill> {

    @Autowired
    public PlanBillController(AbstractBillExtraManager<PlanBill, PlanBillDTO, ConditionQueryPlanBill> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }


    /**
     * 单个查询总部计划
     *
     * @return
     */
    @ApiOperation(value = "单个查询总部计划")
    @RequestMapping(path = "/hq/findByBillCode", method = RequestMethod.GET)
    public ResponseResult findHqPlanByBillCode(@RequestParam("billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", ((PlanBillManager) abstractBillExtraManager).findHqPlanBillByBillCode(billCode));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }


    /**
     * 多条件分页查询计划单据
     *
     * @return
     */
    @ApiOperation(value = "多条件分页查询计划单据")
    @RequestMapping(path = "/hq/findByConditions", method = RequestMethod.POST)
    public ResponseResult findHqPlanByConditions(@RequestBody ConditionQueryPlanBill conditionQueryPlanBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", ((PlanBillManager) abstractBillExtraManager).findHqPlanBillByConditions(conditionQueryPlanBill));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }


}
