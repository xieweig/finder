package cn.sisyphe.coffee.bill.viewmodel.plan.child;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ncmao
 * @Date 2018/1/5 10:08
 * @description
 */
public class ChildPlanBillDTO {

    /**
     * 计划单编码
     */
    private String billCode;

    /**
     * 状态
     */
    private BillStateEnum billState;
    private BillAuditStateEnum auditState;
    private BillSubmitStateEnum submitState;

    /**
     * 备注
     */
    private String memo;
    /**
     * 录单时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    /**
     * 接收对应的编号
     */
    private String receiveBillCode;

    private String inStationCode;
    private String outStationCode;
    private BasicEnum basicEnum;
    private String operatorName;
    /**
     * 数量
     */
    private Integer totalAmount;

    private Integer typeAmount;

    private BillTypeEnum billType;

    /**
     * 起始单号
     */
    private String rootCode;

    private BigDecimal progress;

    private List<ChildPlanBillDetailDTO> childPlanBillDetails;

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public String getRootCode() {
        return rootCode;
    }

    public void setRootCode(String rootCode) {
        this.rootCode = rootCode;
    }

    public String getReceiveBillCode() {
        return receiveBillCode;
    }

    public void setReceiveBillCode(String receiveBillCode) {
        this.receiveBillCode = receiveBillCode;
    }

    public BillStateEnum getBillState() {
        return billState;
    }

    public void setBillState(BillStateEnum billState) {
        this.billState = billState;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInStationCode() {
        return inStationCode;
    }

    public void setInStationCode(String inStationCode) {
        this.inStationCode = inStationCode;
    }

    public String getOutStationCode() {
        return outStationCode;
    }

    public void setOutStationCode(String outStationCode) {
        this.outStationCode = outStationCode;
    }

    public List<ChildPlanBillDetailDTO> getChildPlanBillDetails() {
        return childPlanBillDetails;
    }

    public void setChildPlanBillDetails(List<ChildPlanBillDetailDTO> childPlanBillDetails) {
        this.childPlanBillDetails = childPlanBillDetails;
    }

    public BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTypeAmount() {
        return typeAmount;
    }

    public void setTypeAmount(Integer typeAmount) {
        this.typeAmount = typeAmount;
    }

    public BillTypeEnum getBillType() {
        return billType;
    }

    public void setBillType(BillTypeEnum billType) {
        this.billType = billType;
    }

    public BillAuditStateEnum getAuditState() {
        return auditState;
    }

    public void setAuditState(BillAuditStateEnum auditState) {
        this.auditState = auditState;
    }

    public BillSubmitStateEnum getSubmitState() {
        return submitState;
    }

    public void setSubmitState(BillSubmitStateEnum submitState) {
        this.submitState = submitState;
    }
}
