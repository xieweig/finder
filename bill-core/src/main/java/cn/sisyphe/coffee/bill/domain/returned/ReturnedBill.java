package cn.sisyphe.coffee.bill.domain.returned;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mayupeng
 * @Date 2018/01/07
 * @description 退货计划单
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class ReturnedBill extends Bill<ReturnedBillDetail> {
    public ReturnedBill() {
        setBillType(BillTypeEnum.RETURNED);
    }

    /**
     * 单据属性
     */
    @Enumerated(EnumType.STRING)
    private cn.sisyphe.coffee.bill.domain.returned.enums.PropertyEnum billProperty;
    /**
     * 总价
     */
    private BigDecimal totalPrice;
    /**
     * 按货物还是按原料
     */
    @Enumerated(EnumType.STRING)
    private cn.sisyphe.coffee.bill.domain.returned.enums.BasicEnum basicEnum;

    /**
     * 操作人
     */
    private String operatorCode;

    /**
     * 来源单号
     */
    private String fromBillCode;

    /**
     * 退货数量
     */
    private int amount;

    /**
     * 退货品种数
     */
    private int variety;

    /**
     * 出库备注
     */
    private String outMemo;

    /**
     * 入库备注
     */
    private String planMemo;

    /**
     * 审核意见
     */
    private String auditMemo;

    /**
     * 入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inWareHouseTime;

    @Override
    public String getOperatorCode() {
        return operatorCode;
    }

    @Override
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public cn.sisyphe.coffee.bill.domain.returned.enums.PropertyEnum getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(cn.sisyphe.coffee.bill.domain.returned.enums.PropertyEnum billProperty) {
        this.billProperty = billProperty;
    }

    public String getFromBillCode() {
        return fromBillCode;
    }

    public void setFromBillCode(String fromBillCode) {
        this.fromBillCode = fromBillCode;
    }

    public cn.sisyphe.coffee.bill.domain.returned.enums.BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(cn.sisyphe.coffee.bill.domain.returned.enums.BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }

    public String getOutMemo() {
        return outMemo;
    }

    public void setOutMemo(String outMemo) {
        this.outMemo = outMemo;
    }

    public String getPlanMemo() {
        return planMemo;
    }

    public void setPlanMemo(String planMemo) {
        this.planMemo = planMemo;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    public Date getInWareHouseTime() {
        return inWareHouseTime;
    }

    public void setInWareHouseTime(Date inWareHouseTime) {
        this.inWareHouseTime = inWareHouseTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getVariety() {
        return variety;
    }

    public void setVariety(int variety) {
        this.variety = variety;
    }
}
