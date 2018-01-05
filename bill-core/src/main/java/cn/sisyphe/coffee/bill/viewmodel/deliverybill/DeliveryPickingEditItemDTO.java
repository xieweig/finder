package cn.sisyphe.coffee.bill.viewmodel.deliverybill;

import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/4.
 */
public class DeliveryPickingEditItemDTO implements Serializable {


    /**
     * 货物编码
     */
    private String cargoCode;

    /**
     * 源码编号
     */
    private String rawMaterialCode;
    /**
     * 期望数量
     */
    private Integer expectedAmount;

    /**
     * 实际拣货数量
     */
    private Integer actualAmount;

    /**
     * 包号
     */
    private String packageCode;


    /**
     * 出库位置
     */

    private AbstractLocation outLocation;

    /**
     * 入库位置
     */

    private AbstractLocation inLocation;


    /**
     * 备注
     */
    private String memo;

    public AbstractLocation getOutLocation() {
        return outLocation;
    }

    public void setOutLocation(AbstractLocation outLocation) {
        this.outLocation = outLocation;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public AbstractLocation getInLocation() {
        return inLocation;
    }

    public void setInLocation(AbstractLocation inLocation) {
        this.inLocation = inLocation;
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }

    public String getRawMaterialCode() {
        return rawMaterialCode;
    }

    public void setRawMaterialCode(String rawMaterialCode) {
        this.rawMaterialCode = rawMaterialCode;
    }

    public Integer getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(Integer expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public Integer getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Integer actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    @Override
    public String toString() {
        return "DeliveryBillEditItemDTO{" +
                "cargoCode='" + cargoCode + '\'' +
                ", rawMaterialCode='" + rawMaterialCode + '\'' +
                ", expectedAmount=" + expectedAmount +
                ", actualAmount=" + actualAmount +
                ", packageCode='" + packageCode + '\'' +
                '}';
    }
}
