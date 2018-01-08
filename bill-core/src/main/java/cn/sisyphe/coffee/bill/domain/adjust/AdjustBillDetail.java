package cn.sisyphe.coffee.bill.domain.adjust;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂单明细
 * version: 1.0
 *
 * @author XiongJing
 */
public class AdjustBillDetail extends BillDetail {

    /**
     * 应拣数量
     */
    private Integer shippedAmount;

    /**
     * 实拣数量
     */
    private Integer actualAmount;

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

    @Override
    public String toString() {
        return "AdjustBillDetail{" +
                "shippedAmount=" + shippedAmount +
                ", actualAmount=" + actualAmount +
                "} " + super.toString();
    }
}
