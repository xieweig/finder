package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @date: 2018/1/2
 * @description:
 * @author：bifenglin
 */
public class ConditionQueryRestockBill extends ConditionQueryBill implements Serializable {

    private BillTypeEnum billType;

    private BillPurposeEnum billPurpose;

    /**
     * 开始配送总价
     */
    private BigDecimal startTotalPrice;
    /**
     * 结束配送总价
     */
    private BigDecimal endTotalPrice;

    public BillTypeEnum getBillType() {
        return billType;
    }

    public void setBillType(BillTypeEnum billType) {
        this.billType = billType;
    }

    public BillPurposeEnum getBillPurpose() {
        return billPurpose;
    }

    public void setBillPurpose(BillPurposeEnum billPurpose) {
        this.billPurpose = billPurpose;
    }

    public BigDecimal getStartTotalPrice() {
        return startTotalPrice;
    }

    public void setStartTotalPrice(BigDecimal startTotalPrice) {
        this.startTotalPrice = startTotalPrice;
    }

    public BigDecimal getEndTotalPrice() {
        return endTotalPrice;
    }

    public void setEndTotalPrice(BigDecimal endTotalPrice) {
        this.endTotalPrice = endTotalPrice;
    }

}
