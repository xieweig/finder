package cn.sisyphe.coffee.bill.viewmodel.deliverybill;

import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBill;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/4.
 */
public class DeliveryPickingEditDTO implements Serializable {


    private List<DeliveryPickingEditItemDTO> billDetails;


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

    /**
     * 操作人code
     */
    private String operatorCode;

    /**
     * Bill to DTO
     *
     * @param deliveryBill
     * @return
     */
    public DeliveryPickingEditDTO convertBillToPickingDTO(DeliveryBill deliveryBill) {
        this.billId = deliveryBill.getBillId();
        //bill code
        this.billCode = deliveryBill.getBillCode();
        this.operatorCode = deliveryBill.getOperatorCode();
        //入库
        this.inStationCode = deliveryBill.getInLocation().code();
        //出库
        this.outStationCode = deliveryBill.getOutLocation().code();

        return this;
    }

    /**
     * DTO  to bill
     *
     * @param dto
     * @return
     */
    public DeliveryBill convertPickingDTOToBill(DeliveryPickingEditDTO dto) {
        DeliveryBill deliveryBill = new DeliveryBill();
        // bill code
        deliveryBill.setBillCode(dto.getBillCode());
        return deliveryBill;

    }


    public Long getBillId() {

        return billId;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
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
