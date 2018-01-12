package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author bifenglin
 * @Date 2018/1/12
 * @description
 */
public class AllotBillController {

    @Autowired
    AllotBillManager allotBillManager;

    /**
     * 计划单据多条件分页查询
     *
     * @return
     */
    @ApiOperation(value = "子计划多条件查询")
    @RequestMapping(path = "/findPlanBillByConditions", method = RequestMethod.POST)
    public ResponseResult findAllotBillByConditions(@RequestBody ConditionQueryPlanBill conditionQueryPlanBill) {
        ResponseResult responseResult = new ResponseResult();
        System.err.print("子计划多条件查询开始");
        try {
            Page<AllotBillDTO> planBillDTOS = allotBillManager.findAllotBillByCondition(conditionQueryPlanBill, BillTypeEnum.RESTOCK);

            for (AllotBillDTO allotBillDTO : planBillDTOS) {
                //测试使用
                allotBillDTO.setOperatorName("操作人：懒羊羊");
            }

            responseResult.put("content", planBillDTOS);
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }
}
