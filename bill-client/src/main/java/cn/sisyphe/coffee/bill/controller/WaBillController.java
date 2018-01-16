package cn.sisyphe.coffee.bill.controller;

import cn.sisyphe.coffee.bill.viewmodel.adjust.AddAdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.framework.web.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by heyong on 2018/1/16 14:20
 * Description:
 */
@RestController
@RequestMapping("/api/bill/wx")
@Api(description = "---调剂计划相关接口")
@CrossOrigin(origins = "*")
public class WaBillController extends BillController{

    /**
     * 计划单据多条件分页查询
     *
     * @param conditionQueryPlanBill
     * @return
     */
    @Override
    public ResponseResult findPlanByConditions(ConditionQueryPlanBill conditionQueryPlanBill) {
        return super.findPlanByConditions(conditionQueryPlanBill);
    }

    /**
     * 子计划直接查看已保存的拣货单
     *
     * @param sourceCode
     * @return
     */
    @Override
    public ResponseResult findBySourceCode(String sourceCode) {
        return super.findBySourceCode(sourceCode);
    }

    /**
     * 查询子计划单个查询
     *
     * @param billCode
     * @return
     */
    @Override
    public ResponseResult findPlanByBillCode(String billCode) {
        return super.findPlanByBillCode(billCode);
    }

    /**
     * 多条件查询调剂出库单据
     *
     * @param conditionQueryAdjustBill 查询条件
     * @return
     */
    @Override
    public ResponseResult findOutStorageByConditions(ConditionQueryAdjustBill conditionQueryAdjustBill) {
        return super.findOutStorageByConditions(conditionQueryAdjustBill);
    }

    /**
     * 多条件查询调剂入库单据
     *
     * @param conditionQueryAdjustBill 查询条件
     * @return
     */
    @Override
    public ResponseResult findInStorageByConditions(ConditionQueryAdjustBill conditionQueryAdjustBill) {
        return super.findInStorageByConditions(conditionQueryAdjustBill);
    }

    /**
     * 根据调剂单号查询详细信息
     *
     * @param billCode 调剂单号
     * @return
     */
    @Override
    public ResponseResult findByBillCode(String billCode) {
        return super.findByBillCode(billCode);
    }

    /**
     * 审核按钮--打开操作
     *
     * @param request
     * @param billCode 调剂单号
     * @return
     */
    @Override
    public ResponseResult open(HttpServletRequest request, String billCode) {
        return super.open(request, billCode);
    }

    /**
     * 保存调剂单据信息
     *
     * @param request
     * @param addAdjustBillDTO
     * @return
     */
    @Override
    public ResponseResult save(HttpServletRequest request, AddAdjustBillDTO addAdjustBillDTO) {
        return super.save(request, addAdjustBillDTO);
    }

    /**
     * 提交调剂单据信息
     *
     * @param request
     * @param addAdjustBillDTO
     * @return
     */
    @Override
    public ResponseResult submit(HttpServletRequest request, AddAdjustBillDTO addAdjustBillDTO) {
        return super.submit(request, addAdjustBillDTO);
    }

    /**
     * 审核不通过
     *
     * @param request
     * @param adjustBillCode 调剂单编码
     * @return
     */
    @Override
    public ResponseResult auditFailure(HttpServletRequest request, String adjustBillCode) {
        return super.auditFailure(request, adjustBillCode);
    }

    /**
     * 审核通过
     *
     * @param request
     * @param adjustBillCode 调剂单编码
     * @return
     */
    @Override
    public ResponseResult auditSuccess(HttpServletRequest request, String adjustBillCode) {
        return super.auditSuccess(request, adjustBillCode);
    }
}
