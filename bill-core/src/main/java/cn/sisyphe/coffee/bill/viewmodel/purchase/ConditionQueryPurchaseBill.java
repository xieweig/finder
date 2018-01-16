package cn.sisyphe.coffee.bill.viewmodel.purchase;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
import cn.sisyphe.coffee.bill.viewmodel.BaseConditionQuery;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
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
public class ConditionQueryPurchaseBill extends ConditionQueryBill {

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
    private List<BillSubmitStateEnum> submitStateCode;

    /**
     * 审核状态
     */
    private List<BillAuditStateEnum> auditStateCode;

    /**
     * 出入库状态
     */
    private List<BillInOrOutStateEnum> inOrOutStateCode;

    /**
     * 录单人编码集合
     */
    private List<String> operatorCodeList;

    /**
     * 入库站点
     */
    private String inStationCodeArray;

    /**
     * 入库库位
     */
    private String outStationCodeArray;

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

    public List<BillSubmitStateEnum> getSubmitStateCode() {
        return submitStateCode;
    }

    public void setSubmitStateCode(List<BillSubmitStateEnum> submitStateCode) {
        this.submitStateCode = submitStateCode;
    }

    public List<BillAuditStateEnum> getAuditStateCode() {
        return auditStateCode;
    }

    public void setAuditStateCode(List<BillAuditStateEnum> auditStateCode) {
        this.auditStateCode = auditStateCode;
    }

    public List<BillInOrOutStateEnum> getInOrOutStateCode() {
        return inOrOutStateCode;
    }

    public void setInOrOutStateCode(List<BillInOrOutStateEnum> inOrOutStateCode) {
        this.inOrOutStateCode = inOrOutStateCode;
    }

    public List<String> getOperatorCodeList() {
        return operatorCodeList;
    }

    public void setOperatorCodeList(List<String> operatorCodeList) {
        this.operatorCodeList = operatorCodeList;
    }

    public String getInStationCodeArray() {
        return inStationCodeArray;
    }

    public void setInStationCodeArray(String inStationCodeArray) {
        this.inStationCodeArray = inStationCodeArray;
    }

    public String getOutStationCodeArray() {
        return outStationCodeArray;
    }

    public void setOutStationCodeArray(String outStationCodeArray) {
        this.outStationCodeArray = outStationCodeArray;
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
                ", inStationCodeArray='" + inStationCodeArray + '\'' +
                ", outStationCodeArray='" + outStationCodeArray + '\'' +
                "} " + super.toString();
    }
}