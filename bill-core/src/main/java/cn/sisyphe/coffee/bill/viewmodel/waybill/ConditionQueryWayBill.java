package cn.sisyphe.coffee.bill.viewmodel.waybill;

import cn.sisyphe.coffee.bill.viewmodel.BaseConditionQuery;

import java.io.Serializable;


/**
 * 运单分页查询
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
     * 录单开始时间
     */
    private String createStartTime;
    /**
     * 录单结束时间
     */
    private String createEndTime;

    /**
     * 发货开始时间
     */

    private String deliveryStartTime;

    /**
     * 发货结束时间
     */

    private String deliveryEndTime;

    /**
     * 物流公司名称
     */
    private String logisticsCompanyName;

    /**
     * 入库站点
     */
    private String inStationCode;

    /**
     * 出库站点
     */
    private String outStationCode;

    /**
     * 单据状态
     */
    private String wayBillStatus;

    public String getWayBillCode() {
        return wayBillCode;
    }

    public void setWayBillCode(String wayBillCode) {
        this.wayBillCode = wayBillCode;
    }

    public String getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(String createStartTime) {
        this.createStartTime = createStartTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public String getDeliveryStartTime() {
        return deliveryStartTime;
    }

    public void setDeliveryStartTime(String deliveryStartTime) {
        this.deliveryStartTime = deliveryStartTime;
    }

    public String getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public void setDeliveryEndTime(String deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
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
}
