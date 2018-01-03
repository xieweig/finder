package cn.sisyphe.coffee.bill.viewmodel.restock;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *@date: 2018/1/2
 *@description: 
 *@author：xieweiguang
 */
public class SaveByRawMaterialDTO {


    /**
     *
     *notes : 单据信息
     *  单号 出库库位 单据备注
     */
    private String billCode;
    private String storageCode;
    private String remarks;

    /**
     *
     *notes :ref BaseBill
     *   录单日期起始
     */
    private Date createTime;
    /**
     *
     *notes : ref Bill.DBStation
     *  入库站点
     */
    private String inStationCode;
    /**
     *
     *notes : ref Bill.DBStation
     *  出库站点
     */
    private String outStationCode;
    /**
     *
     *notes :计划单信息
     *  录单时间 出库站点  入库站点
     */
    private Set<BillDetailsDTO> details;
    //private List<BillDetailsDTO> details;

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInStationCode() {
        return inStationCode;
    }

    public void setInStationCode(String inStationCode) {
        this.inStationCode = inStationCode;
    }

    public String getOutStationCode() {
        return outStationCode;
    }

    public void setOutStationCode(String outStationCode) {
        this.outStationCode = outStationCode;
    }

    public Set<BillDetailsDTO> getDetails() {
        return details;
    }

    public void setDetails(Set<BillDetailsDTO> details) {
        this.details = details;
    }
}
