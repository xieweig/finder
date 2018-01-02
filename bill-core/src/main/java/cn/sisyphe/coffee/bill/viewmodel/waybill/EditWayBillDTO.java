package cn.sisyphe.coffee.bill.viewmodel.waybill;


import cn.sisyphe.coffee.bill.domain.transmit.enums.ReceivedStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 运单编辑(添加，修改DTO)
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2017/12/27 11:08
 **/
public class EditWayBillDTO implements Serializable {


    /**
     * 运单明细
     */
    private List<EditWayBillDetailDTO> editWayBillDetailDTOList = new ArrayList<>();
    /**
     * id
     */
    private Long billId;


    /**
     * 运单号
     */
    private String wayBillCode;

    /**
     * 出库单号
     */
    @Column(length = 255)
    private String outStorageBillCode;//


    private String outStationCode;// 出库站点

    private String outStationName;// 出库站点


    /**
     * 入库站点
     */
    private String inStationCode;

    /**
     * 入库站点名称
     */
    private String inStationName;


    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deliveryTime;

    /**
     * 预计到货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date planArrivalTime;

    /**
     * 物流公司名称
     */
    private String logisticsCompanyName;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 运货件数
     */
    private Integer amountOfPackages;
    /**
     * 备注
     */

    private String memo;

    /**
     * 总重量
     */
    private Long totalWeight;


    private String operatorCode;
    /**
     *
     */
    private String operatorName;


    /**
     * 收货状态
     */
    private ReceivedStatusEnum receivedStatus;



    public String getInStationCode() {
        return inStationCode;
    }

    public void setInStationCode(String inStationCode) {
        this.inStationCode = inStationCode;
    }

    public String getInStationName() {
        return inStationName;
    }

    public void setInStationName(String inStationName) {
        this.inStationName = inStationName;
    }

    public String getOperatorName() {

        return operatorName;
    }


    public String getOutStationName() {
        return outStationName;
    }

    public void setOutStationName(String outStationName) {
        this.outStationName = outStationName;
    }

    public String getOutStationCode() {
        return outStationCode;
    }

    public void setOutStationCode(String outStationCode) {
        this.outStationCode = outStationCode;
    }

    public String getOutStorageBillCode() {
        return outStorageBillCode;
    }

    public void setOutStorageBillCode(String outStorageBillCode) {
        this.outStorageBillCode = outStorageBillCode;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getPlanArrivalTime() {
        return planArrivalTime;
    }

    public void setPlanArrivalTime(Date planArrivalTime) {
        this.planArrivalTime = planArrivalTime;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Integer getAmountOfPackages() {
        return amountOfPackages;
    }

    public void setAmountOfPackages(Integer amountOfPackages) {
        this.amountOfPackages = amountOfPackages;
    }

    public String getWayBillCode() {
        return wayBillCode;
    }

    public void setWayBillCode(String wayBillCode) {
        this.wayBillCode = wayBillCode;
    }


    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getLogisticsCompanyName() {
        return logisticsCompanyName;
    }

    public void setLogisticsCompanyName(String logisticsCompanyName) {
        this.logisticsCompanyName = logisticsCompanyName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<EditWayBillDetailDTO> getEditWayBillDetailDTOList() {
        return editWayBillDetailDTOList;
    }

    public void setEditWayBillDetailDTOList(List<EditWayBillDetailDTO> editWayBillDetailDTOList) {
        this.editWayBillDetailDTOList = editWayBillDetailDTOList;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public Long getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Long totalWeight) {
        this.totalWeight = totalWeight;
    }

    public ReceivedStatusEnum getReceivedStatus() {
        return receivedStatus;
    }

    public void setReceivedStatus(ReceivedStatusEnum receivedStatus) {
        this.receivedStatus = receivedStatus;
    }
}
