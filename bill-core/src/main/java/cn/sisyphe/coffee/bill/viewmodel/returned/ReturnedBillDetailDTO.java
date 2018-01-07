package cn.sisyphe.coffee.bill.viewmodel.returned;

import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;


/**
 *
 * @author mayupeng
 */
public class ReturnedBillDetailDTO {
    /**
     * 应拣数量
     */
    private int shippedAmount;

    /**
     * 实拣数量
     */
    private int actualAmount;


    /**
     * 原料
     */
    private RawMaterial rawMaterial;

    /**
     * 备注
     */
    private String memo;

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
}
