package cn.sisyphe.coffee.bill.viewmodel.returned;

import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;


/**
 *
 * @author mayupeng
 */
public class ReturnedBillDetailDTO {
    /**
     * 数量
     */
    private Integer amount;

    /**
     * 原料
     */
    private RawMaterial rawMaterial;

    /**
     * 备注
     */
    private String memo;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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
