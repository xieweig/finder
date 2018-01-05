package cn.sisyphe.coffee.bill.viewmodel;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单据查询条件
 * version: 1.0
 *
 * @author XiongJing
 */
public class ConditionQueryPurchaseBill extends BaseConditionQuery {

    /**
     * 录单人名称
     */
    private String operatorName;
    /**
     * 录单开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createStartTime;
    /**
     * 录单结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createEndTime;
    /**
     * 入库单号
     */
    private String billCode;
    /**
     * 入库开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inStartTime;
    /**
     * 入库结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inEndTime;
    /**
     * 供应商编码
     */
    private String supplierCode;
    /**
     * 提交状态
     */
    private List<String> submitStateCode;

    /**
     * 审核状态
     */
    private List<String> auditStateCode;

    /**
     * 出入库状态
     */
    private List<String> inOrOutStateCode;

    /**
     * 录单人编码集合
     */
    private List<String> operatorCodeList;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(Date createStartTime) {
        this.createStartTime = createStartTime;
    }

    public Date getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(Date createEndTime) {
        this.createEndTime = createEndTime;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public Date getInStartTime() {
        return inStartTime;
    }

    public void setInStartTime(Date inStartTime) {
        this.inStartTime = inStartTime;
    }

    public Date getInEndTime() {
        return inEndTime;
    }

    public void setInEndTime(Date inEndTime) {
        this.inEndTime = inEndTime;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public List<String> getSubmitStateCode() {
        return submitStateCode;
    }

    public void setSubmitStateCode(List<String> submitStateCode) {
        this.submitStateCode = submitStateCode;
    }

    public List<String> getAuditStateCode() {
        return auditStateCode;
    }

    public void setAuditStateCode(List<String> auditStateCode) {
        this.auditStateCode = auditStateCode;
    }

    public List<String> getInOrOutStateCode() {
        return inOrOutStateCode;
    }

    public void setInOrOutStateCode(List<String> inOrOutStateCode) {
        this.inOrOutStateCode = inOrOutStateCode;
    }

    public List<String> getOperatorCodeList() {
        return operatorCodeList;
    }

    public void setOperatorCodeList(List<String> operatorCodeList) {
        this.operatorCodeList = operatorCodeList;
    }

    @Override
    public String toString() {
        return "ConditionQueryPurchaseBill{" +
                "operatorName='" + operatorName + '\'' +
                ", createStartTime=" + createStartTime +
                ", createEndTime=" + createEndTime +
                ", billCode='" + billCode + '\'' +
                ", inStartTime=" + inStartTime +
                ", inEndTime=" + inEndTime +
                ", supplierCode='" + supplierCode + '\'' +
                ", submitStateCode=" + submitStateCode +
                ", auditStateCode=" + auditStateCode +
                ", inOrOutStateCode=" + inOrOutStateCode +
                ", operatorCodeList=" + operatorCodeList +
                "} " + super.toString();
    }
}
