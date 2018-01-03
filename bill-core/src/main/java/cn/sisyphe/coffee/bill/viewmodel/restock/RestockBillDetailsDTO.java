package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;

/**
 *@date: 2018/1/3
 *@description:
 *@author：xieweiguang
 * 约束了restockBillDetails 可以和 DTO copy
 */
public class RestockBillDetailsDTO  {
    /**
     *
     *notes : 货物信息
     *  货物编码 货物名称 所属原料 规格分成最小数量和包号 实际数量 货物备注
     */
    private String cargoCode;

    private String cargoName;

    private String rawMaterialCode;

    private String rawMaterialName;

    /**
     * 实收最小单位数量
     */
    private int amount;

    /**
     * 包号
     */
    private String packageCode;

    private Integer number;

    private String cargoRemarks;


    public String getRawMaterialCode() {
        return rawMaterialCode;
    }

    public void setRawMaterialCode(String rawMaterialCode) {
        this.rawMaterialCode = rawMaterialCode;
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getRawMaterialName() {
        return rawMaterialName;
    }

    public void setRawMaterialName(String rawMaterialName) {
        this.rawMaterialName = rawMaterialName;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public String getPackageCode() {
        return packageCode;
    }


    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCargoRemarks() {
        return cargoRemarks;
    }

    public void setCargoRemarks(String cargoRemarks) {
        this.cargoRemarks = cargoRemarks;
    }
}
