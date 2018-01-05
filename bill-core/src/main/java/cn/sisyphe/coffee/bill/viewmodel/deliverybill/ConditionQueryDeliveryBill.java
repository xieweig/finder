package cn.sisyphe.coffee.bill.viewmodel.deliverybill;

import cn.sisyphe.coffee.bill.viewmodel.BaseConditionQuery;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 多条件查询配送出库的查询条件DTO
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/5 10:37
 **/
public class ConditionQueryDeliveryBill extends BaseConditionQuery implements Serializable {


    /**
     * 入库单号
     */
    private String billCode;


    /**
     * 录单人名称
     */
    private String operatorName;
    /**
     * 录单开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createStartTime;
    /**
     * 录单结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createEndTime;


    /**
     * 出库开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outStockStartTime;
    /**
     * 出库结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outStockEndTime;

    /**
     * 状态
     */
    private String statusCode;

    /**
     * 录单人编码集合
     */
    private List<String> operatorCodeList;

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
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

    public Date getOutStockStartTime() {
        return outStockStartTime;
    }

    public void setOutStockStartTime(Date outStockStartTime) {
        this.outStockStartTime = outStockStartTime;
    }

    public Date getOutStockEndTime() {
        return outStockEndTime;
    }

    public void setOutStockEndTime(Date outStockEndTime) {
        this.outStockEndTime = outStockEndTime;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getOperatorCodeList() {
        return operatorCodeList;
    }

    public void setOperatorCodeList(List<String> operatorCodeList) {
        this.operatorCodeList = operatorCodeList;
    }

    @Override
    public String toString() {
        return "ConditionQueryDeliveryBill{" +
                "billCode='" + billCode + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", createStartTime=" + createStartTime +
                ", createEndTime=" + createEndTime +
                ", outStockStartTime=" + outStockStartTime +
                ", outStockEndTime=" + outStockEndTime +
                ", statusCode='" + statusCode + '\'' +
                ", operatorCodeList=" + operatorCodeList +
                '}';
    }
}
