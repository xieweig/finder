package cn.sisyphe.coffee.bill.viewmodel.returned;

import cn.sisyphe.coffee.bill.domain.restock.enums.PropertyEnum;
import cn.sisyphe.coffee.bill.viewmodel.BaseConditionQuery;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author mayupeng
 * @Date 2018/01/07
 * @description 退货计划单
 */
public class ConditionQueryReturnedBill extends BaseConditionQuery implements Serializable{
    /**
     * 录单人名称
     */
    private String operatorName;
    /**
     * 出库单号
     */
    private String billCode;
    /**
     * 入库站点编号集合
     */
    private Set<String> inStationCodeArray;

    /**
     * 出库站点编号集合
     */
    private Set<String> outStationCodeArray;

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
    private Date inStartTime;
    /**
     * 出库结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inEndTime;

    /**
     * 单据属性
     */
    private PropertyEnum billProperty;

    /**
     * 提交状态
     */
    private List<String> submitStateCode;

    /**
     * 审核状态
     */
    private List<String> auditStateCode;

    /**
     * 出库状态
     */
    private List<String> outStateCode;
    /**
     * 录单人编码集合
     */
    private List<String> operatorCodeList;

    /**
     * 开始配送品种
     */
    private Integer startVariety;

    /**
     * 结束配送品种
     */
    private Integer endVariety;
    /**
     * 开始配送总价
     */
    private BigDecimal startTotalPrice;
    /**
     * 结束配送总价
     */
    private BigDecimal endTotalPrice;

    public Set<String> getInStationCodeArray() {
        return inStationCodeArray;
    }

    public void setInStationCodeArray(Set<String> inStationCodeArray) {
        this.inStationCodeArray = inStationCodeArray;
    }

    public Set<String> getOutStationCodeArray() {
        return outStationCodeArray;
    }

    public void setOutStationCodeArray(Set<String> outStationCodeArray) {
        this.outStationCodeArray = outStationCodeArray;
    }

    public PropertyEnum getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(PropertyEnum billProperty) {
        this.billProperty = billProperty;
    }

    public Integer getStartVariety() {
        return startVariety;
    }

    public void setStartVariety(Integer startVariety) {
        this.startVariety = startVariety;
    }

    public Integer getEndVariety() {
        return endVariety;
    }

    public void setEndVariety(Integer endVariety) {
        this.endVariety = endVariety;
    }

    public BigDecimal getStartTotalPrice() {
        return startTotalPrice;
    }

    public void setStartTotalPrice(BigDecimal startTotalPrice) {
        this.startTotalPrice = startTotalPrice;
    }

    public BigDecimal getEndTotalPrice() {
        return endTotalPrice;
    }

    public void setEndTotalPrice(BigDecimal endTotalPrice) {
        this.endTotalPrice = endTotalPrice;
    }

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


    public List<String> getOperatorCodeList() {
        return operatorCodeList;
    }

    public void setOperatorCodeList(List<String> operatorCodeList) {
        this.operatorCodeList = operatorCodeList;
    }

    public List<String> getOutStateCode() {
        return outStateCode;
    }

    public void setOutStateCode(List<String> outStateCode) {
        this.outStateCode = outStateCode;
    }
}
