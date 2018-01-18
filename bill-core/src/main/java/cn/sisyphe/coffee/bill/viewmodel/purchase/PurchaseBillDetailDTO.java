package cn.sisyphe.coffee.bill.viewmodel.purchase;

import cn.sisyphe.coffee.bill.viewmodel.base.BillDetailDTO;

import java.math.BigDecimal;

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
}
