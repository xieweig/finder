package cn.sisyphe.coffee.bill.viewmodel.waybill;


import java.io.Serializable;
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

    private List<EditWayBillDetailDTO> editWayBillDetailDTOList;

    /**
     * 运单号
     */
    private String wayBillCode;

    /**
     * 发货时间
     */
    private String deliveryTime;

    /**
     * 预计到货时间
     */
    private String planArrivalTime;

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

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getPlanArrivalTime() {
        return planArrivalTime;
    }

    public void setPlanArrivalTime(String planArrivalTime) {
        this.planArrivalTime = planArrivalTime;
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

    public Long getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Long totalWeight) {
        this.totalWeight = totalWeight;
    }
}
