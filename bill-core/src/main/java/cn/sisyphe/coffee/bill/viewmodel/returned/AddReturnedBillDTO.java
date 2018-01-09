package cn.sisyphe.coffee.bill.viewmodel.returned;


import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.returned.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.returned.enums.PropertyEnum;

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
     * 源单号
     */
    private String sourceCode;

    /**
     * 发起单号
     */
    private String rootCode;

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

    //完成度
    private BigDecimal progress;

    public String getRootCode() {
        return rootCode;
    }

    public void setRootCode(String rootCode) {
        this.rootCode = rootCode;
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public cn.sisyphe.coffee.bill.domain.returned.enums.PropertyEnum getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(cn.sisyphe.coffee.bill.domain.returned.enums.PropertyEnum billProperty) {
        this.billProperty = billProperty;
    }

    public cn.sisyphe.coffee.bill.domain.returned.enums.BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(cn.sisyphe.coffee.bill.domain.returned.enums.BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }

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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
