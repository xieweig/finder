package cn.sisyphe.coffee.bill.viewmodel.purchase;

import cn.sisyphe.coffee.bill.viewmodel.base.BillDetailDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by heyong on 2018/1/17 11:41
 * Description:
 */
public class PurchaseBillDetailDTO extends BillDetailDTO {

    /**
     * 数量差值
     */
    private Integer differenceNumber;

    /**
     * 总价差值
     */
    private BigDecimal totalDifferencePrice;

    /**
     * 单位进价
     */
    private BigDecimal unitPrice;

    /**
     * 生产日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateInProduced;

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getDifferenceNumber() {
        return differenceNumber;
    }

    public void setDifferenceNumber(Integer differenceNumber) {
        this.differenceNumber = differenceNumber;
    }

    public BigDecimal getTotalDifferencePrice() {
        return totalDifferencePrice;
    }

    public void setTotalDifferencePrice(BigDecimal totalDifferencePrice) {
        this.totalDifferencePrice = totalDifferencePrice;
    }

    public Date getDateInProduced() {
        return dateInProduced;
    }

    public void setDateInProduced(Date dateInProduced) {
        this.dateInProduced = dateInProduced;
    }
}
