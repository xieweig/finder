package cn.sisyphe.coffee.bill.viewmodel.base;

import cn.sisyphe.coffee.bill.domain.base.model.enums.*;
import cn.sisyphe.coffee.bill.viewmodel.BaseConditionQuery;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂-站点出库单据查询条件
 * version: 1.0
 *
 * @author XiongJing
 */
public class ConditionQueryBill extends BaseConditionQuery implements Serializable {

    /**
     * 录单人名称
     */
    private String operatorName;

    /**
     * 录单人编码集合
     */
    private List<String> operatorCodeList;

    /**
     * 出库单号
     */
    private String billCode;

    /**
     * 出库站点
     */
    private List<String> outStationCodes;

    /**
     * 入库站点
     */
    private List<String> inStationCodes;

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
     * 入库开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inStorageStartTime;
    /**
     * 入库结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inStorageEndTime;

    /**
     * 出库开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outStorageStartTime;
    /**
     * 出库结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outStorageEndTime;

    /**
     * 提交状态
     */
    private List<BillSubmitStateEnum> submitStates;


    /**
     * 调拨状态
      */
    private BillAllotStatusEnum billAllotState;

    /**
     * 审核状态
     */
    private List<BillAuditStateEnum> auditStates;

    /**
     * 出入库状态
     */
    private List<BillInOrOutStateEnum> inOrOutStates;

    /**
     * 具体的单据类型
     */
    private BillTypeEnum specificBillType;

    /**
     * 配送品种开始数量
     */
    private Integer varietyStart;

    /**
     * 配送品种结束数量
     */
    private Integer varietyEnd;

    /**
     * 单据作用
     */
    private BillPurposeEnum purposeEnum;

    /**
     * 单据状态
     */
    private BillStateEnum billStateEnum;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public List<String> getOperatorCodeList() {
        return operatorCodeList;
    }

    public void setOperatorCodeList(List<String> operatorCodeList) {
        this.operatorCodeList = operatorCodeList;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
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

    public Date getInStorageStartTime() {
        return inStorageStartTime;
    }

    public void setInStorageStartTime(Date inStorageStartTime) {
        this.inStorageStartTime = inStorageStartTime;
    }

    public Date getInStorageEndTime() {
        return inStorageEndTime;
    }

    public void setInStorageEndTime(Date inStorageEndTime) {
        this.inStorageEndTime = inStorageEndTime;
    }

    public Date getOutStorageStartTime() {
        return outStorageStartTime;
    }

    public void setOutStorageStartTime(Date outStorageStartTime) {
        this.outStorageStartTime = outStorageStartTime;
    }

    public Date getOutStorageEndTime() {
        return outStorageEndTime;
    }

    public void setOutStorageEndTime(Date outStorageEndTime) {
        this.outStorageEndTime = outStorageEndTime;
    }

    public Integer getVarietyStart() {
        return varietyStart;
    }

    public void setVarietyStart(Integer varietyStart) {
        this.varietyStart = varietyStart;
    }

    public Integer getVarietyEnd() {
        return varietyEnd;
    }

    public void setVarietyEnd(Integer varietyEnd) {
        this.varietyEnd = varietyEnd;
    }

    public BillPurposeEnum getPurposeEnum() {
        return purposeEnum;
    }

    public void setPurposeEnum(BillPurposeEnum purposeEnum) {
        this.purposeEnum = purposeEnum;
    }

    public BillStateEnum getBillStateEnum() {
        return billStateEnum;
    }

    public void setBillStateEnum(BillStateEnum billStateEnum) {
        this.billStateEnum = billStateEnum;
    }

    public List<String> getOutStationCodes() {
        return outStationCodes;
    }

    public void setOutStationCodes(List<String> outStationCodes) {
        this.outStationCodes = outStationCodes;
    }

    public List<String> getInStationCodes() {
        return inStationCodes;
    }

    public void setInStationCodes(List<String> inStationCodes) {
        this.inStationCodes = inStationCodes;
    }

    public List<BillSubmitStateEnum> getSubmitStates() {
        return submitStates;
    }

    public void setSubmitStates(List<BillSubmitStateEnum> submitStates) {
        this.submitStates = submitStates;
    }

    public List<BillAuditStateEnum> getAuditStates() {
        return auditStates;
    }

    public void setAuditStates(List<BillAuditStateEnum> auditStates) {
        this.auditStates = auditStates;
    }

    public List<BillInOrOutStateEnum> getInOrOutStates() {
        return inOrOutStates;
    }

    public void setInOrOutStates(List<BillInOrOutStateEnum> inOrOutStates) {
        this.inOrOutStates = inOrOutStates;
    }

    public BillTypeEnum getSpecificBillType() {
        return specificBillType;
    }

    public void setSpecificBillType(BillTypeEnum specificBillType) {
        this.specificBillType = specificBillType;
    }

    public BillAllotStatusEnum getBillAllotState() {
        return billAllotState;
    }

    public void setBillAllotState(BillAllotStatusEnum billAllotState) {
        this.billAllotState = billAllotState;
    }
}
