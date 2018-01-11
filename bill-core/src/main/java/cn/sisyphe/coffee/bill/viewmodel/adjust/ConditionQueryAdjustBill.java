package cn.sisyphe.coffee.bill.viewmodel.adjust;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
import cn.sisyphe.coffee.bill.viewmodel.BaseConditionQuery;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂-站点出库单据查询条件
 * version: 1.0
 *
 * @author XiongJing
 */
public class ConditionQueryAdjustBill extends BaseConditionQuery {

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
    private String outStationCodeArray;

    /**
     * 入库站点
     */
    private String inStationCodeArray;

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
     * 出库开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inOrOutStartTime;
    /**
     * 出库结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inOrOutEndTime;

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
     * 单据属性
     */
    private List<SourcePlanTypeEnum> billTypeCodeList;

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

    public String getOutStationCodeArray() {
        return outStationCodeArray;
    }

    public void setOutStationCodeArray(String outStationCodeArray) {
        this.outStationCodeArray = outStationCodeArray;
    }

    public String getInStationCodeArray() {
        return inStationCodeArray;
    }

    public void setInStationCodeArray(String inStationCodeArray) {
        this.inStationCodeArray = inStationCodeArray;
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

    public Date getInOrOutStartTime() {
        return inOrOutStartTime;
    }

    public void setInOrOutStartTime(Date inOrOutStartTime) {
        this.inOrOutStartTime = inOrOutStartTime;
    }

    public Date getInOrOutEndTime() {
        return inOrOutEndTime;
    }

    public void setInOrOutEndTime(Date inOrOutEndTime) {
        this.inOrOutEndTime = inOrOutEndTime;
    }

    public List<BillSubmitStateEnum> getSubmitStateCode() {
        return submitStateCode;
    }

    public void setSubmitStateCode(List<BillSubmitStateEnum> submitStateCode) {
        this.submitStateCode = submitStateCode;
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

    public List<SourcePlanTypeEnum> getBillTypeCodeList() {
        return billTypeCodeList;
    }

    public void setBillTypeCodeList(List<SourcePlanTypeEnum> billTypeCodeList) {
        this.billTypeCodeList = billTypeCodeList;
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

    @Override
    public String toString() {
        return "ConditionQueryAdjustBill{" +
                "operatorName='" + operatorName + '\'' +
                ", operatorCodeList=" + operatorCodeList +
                ", billCode='" + billCode + '\'' +
                ", outStationCodeArray='" + outStationCodeArray + '\'' +
                ", inStationCodeArray='" + inStationCodeArray + '\'' +
                ", createStartTime=" + createStartTime +
                ", createEndTime=" + createEndTime +
                ", inOrOutStartTime=" + inOrOutStartTime +
                ", inOrOutEndTime=" + inOrOutEndTime +
                ", submitStateCode=" + submitStateCode +
                ", auditStateCode=" + auditStateCode +
                ", inOrOutStateCode=" + inOrOutStateCode +
                ", billTypeCodeList=" + billTypeCodeList +
                ", varietyStart=" + varietyStart +
                ", varietyEnd=" + varietyEnd +
                ", purposeEnum=" + purposeEnum +
                ", billStateEnum=" + billStateEnum +
                "} " + super.toString();
    }
}
