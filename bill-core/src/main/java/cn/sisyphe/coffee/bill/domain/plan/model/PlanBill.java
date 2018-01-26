package cn.sisyphe.coffee.bill.domain.plan.model;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.enums.OperationStateEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * @author ncmao
 * @Date 2017/12/25 15:38
 * @description
 */

@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class PlanBill extends Bill<PlanBillDetail> {

    @Override
    public String billCodePrefix() {
        return "ZBJH";
    }

    @Enumerated(value = EnumType.STRING)
    private OperationStateEnum operationState;

    private String billName;

    /**
     * 是否是总部计划
     */
    @Column
    private Boolean hqBill;

    //来源总部计划类型
    @Enumerated(value = EnumType.STRING)
    private BillTypeEnum sourceBillType;



    public OperationStateEnum getOperationState() {
        return operationState;
    }

    public void setOperationState(OperationStateEnum operationState) {
        this.operationState = operationState;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public Boolean getHqBill() {
        return hqBill;
    }

    public void setHqBill(Boolean hqBill) {
        this.hqBill = hqBill;
    }

    public BillTypeEnum getSourceBillType() {
        return sourceBillType;
    }

    public void setSourceBillType(BillTypeEnum sourceBillType) {
        this.sourceBillType = sourceBillType;
    }
}
