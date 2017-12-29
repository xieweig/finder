package cn.sisyphe.coffee.bill.domain.transmit;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/29.
 */
public class WayBillDetailKey implements Serializable {

    public WayBillDetailKey() {
    }

    private String packageCode;
    //bill Id
    private Long billDetailId;
    //
    private String cargoCode;

    private String rawMaterialCode;

    private String billCode;
    //
    private WayBill wayBill;


    public WayBill getWayBill() {
        return wayBill;
    }

    public void setWayBill(WayBill wayBill) {
        this.wayBill = wayBill;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public Long getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Long billDetailId) {
        this.billDetailId = billDetailId;
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

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }
}
