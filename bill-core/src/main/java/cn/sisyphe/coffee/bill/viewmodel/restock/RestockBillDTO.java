package cn.sisyphe.coffee.bill.viewmodel.restock;

import java.math.BigDecimal;
import java.util.Date;

/**
 * bifenglin
 */
public class RestockBillDTO {

    /**
     * 进货单号-主表
     */
    private String billCode;

    /**
     * 入库时间-主表
     */
    private Date inWareHouseTime;

    /**
     * 录单时间-主表
     */
    private Date createTime;

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
    private String inStationCode;

    /**
     * 入库库房-主表
     */
    private String inStorageCode;

    /**
     * 实收数量--明细表
     */
    private Integer amount;

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
     * 单据审核状态--主表
     */
    private String auditState;

    /**
     * 单据提交状态--主表
     */
    private String submitState;

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

    public Date getInWareHouseTime() {
        return inWareHouseTime;
    }

    public void setInWareHouseTime(Date inWareHouseTime) {
        this.inWareHouseTime = inWareHouseTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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

    public String getInStationCode() {
        return inStationCode;
    }

    public void setInStationCode(String inStationCode) {
        this.inStationCode = inStationCode;
    }

    public String getInStorageCode() {
        return inStorageCode;
    }

    public void setInStorageCode(String inStorageCode) {
        this.inStorageCode = inStorageCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDifferenceNumber() {
        return differenceNumber;
    }

    public void setDifferenceNumber(Integer differenceNumber) {
        this.differenceNumber = differenceNumber;
    }

    public BigDecimal getInTotalPrice() {
        return inTotalPrice;
    }

    public void setInTotalPrice(BigDecimal inTotalPrice) {
        this.inTotalPrice = inTotalPrice;
    }

    public BigDecimal getDifferencePrice() {
        return differencePrice;
    }

    public void setDifferencePrice(BigDecimal differencePrice) {
        this.differencePrice = differencePrice;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    public String getSubmitState() {
        return submitState;
    }

    public void setSubmitState(String submitState) {
        this.submitState = submitState;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
