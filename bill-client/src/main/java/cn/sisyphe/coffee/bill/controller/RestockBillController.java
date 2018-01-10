package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.planbill.PlanBillManager;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.shared.LoginInfo;
import cn.sisyphe.coffee.bill.viewmodel.waybill.ScanFillBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.child.ChildPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
import cn.sisyphe.coffee.bill.viewmodel.restock.QueryRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2018/1/3
 * @description:
 * @author：bifenglin
 */
@RequestMapping("/api/bill/restock")
@RestController
@CrossOrigin(origins = "*")
@Api(description = "退库相关操作")
public class RestockBillController {

    @Resource
    private RestockBillManager restockBillManager;
    @Resource
    private PlanBillManager planBillManager;
    @Resource
    private SharedManager sharedManager;

    /**
     * 计划单据多条件分页查询
     *
     * @return
     */
    @ApiOperation(value = "子计划多条件查询")
    @RequestMapping(path = "/findPlanBillByConditions", method = RequestMethod.POST)
    public ResponseResult findChildPlanBillByConditions(@RequestBody ConditionQueryPlanBill conditionQueryPlanBill) {
        ResponseResult responseResult = new ResponseResult();
        System.err.print("子计划多条件查询开始");
        //设定查询退库分片
        conditionQueryPlanBill.setSpecificBillType(BillTypeEnum.RESTOCK);
        try {
            Page<ChildPlanBillDTO> planBillDTOS = planBillManager.findChildPlanBillByCondition(conditionQueryPlanBill);

            List<String> restockCodeList = new ArrayList<>();
            for (ChildPlanBillDTO childPlanBillDTO : planBillDTOS) {
                //测试使用
                childPlanBillDTO.setOperatorName("操作人：懒羊羊");
//                restockCodeList.add(childPlanBillDTO.getBillCode());
            }

            responseResult.put("content", planBillDTOS);
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    @ApiOperation(value = "子计划直接查看已保存的拣货单")
    @RequestMapping(path = "/findRestockBillBySourceCode", method = RequestMethod.GET)
    public ResponseResult findRestockBillBySourceCode(@RequestParam("sourceCode") String sourceCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("restockBill", restockBillManager.findRestockBillBySourceCode(sourceCode));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }


    @ApiOperation(value = "子计划单个查询")
    @RequestMapping(path = "/findPlanBillByBillCode", method = RequestMethod.POST)
    public ResponseResult findByBillCode(@RequestParam("billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("planBill", planBillManager.findChildPlanBillByBillCodeAndType(billCode, BillTypeEnum.RESTOCK));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 保存退库出库单
     *
     * @param addRestockBillDTO
     * @return
     */
    @ApiOperation(value = "保存退库出库单  ")
    @RequestMapping(path = "/saveRestockBill", method = RequestMethod.POST)
    public ResponseResult saveRestockBill(HttpServletRequest request, @RequestBody AddRestockBillDTO addRestockBillDTO) {
//        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
//        addRestockBillDTO.setOperatorCode(loginInfo.getOperatorCode());
        ResponseResult responseResult = new ResponseResult();
        restockBillManager.saveBill(addRestockBillDTO);
        return responseResult;
    }

    /**
     * 提交退库单
     *
     * @param addRestockBillDTO
     * @return
     */
    @Transactional
    @ApiOperation(value = "提交退库出库单")
    @RequestMapping(path = "/submitRestockBill", method = RequestMethod.POST)
    public ResponseResult submitRestockBill(HttpServletRequest request, @RequestBody AddRestockBillDTO addRestockBillDTO) {
//        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
//        addRestockBillDTO.setOperatorCode(loginInfo.getOperatorCode());
        ResponseResult responseResult = new ResponseResult();
        restockBillManager.submitBill(addRestockBillDTO);

        return responseResult;
    }

    /**
     * 多条件查询退库入库单
     *
     * @param conditionQueryRestockBill
     * @return
     */
    @ApiOperation(value = "多条件查询退库出库单")
    @RequestMapping(path = "/findByConditions", method = RequestMethod.POST)
    public ResponseResult findByConditions(@RequestBody ConditionQueryRestockBill conditionQueryRestockBill) {
        ResponseResult responseResult = new ResponseResult();

        QueryRestockBillDTO billPage = restockBillManager.findByConditions(conditionQueryRestockBill);
        List<RestockBillDTO> list = billPage.getContent();
        //测试使用
        for (RestockBillDTO restockBillDTO:
             list) {
            restockBillDTO.setAuditPersonName("审核人：海绵宝宝");
            restockBillDTO.setOperatorName("操作人：派大星");
        }
        responseResult.put("content", billPage);
        return responseResult;
    }

    /**
     * @param restockBillCode
     * @return
     */
    @ApiOperation(value = "根据退库出库单编码查询图库出库单详细信息")
    @RequestMapping(path = "/openByRestockBillCode", method = RequestMethod.GET)
    public ResponseResult openByRestockBillCode(@RequestParam String restockBillCode) {
        ResponseResult responseResult = new ResponseResult();
//        QueryOneRestockBillDTO billDTO = restockBillManager.openBill(RestockBillCode);
        RestockBill billDTO = restockBillManager.openBill(restockBillCode);
        responseResult.put("RestockBill", billDTO);
        return responseResult;
    }

    /**
     * @param restockBillCode
     * @return
     */
    @ApiOperation(value = "根据退库出库单编码查询图库出库单详细信息")
    @RequestMapping(path = "/findByRestockBillCode", method = RequestMethod.GET)
    public ResponseResult findByRestockBillCode(@RequestParam String restockBillCode) {
        ResponseResult responseResult = new ResponseResult();
//        QueryOneRestockBillDTO billDTO = restockBillManager.openBill(RestockBillCode);
        RestockBill billDTO = restockBillManager.findByRestockBillCode(restockBillCode);
        responseResult.put("RestockBill", billDTO);
        return responseResult;
    }

    /**
     * 修改退库出库单单据信息
     *
     * @param billDTO
     * @return
     */
    @ApiOperation(value = "修改退库出库单单据信息--保存")
    @RequestMapping(path = "/updateRestockBillToSave", method = RequestMethod.POST)
    public ResponseResult updateRestockBillToSaved(HttpServletRequest request, @RequestBody AddRestockBillDTO billDTO) {
        ResponseResult responseResult = new ResponseResult();
//        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
//        billDTO.setOperatorCode(loginInfo.getOperatorCode());
        try {
            restockBillManager.updateBillToSave(billDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 修改入库单据信息
     *
     * @param billDTO
     * @return
     */
    @ApiOperation(value = "修改退库出库单单据信息--提交审核")
    @RequestMapping(path = "/updateRestockBillToSubmit", method = RequestMethod.POST)
    public ResponseResult updateRestockBillToSubmit(@RequestBody AddRestockBillDTO billDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            restockBillManager.updateBillToSubmit(billDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 审核不通过
     *
     * @param restockBillCode 退库出库单单编码
     * @return
     */
    @ApiOperation(value = "审核不通过")
    @RequestMapping(path = "/auditFailure", method = RequestMethod.POST)
    public ResponseResult auditFailure(@RequestParam String restockBillCode, HttpServletRequest request) {
//        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        ResponseResult responseResult = new ResponseResult();
        restockBillManager.auditBill(restockBillCode, "001", false);
        return responseResult;
    }

    /**
     * 审核通过
     *
     * @param restockBillCode 退库出库单单编码
     * @return
     */
    @ApiOperation(value = "审核通过")
    @RequestMapping(path = "/auditSuccess", method = RequestMethod.POST)
    public ResponseResult auditSuccess(@RequestParam String restockBillCode, HttpServletRequest request) {

        ResponseResult responseResult = new ResponseResult();
//        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);

        restockBillManager.auditBill(restockBillCode,  "001", true);
        return responseResult;
    }
    /**
     * 通过单据号billCode汇总查询出打包的信息
     *
     * @param billCode
     * @return responseResult
     */
    @ApiOperation(value = "通过单据号billCode汇总查询出打包的信息")
    @RequestMapping(path = "/findPackageInfoByBillCode", method = RequestMethod.GET)
    public ResponseResult findPackageInfoByBillCode(@RequestParam String billCode) {

        ResponseResult responseResult = new ResponseResult();
        try {
            ScanFillBillDTO scanFillBillDTO = restockBillManager.findPackageInfoByBillCode(billCode);
            responseResult.put("content", scanFillBillDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

}
