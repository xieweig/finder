package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.domain.shared.LoginInfo;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.child.ChildPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.coffee.bill.viewmodel.restock.*;
import cn.sisyphe.coffee.bill.viewmodel.waybill.ScanFillBillDTO;
import cn.sisyphe.framework.web.ResponseResult;
import cn.sisyphe.framework.web.exception.DataException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private RestockBillManager restockBillManager;
    @Autowired
    private PlanBillManager planBillManager;
    @Autowired
    private SharedManager sharedManager;
    @Autowired
    private AllotBillManager allotBillManager;

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
        try {
            Page<ChildPlanBillDTO> planBillDTOS = planBillManager.findChildPlanBillByCondition(conditionQueryPlanBill, BillTypeEnum.RESTOCK, BillPurposeEnum.OUT_STORAGE);

            for (ChildPlanBillDTO childPlanBillDTO : planBillDTOS) {
                //测试使用
//                childPlanBillDTO.setOperatorName("操作人：懒羊羊");
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
    @ApiOperation(value = "保存退库出库单（计划）")
    @RequestMapping(path = "/saveRestockBill", method = RequestMethod.POST)
    public ResponseResult saveRestockBill(HttpServletRequest request, @RequestBody AddRestockBillDTO addRestockBillDTO) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        addRestockBillDTO.setOperatorCode(loginInfo.getOperatorCode());
//        addRestockBillDTO.setOperatorCode("001");
        ResponseResult responseResult = new ResponseResult();
        restockBillManager.saveBill(addRestockBillDTO);
        return responseResult;
    }

    @ApiOperation(value = "保存退库出库单 (站点自主)")
    @RequestMapping(path = "/saveRestockBillBySelf", method = RequestMethod.POST)
    public ResponseResult saveRestockBillBySelf(HttpServletRequest request, @RequestBody AddRestockBillDTO addRestockBillDTO) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        addRestockBillDTO.setOperatorCode(loginInfo.getOperatorCode());
//        addRestockBillDTO.setOperatorCode("001");
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
    @ApiOperation(value = "提交退库出库单")
    @RequestMapping(path = "/submitRestockBill", method = RequestMethod.POST)
    public ResponseResult submitRestockBill(HttpServletRequest request, @RequestBody AddRestockBillDTO addRestockBillDTO) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        addRestockBillDTO.setOperatorCode(loginInfo.getOperatorCode());
//        addRestockBillDTO.setOperatorCode("001");
        ResponseResult responseResult = new ResponseResult();
        restockBillManager.submitBill(addRestockBillDTO);

        return responseResult;
    }

    @ApiOperation(value = "提交退库出库单")
    @RequestMapping(path = "/submitRestockBillBySelf", method = RequestMethod.POST)
    public ResponseResult submitRestockBillBySelf(HttpServletRequest request, @RequestBody AddRestockBillDTO addRestockBillDTO) {
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        addRestockBillDTO.setOperatorCode(loginInfo.getOperatorCode());
//        addRestockBillDTO.setOperatorCode("001");
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

        Page<RestockBillDTO> billPage =  restockBillManager.findByConditions(conditionQueryRestockBill, BillTypeEnum.RESTOCK, BillPurposeEnum.OUT_STORAGE);
        List<RestockBillDTO> list = billPage.getContent();
        //测试使用
//        for (RestockBillDTO restockBillDTO :
//                list) {
//            restockBillDTO.setAuditPersonName("审核人：海绵宝宝");
//            restockBillDTO.setOperatorName("操作人：派大星");
//        }
        responseResult.put("content", billPage);
        return responseResult;
    }

    /**
     * @param restockBillCode
     * @return
     */
    @ApiOperation(value = "入库单审核")
    @RequestMapping(path = "/openByRestockBillCode", method = RequestMethod.GET)
    public ResponseResult openByRestockBillCode(@RequestParam String restockBillCode) {
        ResponseResult responseResult = new ResponseResult();
        RestockBill billDTO = restockBillManager.openBill(restockBillCode);
        responseResult.put("RestockBill", billDTO);
        return responseResult;
    }

    /**
     * @param restockBillCode
     * @return
     */
    @ApiOperation(value = "据退库出库单编码查详细信息以查询")
    @RequestMapping(path = "/findByRestockBillCode", method = RequestMethod.GET)
    public ResponseResult findByRestockBillCode(@RequestParam String restockBillCode) {
        ResponseResult responseResult = new ResponseResult();
        RestockBill billDTO = restockBillManager.findByRestockBillCode(restockBillCode);
        responseResult.put("RestockBill", billDTO);
        return responseResult;
    }

    /**
     * @param restockBillCode
     * @return
     */
    @ApiOperation(value = "据退库出库单编码查详细信息以修改")
    @RequestMapping(path = "/findByRestockBillCodeToEdit", method = RequestMethod.GET)
    public ResponseResult findByRestockBillCodeToEdit(@RequestParam String restockBillCode) {
        ResponseResult responseResult = new ResponseResult();
        RestockBill billDTO = restockBillManager.findByRestockBillCode(restockBillCode);
        responseResult.put("RestockBill", billDTO);
        return responseResult;
    }

    /**
     * @param restockBillCode
     * @return
     */
    @ApiOperation(value = "据退库出库单编码查详细信息以审核")
    @RequestMapping(path = "/findByRestockBillCodeToAudit", method = RequestMethod.GET)
    public ResponseResult findByRestockBillCodeToAudit(@RequestParam String restockBillCode) {
        ResponseResult responseResult = new ResponseResult();
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
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);
        billDTO.setOperatorCode(loginInfo.getOperatorCode());
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
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);

        ResponseResult responseResult = new ResponseResult();
        restockBillManager.auditBill(restockBillCode, loginInfo.getOperatorCode(), false);
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
        LoginInfo loginInfo = LoginInfo.getLoginInfo(request);

        restockBillManager.auditBill(restockBillCode, loginInfo.getOperatorCode(), true);
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

    /**
     * 调拨单据多条件分页查询
     *
     * @return
     */
    @ApiOperation(value = "调拨单据多条件分页查询")
    @RequestMapping(path = "/findAllotBillByConditions", method = RequestMethod.POST)
    public ResponseResult findAllotBillByConditions(@RequestBody ConditionQueryAllotBill conditionQueryAllotBill) {
        ResponseResult responseResult = new ResponseResult();
        System.err.print("调拨单据多条件分页查询");
        try {
            Page<AllotBillDTO> allotBillDTOS = allotBillManager.findAllotBillByCondition(conditionQueryAllotBill, BillTypeEnum.RESTOCK);

//            for (AllotBillDTO allotBillDTO : allotBillDTOS) {
//                //测试使用
//                allotBillDTO.setOperatorName("操作人：懒羊羊");
//            }
            responseResult.put("content", allotBillDTOS);
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 调拨单查询
     *
     * @return
     */
    @ApiOperation(value = "根据单号查询调拨单")
    @RequestMapping(path = "/findAllotBillByBillCode", method = RequestMethod.POST)
    public ResponseResult findAllotBillByBillCode(@RequestBody String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("allotBill", allotBillManager.findAllotBillByBillCode(billCode));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 据入库单编号查找入库单详细信息
     *
     * @param restockBillCode
     * @return
     */
    @ApiOperation(value = "据入库单编号查找入库单详细信息")
    @RequestMapping(path = "/findRestockInStorageBillByRestockBillCode", method = RequestMethod.GET)
    public ResponseResult findRestockInStorageBillByRestockBillCode(@RequestParam String restockBillCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            BillDTO billDTO = restockBillManager.findRestockInStorageBillByRestockBillCode(restockBillCode);
            responseResult.put("content", billDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

    /**
     * 据入库单编号查找入库调拨单详细信息
     *
     * @param restockBillCode
     * @return
     */
    @ApiOperation(value = "据入库单编号查找入库调拨单详细信息")
    @RequestMapping(path = "/findRestockAllotBillByRestockBillCode", method = RequestMethod.GET)
    public ResponseResult findRestockAllotBillByRestockBillCode(@RequestParam String restockBillCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            BillDTO billDTO = restockBillManager.findRestockInStorageBillByRestockBillCode(restockBillCode);
            responseResult.put("content", billDTO);
        } catch (DataException data) {
            responseResult.putException(data);
        }
        return responseResult;
    }

}
