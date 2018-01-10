package cn.sisyphe.coffee.bill.viewmodel.adjust;

import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂单据明细DTO
 * version: 1.0
 *
 * @author XiongJing
 */
public class AddAdjustBillDetailDTO {

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

    /**
     * 所属原料编码
     */
    private String belongMaterialCode;


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


    public String getBelongMaterialCode() {
        return belongMaterialCode;
    }

    public void setBelongMaterialCode(String belongMaterialCode) {
        this.belongMaterialCode = belongMaterialCode;
    }

    @Override
    public String toString() {
        return "AddAdjustBillDetailDTO{" +
                "shippedAmount=" + shippedAmount +
                ", actualAmount=" + actualAmount +
                ", rawMaterial=" + rawMaterial +
                ", belongMaterialCode='" + belongMaterialCode + '\'' +
                '}';
    }
}
