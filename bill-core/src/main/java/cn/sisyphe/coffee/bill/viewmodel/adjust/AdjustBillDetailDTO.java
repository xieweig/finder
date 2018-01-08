package cn.sisyphe.coffee.bill.viewmodel.adjust;

import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂单据明细DTO
 * version: 1.0
 *
 * @author XiongJing
 */
public class AdjustBillDetailDTO {

    /**
     * 应拣数量
     */
    private Integer shippedAmount;

    /**
     * 实拣数量
     */
    private Integer actualAmount;

    /**
     * 原料
     */
    private RawMaterial rawMaterial;


    private String memo;

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

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "AdjustBillDetailDTO{" +
                "shippedAmount=" + shippedAmount +
                ", actualAmount=" + actualAmount +
                ", rawMaterial=" + rawMaterial +
                '}';
    }
}
