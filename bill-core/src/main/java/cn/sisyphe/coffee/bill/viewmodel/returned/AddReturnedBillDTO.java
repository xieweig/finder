package cn.sisyphe.coffee.bill.viewmodel.returned;


import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.returned.enums.PropertyEnum;
import cn.sisyphe.coffee.bill.domain.returned.enums.BasicEnum;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author mayupeng
 * @Date 2018/01/07
 * @description 退货计划单
 */
public class AddReturnedBillDTO {

    /**
     * 单据属性
     */
    private PropertyEnum billProperty;
    /**
     * 按货物还是按种类
     */
    private BasicEnum basicEnum;

    /**
     * 来源单号
     */
    private String fromBillCode;
    /**
     * 计划备注
     */
    private String planMemo;
    /**
     * 出库备注
     */
    private String outMemo;

    /**
     * 操作人代码
     */
    private String operatorCode;
//    /**
//     * 入库库位
//     */
//    private Storage inStorage;
//    /**
//     * 出库库位
//     */
//    private Storage outStorage;
    /**
     * 入库站点
     */
    private Station inStation;
    /**
     * 出库站点
     */
    private Station outStation;
    /**
     * 单据编码
     */
    private String billCode;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 退库详情
     */
    private Set<ReturnedBillDetailDTO> billDetails;

    public PropertyEnum getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(PropertyEnum billProperty) {
        this.billProperty = billProperty;
    }

    public BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }

//    public Storage getInStorage() {
//        return inStorage;
//    }
//
//    public void setInStorage(Storage inStorage) {
//        this.inStorage = inStorage;
//    }

    public Set<ReturnedBillDetailDTO> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(Set<ReturnedBillDetailDTO> billDetails) {
        this.billDetails = billDetails;
    }

    public String getPlanMemo() {
        return planMemo;
    }

    public void setPlanMemo(String planMemo) {
        this.planMemo = planMemo;
    }

    public String getOutMemo() {
        return outMemo;
    }

    public void setOutMemo(String outMemo) {
        this.outMemo = outMemo;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

//    public Storage getOutStorage() {
//        return outStorage;
//    }
//
//    public void setOutStorage(Storage outStorage) {
//        this.outStorage = outStorage;
//    }

    public Station getInStation() {
        return inStation;
    }

    public void setInStation(Station inStation) {
        this.inStation = inStation;
    }

    public Station getOutStation() {
        return outStation;
    }

    public void setOutStation(Station outStation) {
        this.outStation = outStation;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getFromBillCode() {
        return fromBillCode;
    }

    public void setFromBillCode(String fromBillCode) {
        this.fromBillCode = fromBillCode;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
