package cn.sisyphe.coffee.bill.viewmodel.purchase;

import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by XiongJing on 2017/12/28.
 * remark：进货单明细DTO
 * version: 1.0
 *
 * @author XiongJing
 */
public class BillDetailDTO {
    /**
     * 实收数量-最小单位数量
     */
    private Integer amount;

    /**
     * 包号
     */
    private String packageCode;

    /**
     * 生产日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateInProduced;

    /**
     * 单位进价
     */
    private BigDecimal unitPrice;

    /**
     * 发货数量
     */
    private Integer shippedNumber;

    /**
     * 数量差值
     */
    private Integer differenceNumber;

    /**
     * 总价差值
     */
    private BigDecimal differencePrice;

    /**
     * 原料
     */
    private RawMaterial rawMaterial;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public Date getDateInProduced() {
        return dateInProduced;
    }

    public void setDateInProduced(Date dateInProduced) {
        this.dateInProduced = dateInProduced;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getShippedNumber() {
        return shippedNumber;
    }

    public void setShippedNumber(Integer shippedNumber) {
        this.shippedNumber = shippedNumber;
    }

    public Integer getDifferenceNumber() {
        return differenceNumber;
    }

    public void setDifferenceNumber(Integer differenceNumber) {
        this.differenceNumber = differenceNumber;
    }

    public BigDecimal getDifferencePrice() {
        return differencePrice;
    }

    public void setDifferencePrice(BigDecimal differencePrice) {
        this.differencePrice = differencePrice;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    @Override
    public String toString() {
        return "RestockBillDetailDTO{" +
                "amount=" + amount +
                ", packageCode='" + packageCode + '\'' +
                ", dateInProduced=" + dateInProduced +
                ", unitPrice=" + unitPrice +
                ", shippedNumber=" + shippedNumber +
                ", differenceNumber=" + differenceNumber +
                ", differencePrice=" + differencePrice +
                ", rawMaterial=" + rawMaterial +
                '}';
    }
}
