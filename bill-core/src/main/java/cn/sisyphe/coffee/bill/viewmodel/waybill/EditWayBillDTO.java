package cn.sisyphe.coffee.bill.viewmodel.waybill;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
     * 出库单号
     */
    @Column(length = 255)
    private String outStorageBillCode;//


    private String outStaionCode;// 出库站点

    private String outStaionName;// 出库站点
    /**
     * id
     */
    @JsonIgnore
    private Long billId;
    /**
     * 运单号
     */
    private String wayBillCode;

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

    public String getOperatorName() {

        return operatorName;
    }


    public String getOutStaionCode() {
        return outStaionCode;
    }

    public void setOutStaionCode(String outStaionCode) {
        this.outStaionCode = outStaionCode;
    }

    public String getOutStaionName() {
        return outStaionName;
    }

    public void setOutStaionName(String outStaionName) {
        this.outStaionName = outStaionName;
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
}
