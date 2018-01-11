package cn.sisyphe.coffee.bill.viewmodel.restock;


import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;

import java.util.Date;
import java.util.List;

/**
 * @author mayupeng
 * @Date 2018/01/07
 * @description 退货入库调拨单
 */
public class QueryOneRestockInstoryBillDTO {
    /**
     * 单号
     */
    private String billCode;
    /**
     * 录单时间
     */
    private Date createTime;
    /**
     * 入库时间
     */
    private Date inWareHouseTime;
    /**
     * 录单人
     */
    private String operatorName;
    /**
     * 出库站点
     */
    private AbstractLocation outLocation;
    /**
     * 入库站点
     */
    private AbstractLocation inLocation;
    /**
     * 单据属性
     */
    private SourcePlanTypeEnum billProperty;
    /**
     * 调剂数量
     */
    private Integer totalAmount;
    /**
     * 调剂品种数
     */
    private Integer totalVarietyAmount;
    /**
     * 计划备注
     */
    private String planMemo;
    /**
     * 出库备注
     */
    private String outStorageMemo;
    /**
     * 审核意见
     */
    private String auditMemo;
    /**
     * 退货入库调拨单明细
     */
    private List<QueryOneRestockInstoryBillDetailDTO> billDetailDTOList;

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getInWareHouseTime() {
        return inWareHouseTime;
    }

    public void setInWareHouseTime(Date inWareHouseTime) {
        this.inWareHouseTime = inWareHouseTime;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public AbstractLocation getOutLocation() {
        return outLocation;
    }

    public void setOutLocation(AbstractLocation outLocation) {
        this.outLocation = outLocation;
    }

    public AbstractLocation getInLocation() {
        return inLocation;
    }

    public void setInLocation(AbstractLocation inLocation) {
        this.inLocation = inLocation;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalVarietyAmount() {
        return totalVarietyAmount;
    }

    public void setTotalVarietyAmount(Integer totalVarietyAmount) {
        this.totalVarietyAmount = totalVarietyAmount;
    }

    public String getPlanMemo() {
        return planMemo;
    }

    public void setPlanMemo(String planMemo) {
        this.planMemo = planMemo;
    }

    public String getOutStorageMemo() {
        return outStorageMemo;
    }

    public void setOutStorageMemo(String outStorageMemo) {
        this.outStorageMemo = outStorageMemo;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    public SourcePlanTypeEnum getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(SourcePlanTypeEnum billProperty) {
        this.billProperty = billProperty;
    }

    public List<QueryOneRestockInstoryBillDetailDTO> getBillDetailDTOList() {
        return billDetailDTOList;
    }

    public void setBillDetailDTOList(List<QueryOneRestockInstoryBillDetailDTO> billDetailDTOList) {
        this.billDetailDTOList = billDetailDTOList;
    }
}
