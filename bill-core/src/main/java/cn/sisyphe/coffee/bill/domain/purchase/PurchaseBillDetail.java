package cn.sisyphe.coffee.bill.domain.purchase;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by heyong on 2017/12/21 11:55
 * Description: 进货单明细
 *
 * @author heyong
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class PurchaseBillDetail extends BillDetail {

    /**
     * 标准单位编码
     */
    private String standardUnitCode;


    /**
     * 规格编码
     */
    private String measurementCode;


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
     * 实收数量
     */
    private int actualNumber;

    /**
     * 发货数量
     */
    private int shippedNumber;

    /**
     * 数量差值
     */
    private int differenceNumber;

    /**
     * 总价差值
     */
    private BigDecimal differencePrice;

    public String getStandardUnitCode() {
        return standardUnitCode;
    }

    public void setStandardUnitCode(String standardUnitCode) {
        this.standardUnitCode = standardUnitCode;
    }

    public String getMeasurementCode() {
        return measurementCode;
    }

    public void setMeasurementCode(String measurementCode) {
        this.measurementCode = measurementCode;
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

    public int getActualNumber() {
        return actualNumber;
    }

    public void setActualNumber(int actualNumber) {
        this.actualNumber = actualNumber;
    }

    public int getShippedNumber() {
        return shippedNumber;
    }

    public void setShippedNumber(int shippedNumber) {
        this.shippedNumber = shippedNumber;
    }

    public int getDifferenceNumber() {
        return differenceNumber;
    }

    public void setDifferenceNumber(int differenceNumber) {
        this.differenceNumber = differenceNumber;
    }

    public BigDecimal getDifferencePrice() {
        return differencePrice;
    }

    public void setDifferencePrice(BigDecimal differencePrice) {
        this.differencePrice = differencePrice;
    }

    @Override
    public String toString() {
        return "PurchaseBillDetail{" +
                ", standardUnitCode='" + standardUnitCode + '\'' +
                ", measurementCode='" + measurementCode + '\'' +
                ", dateInProduced=" + dateInProduced +
                ", unitPrice=" + unitPrice +
                ", actualNumber=" + actualNumber +
                ", shippedNumber=" + shippedNumber +
                ", differenceNumber=" + differenceNumber +
                ", differencePrice=" + differencePrice +
                "} " + super.toString();
    }
}
