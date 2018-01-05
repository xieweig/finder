package cn.sisyphe.coffee.bill.viewmodel.deliverybill;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * 出库位置
     */
    private AbstractLocation outLocation;

    /**
     * 入库位置
     */
    private AbstractLocation inLocation;


    /**
     * 操作人code
     */
    private String operatorCode;


    /**
     * 计划单类型
     */
    @Enumerated(value = EnumType.STRING)
    private BillTypeEnum billType;

    /**
     * 单据作用
     */
    @JsonIgnore
    @Enumerated(value = EnumType.STRING)
    private BillPurposeEnum billPurpose;

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
        this.inLocation = deliveryBill.getInLocation();
        //出库
        this.outLocation = deliveryBill.getOutLocation();

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
        //入库
        deliveryBill.setInLocation(dto.getInLocation());
        //出库
        deliveryBill.setOutLocation(dto.getOutLocation());

        deliveryBill.setOperatorCode(deliveryBill.getOperatorCode());
        //单据类型
        deliveryBill.setBillType(dto.getBillType());
        // 单据用途
        deliveryBill.setBillPurpose(dto.getBillPurpose());
        //添加明细
        deliveryBill.setBillDetails(this.convertBillItemsToDTO(dto));
        return deliveryBill;

    }

    /**
     * @param editDTO
     * @return
     */
    private Set<DeliveryBillDetail> convertBillItemsToDTO(DeliveryPickingEditDTO editDTO) {

        Set<DeliveryBillDetail> billDetails = new HashSet<>();
        for (DeliveryPickingEditItemDTO item : editDTO.getBillDetails()) {
            DeliveryBillDetail tempBillDetail = new DeliveryBillDetail();
            //包号
            tempBillDetail.setPackageCode(item.getPackageCode());
            //出库库位
            tempBillDetail.setOutLocation(item.getOutLocation());
            //
            tempBillDetail.setInLocation(item.getInLocation());

            billDetails.add(tempBillDetail);
        }
        return billDetails;
    }

    public BillPurposeEnum getBillPurpose() {
        return billPurpose;
    }

    public void setBillPurpose(BillPurposeEnum billPurpose) {
        this.billPurpose = billPurpose;
    }

    public BillTypeEnum getBillType() {
        return billType;
    }

    public void setBillType(BillTypeEnum billType) {
        this.billType = billType;
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

    public AbstractLocation getOutLocation() {
        return outLocation;
    }

    public void setOutLocation(AbstractLocation outLocation) {
        this.outLocation = outLocation;
    }

    public AbstractLocation getInLocation() {
        return inLocation;
    }

    public void setInLocation(AbstractLocation inLocation) {
        this.inLocation = inLocation;
    }

    public List<DeliveryPickingEditItemDTO> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<DeliveryPickingEditItemDTO> billDetails) {
        this.billDetails = billDetails;
    }

    @Override
    public String toString() {
        return "DeliveryPickingEditDTO{" +
                "billDetails=" + billDetails +
                ", billId=" + billId +
                ", billCode='" + billCode + '\'' +
                ", outLocation=" + outLocation +
                ", inLocation=" + inLocation +
                ", operatorCode='" + operatorCode + '\'' +
                '}';
    }
}
