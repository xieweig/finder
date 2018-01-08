package cn.sisyphe.coffee.bill.domain.restock;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.restock.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.restock.enums.PropertyEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ncmao
 * @Date 2017/12/27 16:10
 * @description 退库计划单
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class RestockBill extends Bill<RestockBillDetail> {
    public RestockBill() {
        setBillType(BillTypeEnum.RESTOCK);
    }

    /**
     * 单据属性
     */
    @Enumerated(EnumType.STRING)
    private PropertyEnum billProperty;
    /**
     * 总价
     */
    private BigDecimal totalPrice;
    /**
     * 按货物还是按原料
     */
    @Enumerated(EnumType.STRING)
    private BasicEnum basicEnum;

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

    public PropertyEnum getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(PropertyEnum billProperty) {
        this.billProperty = billProperty;
    }

    public String getFromBillCode() {
        return fromBillCode;
    }

    public void setFromBillCode(String fromBillCode) {
        this.fromBillCode = fromBillCode;
    }

    public BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(BasicEnum basicEnum) {
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
