package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.restock.ReadyWays;
import cn.sisyphe.coffee.bill.viewmodel.purchase.BillDetailDTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *@date: 2018/1/2
 *@description: 
 *@author：xieweiguang
 */
public class SaveByRestockBillDTO {


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
    /**
     * 进货单明细信息
     */
    private List<RestockBillDetailsDTO> billDetails;
    /**
     *
     *notes :
     *  区分是安照原料拣货还是按照货物拣货 默认按照货物拣货
     *  不作copyProperties之用
     */
    private ReadyWays readyWays = ReadyWays.ByCargo;

    public ReadyWays getReadyWays() {
        return readyWays;
    }

    public void setReadyWays(ReadyWays readyWays) {
        this.readyWays = readyWays;
    }

    public List<RestockBillDetailsDTO> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<RestockBillDetailsDTO> billDetails) {
        this.billDetails = billDetails;
    }

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

//    public Set<BillDetailsDTO> getDetails() {
//        return details;
//    }
//
//    public void setDetails(Set<BillDetailsDTO> details) {
//        this.details = details;
//    }
}
