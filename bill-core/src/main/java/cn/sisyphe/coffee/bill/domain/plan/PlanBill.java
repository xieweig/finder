package cn.sisyphe.coffee.bill.domain.plan;

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

    @Column
    @Enumerated(value = EnumType.STRING)
    private OperationStateEnum operationState;

    @Column
    private String billName;

    /**
     * 具体的单据类型
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private BillTypeEnum specificBillType;

    //是否是总部计划
    @Column
    private Boolean hqBill;


    public OperationStateEnum getOperationState() {
        return operationState;
    }

    public void setOperationState(OperationStateEnum operationState) {
        this.operationState = operationState;
    }

    public PlanBill() {
        setBillType(BillTypeEnum.PLAN);
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public BillTypeEnum getSpecificBillType() {
        return specificBillType;
    }

    public void setSpecificBillType(BillTypeEnum specificBillType) {
        this.specificBillType = specificBillType;
    }

    public Boolean getHqBill() {
        return hqBill;
    }

    public void setHqBill(Boolean hqBill) {
        this.hqBill = hqBill;
    }

}
