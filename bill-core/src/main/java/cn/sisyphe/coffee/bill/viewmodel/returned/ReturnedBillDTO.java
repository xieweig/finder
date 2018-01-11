package cn.sisyphe.coffee.bill.viewmodel.returned;

import cn.sisyphe.coffee.bill.domain.base.model.enums.*;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * @author mayupeng
 */
public class ReturnedBillDTO {
    /**
     * 创建时间
     */
    private Date createTime;
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
    private String operatorName;
    /**
     * 审核人编码
     */
    private String auditPersonName;

    /**
     * 单据属性
     */
    private SourcePlanTypeEnum billProperty;
    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 按货物还是按原料
     */
    private BasicEnum basicEnum;

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

    private Set<ReturnedBillDetailDTO> billDetails;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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

    public String getAuditPersonName() {
        return auditPersonName;
    }

    public void setAuditPersonName(String auditPersonName) {
        this.auditPersonName = auditPersonName;
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

    public Set<ReturnedBillDetailDTO> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(Set<ReturnedBillDetailDTO> billDetails) {
        this.billDetails = billDetails;
    }

    public SourcePlanTypeEnum getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(SourcePlanTypeEnum billProperty) {
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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
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
