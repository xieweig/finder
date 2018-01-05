package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;

import java.util.List;

/**
 * @author bifenglin
 */
public class AddRestockBillDTO {
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
     * 单据编码
     */
    private String billCode;
    /**
     * 进货单明细信息
     */
    private List<RestockBillDetailDTO> billDetails;

    public List<RestockBillDetailDTO> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<RestockBillDetailDTO> billDetails) {
        this.billDetails = billDetails;
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

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }
}
