package cn.sisyphe.coffee.bill.viewmodel.allot;

import cn.sisyphe.coffee.bill.domain.base.model.db.DbStation;
import cn.sisyphe.coffee.bill.domain.base.model.enums.*;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillOutStateEnum.NOT_OUTBOUND;

public class AllotBillDTO {
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
     * 归属站点
     */
    private String belongStationCode;

    /**
     * 出库位置
     */
    private AbstractLocation outLocation;

    /**
     * 入库位置
     */
    private AbstractLocation inLocation;

    /**
     * 数据库位置储存
     */
    private DbStation dbStation = new DbStation();

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
     * 物品明细
     */
    private Set<AllotBillDetailDTO> billDetails = new HashSet<>();

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

    /**
     * 出库时间
     */
    private Date outWareHouseTime;

    /**
     * 入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inWareHouseTime;


    /**
     * 出库状态编码
     */
    private BillOutStateEnum outStateEnum = NOT_OUTBOUND;

    /**
     * 数量
     */
    private Integer totalAmount;

    /**
     * 品种数
     */
    private Integer totalVarietyAmount;

    /**
     * 计划备注
     */
    private String planMemo;

    /**
     * 出库备注
     */
    private String outStorageMemo;

    /**
     * 审核意见
     */
    private String auditMemo;

    /**
     * 按货物还是按原料
     */
    private BasicEnum basicEnum;

    /**
     * 完成度
     */
    private BigDecimal progress;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 单据来源类型
     */
    private SourcePlanTypeEnum billProperty;

    /**
     * 单据编码前缀
     */
    private String billCodePrefix;

    /**
     * 具体的单据类型
     */
    private BillTypeEnum specificBillType;

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

    public String getBelongStationCode() {
        return belongStationCode;
    }

    public void setBelongStationCode(String belongStationCode) {
        this.belongStationCode = belongStationCode;
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

    public DbStation getDbStation() {
        return dbStation;
    }

    public void setDbStation(DbStation dbStation) {
        this.dbStation = dbStation;
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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getAuditPersonName() {
        return auditPersonName;
    }

    public void setAuditPersonName(String auditPersonName) {
        this.auditPersonName = auditPersonName;
    }

    public Set<AllotBillDetailDTO> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(Set<AllotBillDetailDTO> billDetails) {
        this.billDetails = billDetails;
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

    public Date getOutWareHouseTime() {
        return outWareHouseTime;
    }

    public void setOutWareHouseTime(Date outWareHouseTime) {
        this.outWareHouseTime = outWareHouseTime;
    }

    public Date getInWareHouseTime() {
        return inWareHouseTime;
    }

    public void setInWareHouseTime(Date inWareHouseTime) {
        this.inWareHouseTime = inWareHouseTime;
    }

    public BillOutStateEnum getOutStateEnum() {
        return outStateEnum;
    }

    public void setOutStateEnum(BillOutStateEnum outStateEnum) {
        this.outStateEnum = outStateEnum;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalVarietyAmount() {
        return totalVarietyAmount;
    }

    public void setTotalVarietyAmount(Integer totalVarietyAmount) {
        this.totalVarietyAmount = totalVarietyAmount;
    }

    public String getPlanMemo() {
        return planMemo;
    }

    public void setPlanMemo(String planMemo) {
        this.planMemo = planMemo;
    }

    public String getOutStorageMemo() {
        return outStorageMemo;
    }

    public void setOutStorageMemo(String outStorageMemo) {
        this.outStorageMemo = outStorageMemo;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    public BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public SourcePlanTypeEnum getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(SourcePlanTypeEnum billProperty) {
        this.billProperty = billProperty;
    }

    public String getBillCodePrefix() {
        return billCodePrefix;
    }

    public void setBillCodePrefix(String billCodePrefix) {
        this.billCodePrefix = billCodePrefix;
    }

    @Override
    public String toString() {
        return "AllotBillDTO{" +
                "billCode='" + billCode + '\'' +
                ", billType=" + billType +
                ", billPurpose=" + billPurpose +
                ", belongStationCode='" + belongStationCode + '\'' +
                ", outLocation=" + outLocation +
                ", inLocation=" + inLocation +
                ", dbStation=" + dbStation +
                ", sourceCode='" + sourceCode + '\'' +
                ", rootCode='" + rootCode + '\'' +
                ", billDetails=" + billDetails +
                ", billState=" + billState +
                ", submitState=" + submitState +
                ", auditState=" + auditState +
                ", inOrOutState=" + inOrOutState +
                ", outWareHouseTime=" + outWareHouseTime +
                ", inWareHouseTime=" + inWareHouseTime +
                ", outStateEnum=" + outStateEnum +
                ", totalAmount=" + totalAmount +
                ", totalVarietyAmount=" + totalVarietyAmount +
                ", planMemo='" + planMemo + '\'' +
                ", outStorageMemo='" + outStorageMemo + '\'' +
                ", auditMemo='" + auditMemo + '\'' +
                ", basicEnum=" + basicEnum +
                ", progress=" + progress +
                ", totalPrice=" + totalPrice +
                ", billProperty=" + billProperty +
                ", billCodePrefix='" + billCodePrefix + '\'' +
                '}';
    }
}
