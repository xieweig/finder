package cn.sisyphe.coffee.bill.viewmodel.deliverybill;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillDetail;
import cn.sisyphe.coffee.bill.domain.delivery.enums.PickingTypeEnum;
import cn.sisyphe.framework.web.exception.DataException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.StringUtils;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Administrator on 2018/1/4.
 */
public class DeliveryPickingEditDTO implements Serializable {


    private List<DeliveryPickingEditItemDTO> billDetails;

    /**
     * 站点
     */
    private Station station;

    /**
     * 归属站点
     */
    private String belongStationCode;


//    /**
//     * 库房
//     */
//    private Storage storage;


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
     * 拣货方式
     */
    @Enumerated(value = EnumType.STRING)
    private PickingTypeEnum pickingTypeEnum;
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
    public DeliveryBill convertPickingDTOToBill(DeliveryPickingEditDTO dto) throws DataException {
        DeliveryBill deliveryBill = new DeliveryBill();

        // 假如首次添加默认一个暂时的单号
        if (!StringUtils.isEmpty(dto.getBillCode())) {
            // bill code
            deliveryBill.setBillCode(dto.getBillCode());//
        } else {
            //测试使用
            Random random = new Random();
            //配送单号
            deliveryBill.setBillCode("PS" + random.nextInt(10000));
        }
        //归属站点
        deliveryBill.setBelongStationCode(dto.getOutLocation().code());
        //入库站点
        deliveryBill.setInLocation(dto.getInLocation());
        //出库站点
        deliveryBill.setOutLocation(dto.getOutLocation());
        //操作人编码
        deliveryBill.setOperatorCode(dto.getOperatorCode());
        //单据类型
        deliveryBill.setBillType(dto.getBillType());
        //单据用途
        deliveryBill.setBillPurpose(dto.getBillPurpose());
        //总数量
        deliveryBill.setTotalAmount(this.calcTotalAmount(dto));
        //总品种
        deliveryBill.setTotalCount(this.calcTotalCount(dto));
        //添加明细
        deliveryBill.setBillDetails(this.convertBillItemsToDTO(dto));
        return deliveryBill;

    }

    /**
     * 拣货明细(添加货物或者原料)
     *
     * @param editDTO
     * @return
     */
    private Set<DeliveryBillDetail> convertBillItemsToDTO(DeliveryPickingEditDTO editDTO) throws DataException {

        Set<DeliveryBillDetail> billDetails = new HashSet<>();
        //
        for (DeliveryPickingEditItemDTO item : editDTO.getBillDetails()) {
            DeliveryBillDetail tempBillDetail = new DeliveryBillDetail();
            //包号
            tempBillDetail.setPackageCode(item.getPackageCode());
            //出库库位
            tempBillDetail.setOutLocation(item.getOutLocation());
            //
            tempBillDetail.setInLocation(item.getInLocation());
            // 设置货物和原料信息
            RawMaterial rawMaterial = item.getRawMaterial();

            tempBillDetail.setGoods(rawMaterial);
            //实际数量
            tempBillDetail.setAmount(item.getActualAmount());
            //备注
            item.setMemo(item.getMemo());
            //明细添加
            billDetails.add(tempBillDetail);
        }
        return billDetails;
    }

    /**
     * 总数量
     *
     * @param editDTO
     * @return
     */
    public Integer calcTotalAmount(DeliveryPickingEditDTO editDTO) {
        //
        int totalAmount = 0;
        for (DeliveryPickingEditItemDTO item : editDTO.getBillDetails()) {
            // 数量累计
            totalAmount += item.getActualAmount();//
        }
        return totalAmount;
    }

    /**
     * 品种计算
     *
     * @param editDTO
     * @return
     */
    public Integer calcTotalCount(DeliveryPickingEditDTO editDTO) {
        //
        List<String> cardCodeList = new ArrayList<>();

        for (DeliveryPickingEditItemDTO item : editDTO.getBillDetails()) {
            // 数量累计
            String strCargoCode = item.getRawMaterial().getCargo().getCargoCode();
            String billCode = editDTO.getBillCode();
            String uniqueCode = billCode + strCargoCode;
            if (!cardCodeList.contains(uniqueCode)) {
                cardCodeList.add(uniqueCode);//
            }
        }
        return cardCodeList.size();
    }


    public String getBelongStationCode() {
        return belongStationCode;
    }

    public void setBelongStationCode(String belongStationCode) {
        this.belongStationCode = belongStationCode;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public PickingTypeEnum getPickingTypeEnum() {
        return pickingTypeEnum;
    }

    public void setPickingTypeEnum(PickingTypeEnum pickingTypeEnum) {
        this.pickingTypeEnum = pickingTypeEnum;
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
