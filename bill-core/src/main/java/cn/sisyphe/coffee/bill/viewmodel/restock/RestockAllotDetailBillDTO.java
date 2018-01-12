package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;

/**
 * @author mayupeng
 * @Date 2018/01/12
 * @description 退库调拨单明细DTO
 */
public class RestockAllotDetailBillDTO {
    /**
     * 原料
     */
    private RawMaterial rawMaterial;
    /**
     * 入库数量
     */
    private int shippedAmount;

    /**
     * 实调数量
     */
    private int actualAmount;

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
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
}
