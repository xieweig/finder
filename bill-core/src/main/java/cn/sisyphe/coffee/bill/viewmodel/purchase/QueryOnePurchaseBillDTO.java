package cn.sisyphe.coffee.bill.viewmodel.purchase;

import java.util.List;

/**
 * Created by XiongJing on 2017/12/29.
 * remark：前端根据编码查询进货单信息DTO
 * version: 1.0
 *
 * @author XiongJing
 */
public class QueryOnePurchaseBillDTO {

    /**
     * 单据编码
     */
    private String billCode;
    /**
     * 货运单号
     */
    private String freightCode;

    /**
     * 发货件数
     */
    private Integer shippedAmount;

    /**
     * 实收件数
     */
    private Integer actualAmount;
    /**
     * 备注
     */
    private String memo;
    /**
     * 供应商信息
     */
    private String supplierCode;

    /**
     * 入库库位
     */
    private String inStorageCode;
    /**
     * 进货单明细信息
     */
    private List<BillDetailDTO> billDetails;

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getFreightCode() {
        return freightCode;
    }

    public void setFreightCode(String freightCode) {
        this.freightCode = freightCode;
    }

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getInStorageCode() {
        return inStorageCode;
    }

    public void setInStorageCode(String inStorageCode) {
        this.inStorageCode = inStorageCode;
    }

    public List<BillDetailDTO> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetailDTO> billDetails) {
        this.billDetails = billDetails;
    }

    @Override
    public String toString() {
        return "QueryOnePurchaseBillDTO{" +
                "billCode='" + billCode + '\'' +
                ", freightCode='" + freightCode + '\'' +
                ", shippedAmount=" + shippedAmount +
                ", actualAmount=" + actualAmount +
                ", memo='" + memo + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", inStorageCode='" + inStorageCode + '\'' +
                ", billDetails=" + billDetails +
                '}';
    }
}
