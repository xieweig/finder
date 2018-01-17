package cn.sisyphe.coffee.bill.controller;


import cn.sisyphe.coffee.bill.application.delivery.DeliveryBillManager;
import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.delivery.AuditDeliveryBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.delivery.ConditionQueryDeliveryBill;
import cn.sisyphe.coffee.bill.viewmodel.delivery.DeliveryPickingEditDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
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
 * 配送单
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/4 10:25
 **/
@RestController
@RequestMapping("/api/bill/deliverybill")
@Api(description = "配送计划相关接口")
@CrossOrigin(origins = "*")
public class DeliveryBillController {

    //配送拣货
    @Autowired
    private DeliveryBillManager deliveryBillManager;

    //计划单
    @Autowired
    private PlanBillManager planBillManager;

    /**
     * 计划单据多条件分页查询(根据传入的单据类型返回不同单据)
     *
     * @return
     */
    @ApiOperation(value = "配送计划单据多条件分页查询")
    @RequestMapping(path = "/findPlanBillByConditions", method = RequestMethod.POST)
    public ResponseResult findPlanBillByConditions(@RequestBody ConditionQueryPlanBill conditionQueryPlanBill) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("content", planBillManager.findPageByCondition(conditionQueryPlanBill));
        return responseResult;
    }

    /**
     * 根据单据编号查询配送单信息
     *
     * @param billCode
     * @return
     */
    @ApiOperation(value = "根据单据编号查询配送单信息")
    @RequestMapping(path = "/findPlanByBillCode", method = RequestMethod.GET)
    public ResponseResult findPlanByBillCode(@RequestParam("billCode") String billCode) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("planBill", planBillManager.findChildPlanBillByBillCodeAndType(billCode, BillTypeEnum.DELIVERY));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 按货物拣货
     *
     * @param deliveryBillDTO
     * @return
     */
    @ApiOperation(value = "站点配送计划拣货 按货物拣货,保存")
    @RequestMapping(path = "/pickingByCargo", method = RequestMethod.POST)
    public ResponseResult pickingByCargo(@RequestBody DeliveryPickingEditDTO deliveryBillDTO) {
        ResponseResult responseResult = new ResponseResult();

        try {
            deliveryBillManager.pickingByCargo(deliveryBillDTO);
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }


    /**
     * 按原料拣货
     *
     * @param deliveryBillDTO
     * @return
     */
    @ApiOperation(value = "站点配送计划拣货 按原料拣货,保存")
    @RequestMapping(path = "/pickingByRawMaterial", method = RequestMethod.POST)
    public ResponseResult pickingByRawMaterial(@RequestBody DeliveryPickingEditDTO deliveryBillDTO) {
        ResponseResult responseResult = new ResponseResult();

        try {
            deliveryBillManager.pickingByRawMaterial(deliveryBillDTO);
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }

    /**
     * 拣货提交
     *
     * @param deliveryBillDTO
     * @return
     */
    @ApiOperation(value = "拣货提交")
    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    public ResponseResult submitWhenDone(@RequestBody DeliveryPickingEditDTO deliveryBillDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            deliveryBillManager.submitWhenDone(deliveryBillDTO);
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }


    /**
     * 根据单号查询配送单
     *
     * @param billCode
     * @return
     */
    @ApiOperation(value = "根据单号查询配送单")
    @RequestMapping(path = "/findOneDeliveryBillByBillCode", method = RequestMethod.GET)
    public ResponseResult findOneDeliveryBillByBillCode(@RequestParam(required = true) String billCode) {

        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("deliveryBill", deliveryBillManager.findOneDeliveryBillByBillCode(billCode));
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;

    }

    /**
     * 运单多条件分页查询
     *
     * @param conditionQueryDeliveryBill
     * @return
     */
    @ApiOperation(value = "配送单多条件分页查询")
    @RequestMapping(path = "/findDeliveryBillByConditions", method = RequestMethod.POST)
    public ResponseResult findDeliveryBillByConditions(@RequestBody ConditionQueryDeliveryBill conditionQueryDeliveryBill) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.put("deliveryBillList", deliveryBillManager.findDeliveryBillsByCondition(conditionQueryDeliveryBill));

        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }


    /**
     * 审核不通过
     *
     * @param auditDeliveryBillDTO
     * @return
     */
    @ApiOperation(value = "审核不通过")
    @RequestMapping(path = "/auditNotPass", method = RequestMethod.POST)
    public ResponseResult auditNotPass(@RequestBody AuditDeliveryBillDTO auditDeliveryBillDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            deliveryBillManager.auditNotPass(auditDeliveryBillDTO);
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }


    /**
     * 审核通过
     *
     * @param auditDeliveryBillDTO
     * @return
     */
    @ApiOperation(value = "审核通过")
    @RequestMapping(path = "/auditPass", method = RequestMethod.POST)
    public ResponseResult auditPass(@RequestBody AuditDeliveryBillDTO auditDeliveryBillDTO) {
        ResponseResult responseResult = new ResponseResult();
        try {
            deliveryBillManager.auditPass(auditDeliveryBillDTO);
        } catch (DataException e) {
            responseResult.putException(e);
        }
        return responseResult;
    }
}
