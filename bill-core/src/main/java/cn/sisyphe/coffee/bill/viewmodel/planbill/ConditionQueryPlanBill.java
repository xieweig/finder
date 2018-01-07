package cn.sisyphe.coffee.bill.viewmodel.planbill;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.BaseConditionQuery;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author bifenglin
 */
public class ConditionQueryPlanBill extends BaseConditionQuery implements Serializable {

    /**
     * 配送单编码
     */
    private String billCode;

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
     * 入库站点编号集合
     */
    private Set<String> inStationCodeArray;

    /**
     * 出库站点编号集合
     */
    private Set<String> outStationCodeArray;


    /**
     * 录单人
     */
    private String creatorName;

    private List<String> creatorCodeList;

    private BillStateEnum billState;

    private BillPurposeEnum billPurpose;

    private BillTypeEnum specificBillType;

    /**
     * 单据名称
     */
    private String billName;
    /**
     * 是否为总部计划单据
     */
    private String hqBill;

    /**
     * 货物编号集合
     */
    private Set<String> cargoCodeArray;
    /**
     * 货物编号
     */
    private String cargoCode;

    private List<String> operatorCodes;

    public List<String> getCreatorCodeList() {
        return creatorCodeList;
    }

    public void setCreatorCodeList(List<String> creatorCodeList) {
        this.creatorCodeList = creatorCodeList;
    }

    public BillStateEnum getBillState() {
        return billState;
    }

    public void setBillState(BillStateEnum billState) {
        this.billState = billState;
    }

    public BillPurposeEnum getBillPurpose() {
        return billPurpose;
    }

    public void setBillPurpose(BillPurposeEnum billPurpose) {
        this.billPurpose = billPurpose;
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

    public Set<String> getInStationCodeArray() {
        return inStationCodeArray;
    }

    public void setInStationCodeArray(Set<String> inStationCodeArray) {
        this.inStationCodeArray = inStationCodeArray;
    }

    public Set<String> getOutStationCodeArray() {
        return outStationCodeArray;
    }

    public void setOutStationCodeArray(Set<String> outStationCodeArray) {
        this.outStationCodeArray = outStationCodeArray;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getHqBill() {
        return hqBill;
    }

    public void setHqBill(String hqBill) {
        this.hqBill = hqBill;
    }

    public Set<String> getCargoCodeArray() {
        return cargoCodeArray;
    }

    public void setCargoCodeArray(Set<String> cargoCodeArray) {
        this.cargoCodeArray = cargoCodeArray;
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }

    public List<String> getOperatorCodes() {
        return operatorCodes;
    }

    public void setOperatorCodes(List<String> operatorCodes) {
        this.operatorCodes = operatorCodes;
    }
}
