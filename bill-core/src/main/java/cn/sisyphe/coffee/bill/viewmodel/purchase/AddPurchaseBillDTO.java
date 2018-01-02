package cn.sisyphe.coffee.bill.viewmodel.purchase;

import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;

import java.util.List;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：保存进货单数据
 * version: 1.0
 *
 * @author XiongJing
 */
public class AddPurchaseBillDTO {
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
     * 操作人代码
     */
    private String operatorCode;
    /**
     * 库房
     */
    private Storage storage;
    /**
     * 站点
     */
    private Station station;
    /**
     * 供应商信息
     */
    private Supplier supplier;
//    /**
//     * 单据状态
//     */
//    private BillStateEnum billState;

    /**
     * 进货单明细信息
     */
    private List<BillDetailDTO> billDetails;

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

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

//    public BillStateEnum getBillState() {
//        return billState;
//    }
//
//    public void setBillState(BillStateEnum billState) {
//        this.billState = billState;
//    }

    public List<BillDetailDTO> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetailDTO> billDetails) {
        this.billDetails = billDetails;
    }

    @Override
    public String toString() {
        return "AddPurchaseBillDTO{" +
                "freightCode='" + freightCode + '\'' +
                ", shippedAmount=" + shippedAmount +
                ", actualAmount=" + actualAmount +
                ", memo='" + memo + '\'' +
                ", operatorCode='" + operatorCode + '\'' +
                ", storage=" + storage +
                ", station=" + station +
                ", supplier=" + supplier +
//                ", billState=" + billState +
                ", billDetails=" + billDetails +
                '}';
    }
}
