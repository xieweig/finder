package cn.sisyphe.coffee.bill.viewmodel.adjust;

import cn.sisyphe.coffee.bill.viewmodel.BaseConditionQuery;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂单据查询条件
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
    private String outStationCode;

    /**
     * 入库站点
     */
    private String inStationCode;

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
    private Date outStartTime;
    /**
     * 入库结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outEndTime;

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
     * 单据属性
     */
    private String billTypeCode;

    /**
     * 配送品种开始数量
     */
    private Integer varietyStart;

    /**
     * 配送品种结束数量
     */
    private Integer varietyEnd;

    /**
     * 配送总价开始
     */
    private BigDecimal totalPriceStart;

    /**
     * 配送总价结束
     */
    private BigDecimal totalPriceEnd;

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

    public String getOutStationCode() {
        return outStationCode;
    }

    public void setOutStationCode(String outStationCode) {
        this.outStationCode = outStationCode;
    }

    public String getInStationCode() {
        return inStationCode;
    }

    public void setInStationCode(String inStationCode) {
        this.inStationCode = inStationCode;
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

    public Date getOutStartTime() {
        return outStartTime;
    }

    public void setOutStartTime(Date outStartTime) {
        this.outStartTime = outStartTime;
    }

    public Date getOutEndTime() {
        return outEndTime;
    }

    public void setOutEndTime(Date outEndTime) {
        this.outEndTime = outEndTime;
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

    public String getBillTypeCode() {
        return billTypeCode;
    }

    public void setBillTypeCode(String billTypeCode) {
        this.billTypeCode = billTypeCode;
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

    public BigDecimal getTotalPriceStart() {
        return totalPriceStart;
    }

    public void setTotalPriceStart(BigDecimal totalPriceStart) {
        this.totalPriceStart = totalPriceStart;
    }

    public BigDecimal getTotalPriceEnd() {
        return totalPriceEnd;
    }

    public void setTotalPriceEnd(BigDecimal totalPriceEnd) {
        this.totalPriceEnd = totalPriceEnd;
    }

    @Override
    public String toString() {
        return "ConditionQueryAdjustBill{" +
                "operatorName='" + operatorName + '\'' +
                ", operatorCodeList=" + operatorCodeList +
                ", billCode='" + billCode + '\'' +
                ", outStationCode='" + outStationCode + '\'' +
                ", inStationCode='" + inStationCode + '\'' +
                ", createStartTime=" + createStartTime +
                ", createEndTime=" + createEndTime +
                ", outStartTime=" + outStartTime +
                ", outEndTime=" + outEndTime +
                ", submitStateCode=" + submitStateCode +
                ", auditStateCode=" + auditStateCode +
                ", inOrOutStateCode=" + inOrOutStateCode +
                ", billTypeCode='" + billTypeCode + '\'' +
                ", varietyStart=" + varietyStart +
                ", varietyEnd=" + varietyEnd +
                ", totalPriceStart=" + totalPriceStart +
                ", totalPriceEnd=" + totalPriceEnd +
                "} " + super.toString();
    }
}
