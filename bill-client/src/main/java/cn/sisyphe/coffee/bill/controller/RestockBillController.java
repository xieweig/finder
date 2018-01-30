package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBillDetail;
import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;
import cn.sisyphe.framework.auth.logic.annotation.ScopeAuth;
import cn.sisyphe.framework.web.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @date: 2018/1/3
 * @description:
 * @author：bifenglin
 */
@RequestMapping("/api/bill/restock")
@RestController
@CrossOrigin(origins = "*")
@Api(description = "退库相关操作")
public class RestockBillController extends BillController<RestockBill, RestockBillDTO, ConditionQueryRestockBill, RestockBillDetailDTO> {

    @Autowired
    public RestockBillController(AbstractBillExtraManager<RestockBill, RestockBillDTO, ConditionQueryRestockBill, RestockBillDetailDTO> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult save(RestockBillDTO billDTO, HttpServletRequest request) {
        return super.save(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult saveBySelf(RestockBillDTO billDTO, HttpServletRequest request) {
        return super.saveBySelf(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult submit(RestockBillDTO billDTO, HttpServletRequest request) {
        return super.submit(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult submitBySelf(RestockBillDTO billDTO, HttpServletRequest request) {
        return super.submitBySelf(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult auditFailure(RestockBillDTO billDTO, HttpServletRequest request) {
        return super.auditFailure(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult auditSuccess(RestockBillDTO billDTO, HttpServletRequest request) {
        return super.auditSuccess(billDTO, request);
    }
}
