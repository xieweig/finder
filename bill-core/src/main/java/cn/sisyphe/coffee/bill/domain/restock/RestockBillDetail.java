package cn.sisyphe.coffee.bill.domain.restock;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author ncmao
 * @Date 2017/12/27 16:11
 * 退库计划详情
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class RestockBillDetail extends BillDetail {
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
    private int shippedNumber;

    /**
     * 数量差值
     */
    private int differenceNumber;

    /**
     * 总价差值
     */
    private BigDecimal differencePrice;

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
        return "RestockBillDetail{" +
                "dateInProduced=" + dateInProduced +
                ", unitPrice=" + unitPrice +
                ", shippedNumber=" + shippedNumber +
                ", differenceNumber=" + differenceNumber +
                ", differencePrice=" + differencePrice +
                '}';
    }
}
