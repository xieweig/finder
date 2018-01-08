package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.base.model.enums.*;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.restock.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.restock.enums.PropertyEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * bifenglin
 */
public class RestockBillDTO {

    /**
     * 单据号
     */
    private String billCode;

    /**
     * 单据种类
     */
    private BillTypeEnum billType;
    /**
     * 单据作用
     */
    private BillPurposeEnum billPurpose;

    /**
     * 出库位置
     */
    private AbstractLocation outLocation;

    /**
     * 入库位置
     */
    private AbstractLocation inLocation;

    /**
     * 源单号
     */
    private String sourceCode;

    /**
     * 发起单号
     */
    private String rootCode;

    /**
     * 操作人代码
     */
    private String operatorCode;
    /**
     * 审核人编码
     */
    private String auditPersonCode;

    /**
     * 单据属性
     */
    private PropertyEnum billProperty;
    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 按货物还是按原料
     */
    private BasicEnum basicEnum;

    /**
     * 来源单号
     */
    private String fromBillCode;

    /**
     * 退货数量
     */
    private int amount;

    /**
     * 退货品种数
     */
    private int variety;

    /**
     * 出库备注
     */
    private String outMemo;

    /**
     * 入库备注
     */
    private String planMemo;

    /**
     * 审核意见
     */
    private String auditMemo;

    /**
     * 入库时间
     */
    private Date inWareHouseTime;

    /**
     * 完成度
     */
    private BigDecimal progress;

    /**
     * 单据状态
     */
    private BillStateEnum billState = BillStateEnum.SAVED;

    /**
     * 提交状态
     */
    private BillSubmitStateEnum submitState;

    /**
     * 审核状态
     */
    private BillAuditStateEnum auditState;

    /**
     * 出入库状态
     */
    private BillInOrOutStateEnum inOrOutState;

    private Set<RestockBillDetailDTO> billDetails;

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public BillTypeEnum getBillType() {
        return billType;
    }

    public void setBillType(BillTypeEnum billType) {
        this.billType = billType;
    }

    public BillPurposeEnum getBillPurpose() {
        return billPurpose;
    }

    public void setBillPurpose(BillPurposeEnum billPurpose) {
        this.billPurpose = billPurpose;
    }

    public AbstractLocation getOutLocation() {
        return outLocation;
    }

    public void setOutLocation(AbstractLocation outLocation) {
        this.outLocation = outLocation;
    }

    public AbstractLocation getInLocation() {
        return inLocation;
    }

    public void setInLocation(AbstractLocation inLocation) {
        this.inLocation = inLocation;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getRootCode() {
        return rootCode;
    }

    public void setRootCode(String rootCode) {
        this.rootCode = rootCode;
    }

    public String getAuditPersonCode() {
        return auditPersonCode;
    }

    public void setAuditPersonCode(String auditPersonCode) {
        this.auditPersonCode = auditPersonCode;
    }

    public BillStateEnum getBillState() {
        return billState;
    }

    public void setBillState(BillStateEnum billState) {
        this.billState = billState;
    }

    public BillSubmitStateEnum getSubmitState() {
        return submitState;
    }

    public void setSubmitState(BillSubmitStateEnum submitState) {
        this.submitState = submitState;
    }

    public BillAuditStateEnum getAuditState() {
        return auditState;
    }

    public void setAuditState(BillAuditStateEnum auditState) {
        this.auditState = auditState;
    }

    public BillInOrOutStateEnum getInOrOutState() {
        return inOrOutState;
    }

    public void setInOrOutState(BillInOrOutStateEnum inOrOutState) {
        this.inOrOutState = inOrOutState;
    }

    public Set<RestockBillDetailDTO> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(Set<RestockBillDetailDTO> billDetails) {
        this.billDetails = billDetails;
    }

    public PropertyEnum getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(PropertyEnum billProperty) {
        this.billProperty = billProperty;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getFromBillCode() {
        return fromBillCode;
    }

    public void setFromBillCode(String fromBillCode) {
        this.fromBillCode = fromBillCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getVariety() {
        return variety;
    }

    public void setVariety(int variety) {
        this.variety = variety;
    }

    public String getOutMemo() {
        return outMemo;
    }

    public void setOutMemo(String outMemo) {
        this.outMemo = outMemo;
    }

    public String getPlanMemo() {
        return planMemo;
    }

    public void setPlanMemo(String planMemo) {
        this.planMemo = planMemo;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    public Date getInWareHouseTime() {
        return inWareHouseTime;
    }

    public void setInWareHouseTime(Date inWareHouseTime) {
        this.inWareHouseTime = inWareHouseTime;
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }
}
