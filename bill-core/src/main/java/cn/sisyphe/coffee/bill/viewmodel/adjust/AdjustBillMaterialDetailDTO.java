package cn.sisyphe.coffee.bill.viewmodel.adjust;

import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;

/**
 * Created by XiongJing on 2018/1/9.
 * remark：
 * version:
 */
public class AdjustBillMaterialDetailDTO {
    /**
     * 应拣数量
     */
    private Integer shippedAmount;

    /**
     * 原料
     */
    private RawMaterial rawMaterial;

    public Integer getShippedAmount() {
        return shippedAmount;
    }

    public void setShippedAmount(Integer shippedAmount) {
        this.shippedAmount = shippedAmount;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    @Override
    public String toString() {
        return "AdjustBillMaterialDetailDTO{" +
                "shippedAmount=" + shippedAmount +
                ", rawMaterial=" + rawMaterial +
                '}';
    }
}
