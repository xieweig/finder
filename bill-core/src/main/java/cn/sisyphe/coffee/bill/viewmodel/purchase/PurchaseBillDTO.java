package cn.sisyphe.coffee.bill.viewmodel.purchase;

import java.math.BigDecimal;

/**
 * Created by XiongJing on 2017/12/28.
 * remark：进货单据DTO
 * version: 1.0
 *
 * @author XiongJing
 */
public class PurchaseBillDTO {

    /**
     * 进货单号-主表
     */
    private String billCode;

    /**
     * 入库时间-主表
     */
    private String inWareHouseTime;

    /**
     * 录单时间-主表
     */
    private String createTime;

    /**
     * 录单人-主表
     */
    private String operatorCode;

    /**
     * 审核人-主表
     */
    private String auditPersonCode;

    /**
     * 入库站点-主表
     */
    private String inStationName;

    /**
     * 入库库房-主表
     */
    private String inStorageName;

    /**
     * 实收数量--明细表
     */
    private Integer actualNumber;

    /**
     * 数量差值--明细表
     */
    private Integer differenceNumber;

    /**
     * 进货实洋--明细表
     */
    private BigDecimal inTotalPrice;

    /**
     * 总价差值--明细表
     */
    private BigDecimal differencePrice;
    /**
     * 供应商名称--主表
     */
    private String supplierCode;

    /**
     * 单据状态--主表
     */
    private String billState;

    /**
     * 备注--主表
     */
    private String memo;

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getInWareHouseTime() {
        return inWareHouseTime;
    }

    public void setInWareHouseTime(String inWareHouseTime) {
        this.inWareHouseTime = inWareHouseTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getAuditPersonCode() {
        return auditPersonCode;
    }

    public void setAuditPersonCode(String auditPersonCode) {
        this.auditPersonCode = auditPersonCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getInStationName() {
        return inStationName;
    }

    public void setInStationName(String inStationName) {
        this.inStationName = inStationName;
    }

    public String getInStorageName() {
        return inStorageName;
    }

    public void setInStorageName(String inStorageName) {
        this.inStorageName = inStorageName;
    }
    public Integer getActualNumber() {
        return actualNumber;
    }

    public void setActualNumber(Integer actualNumber) {
        this.actualNumber = actualNumber;
    }

    public Integer getDifferenceNumber() {
        return differenceNumber;
    }

    public void setDifferenceNumber(Integer differenceNumber) {
        this.differenceNumber = differenceNumber;
    }

    public BigDecimal getDifferencePrice() {
        return differencePrice;
    }

    public void setDifferencePrice(BigDecimal differencePrice) {
        this.differencePrice = differencePrice;
    }

    public String getBillState() {
        return billState;
    }

    public void setBillState(String billState) {
        this.billState = billState;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getInTotalPrice() {
        return inTotalPrice;
    }

    public void setInTotalPrice(BigDecimal inTotalPrice) {
        this.inTotalPrice = inTotalPrice;
    }

    @Override
    public String toString() {
        return "PurchaseBillDTO{" +
                "billCode='" + billCode + '\'' +
                ", inWareHouseTime='" + inWareHouseTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", operatorCode='" + operatorCode + '\'' +
                ", auditPersonCode='" + auditPersonCode + '\'' +
                ", inStationName='" + inStationName + '\'' +
                ", inStorageName='" + inStorageName + '\'' +
                ", actualNumber=" + actualNumber +
                ", differenceNumber=" + differenceNumber +
                ", inTotalPrice=" + inTotalPrice +
                ", differencePrice=" + differencePrice +
                ", supplierCode='" + supplierCode + '\'' +
                ", billState='" + billState + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
