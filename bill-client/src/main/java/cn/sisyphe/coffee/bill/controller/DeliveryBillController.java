package cn.sisyphe.coffee.bill.controller;


import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBillDetail;
import cn.sisyphe.coffee.bill.viewmodel.delivery.ConditionQueryDeliveryBill;
import cn.sisyphe.coffee.bill.viewmodel.delivery.DeliveryBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.delivery.DeliveryBillDetailDTO;
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
 * 配送单
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/4 10:25
 **/
@RestController
@RequestMapping("/api/bill/delivery")
@Api(description = "配送相关接口")
@CrossOrigin(origins = "*")
public class DeliveryBillController extends BillController<DeliveryBill, DeliveryBillDTO, ConditionQueryDeliveryBill, DeliveryBillDetailDTO> {

    @Autowired
    public DeliveryBillController(AbstractBillExtraManager<DeliveryBill, DeliveryBillDTO, ConditionQueryDeliveryBill, DeliveryBillDetailDTO> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult save(@RequestBody DeliveryBillDTO billDTO, HttpServletRequest request) {
        return super.save(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult saveBySelf(@RequestBody DeliveryBillDTO billDTO, HttpServletRequest request) {
        return super.saveBySelf(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult submit(@RequestBody DeliveryBillDTO billDTO, HttpServletRequest request) {
        return super.submit(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult submitBySelf(@RequestBody DeliveryBillDTO billDTO, HttpServletRequest request) {
        return super.submitBySelf(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult auditFailure(@RequestBody DeliveryBillDTO billDTO, HttpServletRequest request) {
        return super.auditFailure(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult auditSuccess(@RequestBody DeliveryBillDTO billDTO, HttpServletRequest request) {
        return super.auditSuccess(billDTO, request);
    }
}
