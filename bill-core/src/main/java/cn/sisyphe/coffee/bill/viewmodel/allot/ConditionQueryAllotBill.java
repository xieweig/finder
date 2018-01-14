package cn.sisyphe.coffee.bill.viewmodel.allot;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.viewmodel.BaseConditionQuery;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @date: 2018/1/12
 * @description: 多条件查询退库调拨单查询
 * @author：bifenglin
 */
public class ConditionQueryAllotBill  extends ConditionQueryBill implements Serializable {

    /**
     * 调拨单号
     */
    private String billCode;

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

    private String operatorCode;

    private List<String> operatorCodeList;

    /**
     * 入库站点编号集合
     */
    private String inStationCodeArray;

    /**
     * 出库站点编号集合
     */
    private String outStationCodeArray;
    /**
     * 入库库位
     */
    private Storage inStorage;
    /**
     * 出库库位
     */
    private Storage outStorage;

    private BillTypeEnum specificBillType;

    public List<String> getOperatorCodeList() {
        return operatorCodeList;
    }

    public void setOperatorCodeList(List<String> operatorCodeList) {
        this.operatorCodeList = operatorCodeList;
    }

    public BillTypeEnum getSpecificBillType() {
        return specificBillType;
    }

    public void setSpecificBillType(BillTypeEnum specificBillType) {
        this.specificBillType = specificBillType;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
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

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getInStationCodeArray() {
        return inStationCodeArray;
    }

    public void setInStationCodeArray(String inStationCodeArray) {
        this.inStationCodeArray = inStationCodeArray;
    }

    public String getOutStationCodeArray() {
        return outStationCodeArray;
    }

    public void setOutStationCodeArray(String outStationCodeArray) {
        this.outStationCodeArray = outStationCodeArray;
    }

    public Storage getInStorage() {
        return inStorage;
    }

    public void setInStorage(Storage inStorage) {
        this.inStorage = inStorage;
    }

    public Storage getOutStorage() {
        return outStorage;
    }

    public void setOutStorage(Storage outStorage) {
        this.outStorage = outStorage;
    }
}
