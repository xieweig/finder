package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author bifenglin
 */
public class AddRestockBillDTO {

    private String BillCode;

    /**
     * 单据属性
     */
    private SourcePlanTypeEnum billProperty;
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

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 退库详情
     */
    private Set<RestockBillDetailDTO> billDetails;

    /**
     * 完成度
     */
    private BigDecimal progress;

    public String getRootCode() {
        return rootCode;
    }

    public void setRootCode(String rootCode) {
        this.rootCode = rootCode;
    }

    public String getBillCode() {
        return BillCode;
    }

    public void setBillCode(String billCode) {
        BillCode = billCode;
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }


    public SourcePlanTypeEnum getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(SourcePlanTypeEnum billProperty) {
        this.billProperty = billProperty;
    }

    public BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }

    public Set<RestockBillDetailDTO> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(Set<RestockBillDetailDTO> billDetails) {
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


    @Override
    public String toString() {
        return "AddRestockBillDTO{" +
                "BillCode='" + BillCode + '\'' +
                ", billProperty=" + billProperty +
                ", basicEnum=" + basicEnum +
                ", sourceCode='" + sourceCode + '\'' +
                ", rootCode='" + rootCode + '\'' +
                ", planMemo='" + planMemo + '\'' +
                ", outMemo='" + outMemo + '\'' +
                ", operatorCode='" + operatorCode + '\'' +
                ", inStation=" + inStation +
                ", outStation=" + outStation +
                ", totalPrice=" + totalPrice +
                ", billDetails=" + billDetails +
                ", progress=" + progress +
                '}';
    }

}
