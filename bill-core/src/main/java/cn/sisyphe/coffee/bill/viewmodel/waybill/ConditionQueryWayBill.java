package cn.sisyphe.coffee.bill.viewmodel.waybill;

import cn.sisyphe.coffee.bill.viewmodel.BaseConditionQuery;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 运单跟踪分页查询
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2017/12/27 11:22
 **/
public class ConditionQueryWayBill extends BaseConditionQuery implements Serializable {

    /**
     * 运单号
     */
    private String wayBillCode;


    /**
     * 出库单号
     */
    private String outStorageBillCode;//


    /**
     * 录单开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    /**
     * 录单开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createStartTime;
    /**
     * 录单结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createEndTime;

    /**
     * 运货件数
     */
    private Integer amountOfPackages;

    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deliverTime;//


    /**
     * 发货开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deliveryStartTime;

    /**
     * 发货结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deliveryEndTime;

    /**
     * 物流公司名称
     */
    private String logisticsCompanyName;

    /**
     * 入库站点
     */
    private List<String> inStationCode;

    /**
     * 出库站点
     */
    private List<String> outStationCode;

    /**
     * 单据状态
     */

    /**
     * 收货状态
     */
    // @Enumerated(value = EnumType.STRING)

    //  private ReceivedStatusEnum receivedStatus;
    private String receivedStatus;

    public List<String> getInStationCode() {
        return inStationCode;
    }

    public void setInStationCode(List<String> inStationCode) {
        this.inStationCode = inStationCode;
    }

    public List<String> getOutStationCode() {
        return outStationCode;
    }

    public void setOutStationCode(List<String> outStationCode) {
        this.outStationCode = outStationCode;
    }

    public String getOperatorName() {
        return operatorName;
    }

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


    public String getReceivedStatus() {
        return receivedStatus;
    }

    public void setReceivedStatus(String receivedStatus) {
        this.receivedStatus = receivedStatus;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

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

    public Date getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(Date createStartTime) {
        this.createStartTime = createStartTime;
    }

    public Date getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(Date createEndTime) {
        this.createEndTime = createEndTime;
    }

    public Date getDeliveryStartTime() {
        return deliveryStartTime;
    }

    public void setDeliveryStartTime(Date deliveryStartTime) {
        this.deliveryStartTime = deliveryStartTime;
    }

    public Date getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public void setDeliveryEndTime(Date deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }

    public String getLogisticsCompanyName() {
        return logisticsCompanyName;
    }

    public void setLogisticsCompanyName(String logisticsCompanyName) {
        this.logisticsCompanyName = logisticsCompanyName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }




    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }



}
