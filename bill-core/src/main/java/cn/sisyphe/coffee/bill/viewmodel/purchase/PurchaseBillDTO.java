package cn.sisyphe.coffee.bill.viewmodel.purchase;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;

import java.math.BigDecimal;

/**
 * Created by heyong on 2018/1/17 11:41
 * Description:
 */
public class PurchaseBillDTO extends BillDTO<PurchaseBillDetailDTO> {

    /**
     * 货运单号
     */
    private String freightCode;

    /**
     * 发货件数
     */
    private Integer shippedAmount;

    /**
     * 实收件数
     */
    private Integer actualAmount;

    /**
     * 供应商
     */
    private Supplier supplier;

    /**
     * 备注
     */
    private String memo;

    /**
     * 数量差值
     */
    private Integer differenceAmount;

    /**
     * 进货实洋
     */
    private BigDecimal totalPriceAmount;

    /**
     * 总价差值
     */
    private BigDecimal totalPriceDifferenceAmount;


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getFreightCode() {
        return freightCode;
    }

    public void setFreightCode(String freightCode) {
        this.freightCode = freightCode;
    }

    public Integer getShippedAmount() {
        return shippedAmount;
    }

    public void setShippedAmount(Integer shippedAmount) {
        this.shippedAmount = shippedAmount;
    }

    public Integer getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Integer actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getDifferenceAmount() {
        return differenceAmount;
    }

    public void setDifferenceAmount(Integer differenceAmount) {
        this.differenceAmount = differenceAmount;
    }

    public BigDecimal getTotalPriceAmount() {
        return totalPriceAmount;
    }

    public void setTotalPriceAmount(BigDecimal totalPriceAmount) {
        this.totalPriceAmount = totalPriceAmount;
    }

    public BigDecimal getTotalPriceDifferenceAmount() {
        return totalPriceDifferenceAmount;
    }

    public void setTotalPriceDifferenceAmount(BigDecimal totalPriceDifferenceAmount) {
        this.totalPriceDifferenceAmount = totalPriceDifferenceAmount;
    }
}
