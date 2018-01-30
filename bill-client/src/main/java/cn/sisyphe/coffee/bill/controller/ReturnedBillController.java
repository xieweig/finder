package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBill;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBillDetail;
import cn.sisyphe.coffee.bill.viewmodel.returned.ConditionQueryReturnedBill;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDetailDTO;
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
 * @date: 2018/1/3
 * @description:
 * @author：bifenglin
 */
@RequestMapping("/api/bill/returned")
@RestController
@CrossOrigin(origins = "*")
@Api(description = "退货相关操作")
public class ReturnedBillController extends BillController<ReturnedBill, ReturnedBillDTO, ConditionQueryReturnedBill, ReturnedBillDetailDTO> {

    @Autowired
    public ReturnedBillController(AbstractBillExtraManager<ReturnedBill, ReturnedBillDTO, ConditionQueryReturnedBill, ReturnedBillDetailDTO> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult save(@RequestBody ReturnedBillDTO billDTO, HttpServletRequest request) {
        return super.save(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult saveBySelf(@RequestBody ReturnedBillDTO billDTO, HttpServletRequest request) {
        return super.saveBySelf(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult submit(@RequestBody ReturnedBillDTO billDTO, HttpServletRequest request) {
        return super.submit(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult submitBySelf(@RequestBody ReturnedBillDTO billDTO, HttpServletRequest request) {
        return super.submitBySelf(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult auditFailure(@RequestBody ReturnedBillDTO billDTO, HttpServletRequest request) {
        return super.auditFailure(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult auditSuccess(@RequestBody ReturnedBillDTO billDTO, HttpServletRequest request) {
        return super.auditSuccess(billDTO, request);
    }
}
