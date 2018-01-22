package cn.sisyphe.coffee.bill.domain.purchase.model;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 进货单
 *
 * @author heyong
 */
@Entity
@Table(name = "purchase_bill")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public final class PurchaseBill extends Bill<PurchaseBillDetail> {

    public PurchaseBill() {
        setBillType(BillTypeEnum.PURCHASE);
    }

    @Override
    public String billCodePrefix() {
        return "JHDJ";
    }

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
}
