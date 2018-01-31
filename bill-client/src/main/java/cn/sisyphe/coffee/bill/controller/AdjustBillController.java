package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillExtraManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.base.BillController;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
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
 * Created by XiongJing on 2018/1/8.
 * remark：调剂业务控制层
 * version: 1.0
 *
 * @author XiongJing
 */
@RestController
@RequestMapping("/api/bill/adjust")
@Api(description = "调剂相关接口")
@CrossOrigin(origins = "*")
public class AdjustBillController extends BillController<AdjustBill, AdjustBillDTO, ConditionQueryAdjustBill, AdjustBillDetailDTO> {


    @Autowired
    public AdjustBillController(AbstractBillExtraManager<AdjustBill, AdjustBillDTO, ConditionQueryAdjustBill, AdjustBillDetailDTO> abstractBillExtraManager, PlanBillManager planBillManager, AllotBillManager allotBillManager) {
        super(abstractBillExtraManager, planBillManager, allotBillManager);
    }


    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult save(@RequestBody AdjustBillDTO billDTO, HttpServletRequest request) {
        return super.save(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult saveBySelf(@RequestBody AdjustBillDTO billDTO, HttpServletRequest request) {
        return super.saveBySelf(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult submit(@RequestBody AdjustBillDTO billDTO, HttpServletRequest request) {
        return super.submit(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult submitBySelf(@RequestBody AdjustBillDTO billDTO, HttpServletRequest request) {
        return super.submitBySelf(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult auditFailure(AdjustBillDTO billDTO, HttpServletRequest request) {
        return super.auditFailure(billDTO, request);
    }

    @Override
    @ScopeAuth(scope = "#billDTO.outLocation.stationCode", token = "userCode")
    public ResponseResult auditSuccess(AdjustBillDTO billDTO, HttpServletRequest request) {
        return super.auditSuccess(billDTO, request);
    }
}
