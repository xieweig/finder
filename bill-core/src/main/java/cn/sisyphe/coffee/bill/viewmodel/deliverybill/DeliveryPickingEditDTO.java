package cn.sisyphe.coffee.bill.viewmodel.deliverybill;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/4.
 */
public class DeliveryPickingEditDTO implements Serializable {


    @JsonIgnore
    private Long billId;

    /**
     * 单据号
     */
    private String billCode;

    /**
     * 入库站点
     */
    private String inStationCode;

    /**
     * 出库站点
     */
    private String outStationCode;

    private List<DeliveryPickingEditItemDTO> billDetails;

    public Long getBillId() {

        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
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

    public List<DeliveryPickingEditItemDTO> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<DeliveryPickingEditItemDTO> billDetails) {
        this.billDetails = billDetails;
    }

    @Override
    public String
    toString() {
        return "DeliveryBillEditDTO{" +
                "billId=" + billId +
                ", billCode='" + billCode + '\'' +
                ", inStationCode='" + inStationCode + '\'' +
                ", outStationCode='" + outStationCode + '\'' +
                ", billDetails=" + billDetails +
                '}';
    }
}
