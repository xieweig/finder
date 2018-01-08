package cn.sisyphe.coffee.bill.viewmodel.returned;

import java.util.List;

/**
 * @author mayupeng
 * @Date 2018/01/07
 * @description 退货计划详情
 */
public class QueryOneReturnedBillDTO {

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
    private List<ReturnedBillDetailDTO> billDetails;

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

    public List<ReturnedBillDetailDTO> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<ReturnedBillDetailDTO> billDetails) {
        this.billDetails = billDetails;
    }
}
