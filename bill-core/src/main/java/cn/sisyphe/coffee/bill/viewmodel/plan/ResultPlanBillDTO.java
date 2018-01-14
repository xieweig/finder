package cn.sisyphe.coffee.bill.viewmodel.plan;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

/**
 * @author Amy 2018/1/3
 * 计划单返回实体
 */
public class ResultPlanBillDTO {
    /**
     * 单据名称
     */
    private String billName;
    /**
     * 计划单编号
     */
    private String billCode;
    /**
     * 计划单类型
     */
    private BillTypeEnum billType;
    /**
     * 按原料还是按货物
     */
    private BasicEnum basicEnum;

    /**
     * 录单时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private BillSubmitStateEnum billSubmitState;

    private BillAuditStateEnum auditState;

    private BillStateEnum billState;

    private String auditorName;

    private String operatorName;

    /**
     * 单据明细
     */
    private Set<ResultPlanBillGoodsDTO> planBillDetails;

    private String auditMemo;

    private String memo;

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public BillTypeEnum getBillType() {
        return billType;
    }

    public void setBillType(BillTypeEnum billType) {
        this.billType = billType;
    }

    public Set<ResultPlanBillGoodsDTO> getPlanBillDetails() {
        return planBillDetails;
    }

    public void setPlanBillDetails(Set<ResultPlanBillGoodsDTO> planBillDetails) {
        this.planBillDetails = planBillDetails;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    @Override
    public String toString() {
        return "ResultPlanBillDTO{" +
                "billName='" + billName + '\'' +
                ", billCode='" + billCode + '\'' +
                ", billType=" + billType +
                ", planBillDetails=" + planBillDetails +
                '}';
    }

    public BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BillSubmitStateEnum getBillSubmitState() {
        return billSubmitState;
    }

    public void setBillSubmitState(BillSubmitStateEnum billSubmitState) {
        this.billSubmitState = billSubmitState;
    }

    public BillAuditStateEnum getAuditState() {
        return auditState;
    }

    public void setAuditState(BillAuditStateEnum auditState) {
        this.auditState = auditState;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BillStateEnum getBillState() {
        return billState;
    }

    public void setBillState(BillStateEnum billState) {
        this.billState = billState;
    }
}
