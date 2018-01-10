package cn.sisyphe.coffee.bill.domain.purchase;

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
    public static final String PURCHASE_OUT_STORAGE_PREFIX = "JHDJ";
    public PurchaseBill() {
        setBillCodePrefix(PURCHASE_OUT_STORAGE_PREFIX);
        setBillType(BillTypeEnum.PURCHASE);
    }

    /**
     * 货运单号
     */
    private String freightCode;

    /**
     * 发货件数
     */
    private int shippedAmount;

    /**
     * 实收件数
     */
    private int actualAmount;
    /**
     * 备注
     */
    private String memo;

    public String getFreightCode() {
        return freightCode;
    }

    public void setFreightCode(String freightCode) {
        this.freightCode = freightCode;
    }

    public int getShippedAmount() {
        return shippedAmount;
    }

    public void setShippedAmount(int shippedAmount) {
        this.shippedAmount = shippedAmount;
    }

    public int getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(int actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


}
