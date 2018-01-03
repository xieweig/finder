package cn.sisyphe.coffee.bill.domain.plan;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

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
    private String billName;


    @Column
    @Enumerated(value = EnumType.STRING)
    private BasicEnum basicEnum;


    @Column
    @Enumerated(value = EnumType.STRING)
    private BillTypeEnum specificBillType;

    //创建人名称
    @Column
    private String creatorName;

    //审核人名称
    @Column
    private String auditorName;

    @Column
    private Boolean hqBill;

    @Column
    private String memo;

    //审核意见
    private String auditMemo;


    //完成度
    @Column
    private BigDecimal progress;

    @Column
    private String parentBillCode;

    public PlanBill() {
        setBillType(BillTypeEnum.PLAN);
    }


    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }


    public BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }

    public BillTypeEnum getSpecificBillType() {
        return specificBillType;
    }

    public void setSpecificBillType(BillTypeEnum specificBillType) {
        this.specificBillType = specificBillType;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public Boolean getHqBill() {
        return hqBill;
    }

    public void setHqBill(Boolean hqBill) {
        this.hqBill = hqBill;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    public String getParentBillCode() {
        return parentBillCode;
    }

    public void setParentBillCode(String parentBillCode) {
        this.parentBillCode = parentBillCode;
    }
}
