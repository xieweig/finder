package cn.sisyphe.coffee.bill.viewmodel.waybill;

import cn.sisyphe.coffee.bill.domain.transmit.enums.ReceivedStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 运单跟踪分页查询返回DTO
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2017/12/27 14:21
 **/
public class ReturnWayBillDTO implements Serializable {
    /**
     * 运单号
     */
    private String wayBillCode;


    /**
     * 出库单号
     */
    private String outStorageBillCode;//

    /**
     * 录单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 运货件数
     */
    private Integer amountOfPackages;

    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deliveryTime;


    /**
     * 物流公司名称
     */
    private String logisticsCompanyName;

    /**
     * 入库站点
     */
    private String inStationCode;

    private String inStationName;

    /**
     * 出库站点
     */
    private String outStationCode;


    private String outStationName;
    /**
     * 单据状态
     */
    private String wayBillStatus;


    /**
     * 录单人
     */
    private String operatorName;


    public Integer getAmountOfPackages() {

        return amountOfPackages;
    }


    public String getOutStorageBillCode() {

        return outStorageBillCode;
    }

    /**
     * 收货状态
     */
    private ReceivedStatusEnum receivedStatus;


    public void setOutStorageBillCode(String outStorageBillCode) {
        this.outStorageBillCode = outStorageBillCode;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getWayBillStatus() {
        return wayBillStatus;
    }

    public void setWayBillStatus(String wayBillStatus) {
        this.wayBillStatus = wayBillStatus;
    }

    public String getOperatorName() {

        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getInStationName() {
        return inStationName;
    }

    public void setInStationName(String inStationName) {
        this.inStationName = inStationName;
    }

    public String getOutStationName() {
        return outStationName;
    }

    public void setOutStationName(String outStationName) {
        this.outStationName = outStationName;
    }

    public ReceivedStatusEnum getReceivedStatus() {
        return receivedStatus;
    }

    public void setReceivedStatus(ReceivedStatusEnum receivedStatus) {
        this.receivedStatus = receivedStatus;
    }

    @Override
    public String toString() {
        return "ReturnWayBillDTO{" +
                "wayBillCode='" + wayBillCode + '\'' +
                ", outStorageBillCode='" + outStorageBillCode + '\'' +
                ", createTime=" + createTime +
                ", amountOfPackages=" + amountOfPackages +
                ", deliveryTime=" + deliveryTime +
                ", logisticsCompanyName='" + logisticsCompanyName + '\'' +
                ", inStationCode='" + inStationCode + '\'' +
                ", inStationName='" + inStationName + '\'' +
                ", outStationCode='" + outStationCode + '\'' +
                ", outStationName='" + outStationName + '\'' +
                ", wayBillStatus='" + wayBillStatus + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", receivedStatus=" + receivedStatus +
                '}';
    }
}
