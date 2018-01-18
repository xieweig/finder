package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import cn.sisyphe.coffee.bill.viewmodel.mistake.ConditionQueryMistakeBill;
import cn.sisyphe.coffee.bill.viewmodel.mistake.MistakeBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author Amy on 2018/1/18.
 *         describe：流转误差单相关接口
 */
@RestController
@RequestMapping("/api/bill/mistake")
@CrossOrigin(origins = "*")
public class MistakeBillController extends BillController<MistakeBill, MistakeBillDTO, ConditionQueryMistakeBill> {

    @Autowired
    public MistakeBillController(AbstractBillExtraManager<MistakeBill, MistakeBillDTO, ConditionQueryMistakeBill> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }

    @Autowired
    private AllotBillManager allotBillManager;

    /**
     * 条件筛选查询
     *
     * @return
     */
    @RequestMapping(path = "/findByConditions", method = RequestMethod.POST)
    public ResponseResult findByConditions(@RequestBody ConditionQueryAllotBill conditionQueryAllotBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("bill", allotBillManager.findBillByCondition(conditionQueryAllotBill, BillPurposeEnum.MOVE_STORAGE));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 查询详情
     *
     * @param billCode
     * @return
     */
    @RequestMapping(path = "/findByBillCode", method = RequestMethod.POST)
    public ResponseResult find(@RequestParam("billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            AllotBillDTO dto = allotBillManager.findBillDtoByBillCode(billCode);
            MistakeBillDTO mistakeBillDTO = new MistakeBillDTO();
            if (dto != null && !StringUtils.isEmpty(dto.getMistakeBill().getBillCode())) {
                mistakeBillDTO = abstractBillExtraManager.findBillDtoBySourceCode(dto.getMistakeBill().getBillCode());
                if (mistakeBillDTO == null) {
                    mistakeBillDTO = new MistakeBillDTO();
                }
            } else {
                dto = new AllotBillDTO();
            }
            responseResult.put("bill", dto);
            responseResult.put("billDetails", dto.getBillDetails());
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }


}
