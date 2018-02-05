package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import cn.sisyphe.framework.auth.logic.annotation.ScopeAuth;
import cn.sisyphe.framework.web.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bifenglin
 * @Date 2018/1/12
 * @description 只限于其它调拨功能，（流转相关调拨功能包含在 cn.sisyphe.coffee.bill.controller.base.BillController）
 */
@RestController
@RequestMapping("/api/bill/allot")
@Api(description = "调拨相关接口")
@CrossOrigin(origins = "*")
public class AllotBillController extends BillController<AllotBill, AllotBillDTO, ConditionQueryAllotBill, AllotBillDetailDTO> {
    @Autowired
    public AllotBillController(AbstractBillExtraManager<AllotBill, AllotBillDTO, ConditionQueryAllotBill, AllotBillDetailDTO> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult save(@RequestBody AllotBillDTO billDTO, HttpServletRequest request) {
        return super.save(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult saveBySelf(@RequestBody AllotBillDTO billDTO, HttpServletRequest request) {
        return super.saveBySelf(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult submit(@RequestBody AllotBillDTO billDTO, HttpServletRequest request) {
        return super.submit(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult submitBySelf(@RequestBody AllotBillDTO billDTO, HttpServletRequest request) {
        return super.submitBySelf(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult auditFailure(@RequestBody AllotBillDTO billDTO, HttpServletRequest request) {
        return super.auditFailure(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult auditSuccess(@RequestBody AllotBillDTO billDTO, HttpServletRequest request) {
        return super.auditSuccess(billDTO, request);
    }
}
