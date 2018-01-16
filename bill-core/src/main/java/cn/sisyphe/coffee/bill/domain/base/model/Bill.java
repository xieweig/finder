package cn.sisyphe.coffee.bill.domain.base.model;


import cn.sisyphe.coffee.bill.domain.base.model.db.DbStation;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAllotStatusEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.SourcePlanTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillOutStateEnum.NOT_OUTBOUND;

/**
 * 单据基础类
 *
 * @author heyong
 */
@MappedSuperclass
public abstract class Bill<T extends BillDetail> extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;

    /**
     * 单据号
     */
    @Column(unique = true, updatable = false)
    private String billCode;

    /**
     * 单据种类
     */
    @Enumerated(EnumType.STRING)
    private BillTypeEnum billType;
    /**
     * 单据作用
     */
    @Enumerated(EnumType.STRING)
    private BillPurposeEnum billPurpose;

    /**
     * 归属站点
     */
    private String belongStationCode;

    /**
     * 出库位置
     */
    @Transient
    private AbstractLocation outLocation;

    /**
     * 入库位置
     */
    @Transient
    private AbstractLocation inLocation;

    /**
     * 数据库位置储存
     */
    @JsonIgnore
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
    private String operatorCode;
    /**
     * 审核人编码
     */
    private String auditPersonCode;
    /**
     * 物品明细
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    // 去掉外健
    @org.hibernate.annotations.ForeignKey(name = "none")
    // 设置子类的 billCode
    @JoinColumn(name = "billCode", referencedColumnName = "billCode")
    private Set<T> billDetails = new HashSet<>();

    /**
     * 单据状态
     */
    @Enumerated(EnumType.STRING)
    private BillStateEnum billState = BillStateEnum.SAVED;

    /**
     * 提交状态
     */
    @Enumerated(EnumType.STRING)
    private BillSubmitStateEnum submitState;

    /**
     * 审核状态
     */
    @Enumerated(EnumType.STRING)
    private BillAuditStateEnum auditState;

    /**
     * 出入库状态
     */
    @Enumerated(EnumType.STRING)
    private BillInOrOutStateEnum inOrOutState;


    /**
     * 更新前, 在数据库操作中调用
     */
    public void update() {
        if (inLocation != null) {
            dbStation.setInLocation(inLocation);
        }

        if (outLocation != null) {
            dbStation.setOutLocation(outLocation);
        }
    }

    /**
     * 载入
     */
    @PostLoad
    @PostPersist
    public void load() {
        if (dbStation == null) {
            return;
        }

        inLocation = dbStation.getInLocation();
        outLocation = dbStation.getOutLocation();
    }

    /**
     * 出库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outWareHouseTime;

    /**
     * 入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inWareHouseTime;


    /**
     * 出库状态编码
     */
    @Enumerated(EnumType.STRING)
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
    @Enumerated(EnumType.STRING)
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
    @Enumerated(value = EnumType.STRING)
    private SourcePlanTypeEnum billProperty;

    /**
     * 调拨状态
     */
    @Enumerated(value = EnumType.STRING)
    private BillAllotStatusEnum allotStatus;

    /**
     * 单据编码前缀
     */
    @Transient
    private String billCodePrefix;


    /**
     * 差错单
     */
    @JsonIgnore
    @Transient
    private MistakeBill mistakeBill;


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

    public BillTypeEnum getBillType() {
        return billType;
    }

    public void setBillType(BillTypeEnum billType) {
        this.billType = billType;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
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

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public Set<T> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(Set<T> billDetails) {
        this.billDetails = billDetails;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public BillStateEnum getBillState() {
        return billState;
    }

    public void setBillState(BillStateEnum billState) {
        this.billState = billState;
    }

    public String getAuditPersonCode() {
        return auditPersonCode;
    }

    public void setAuditPersonCode(String auditPersonCode) {
        this.auditPersonCode = auditPersonCode;
    }

    public void addBillDetails(T billDetails) {
        this.billDetails.add(billDetails);
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

    public DbStation getDbStation() {
        return dbStation;
    }

    public void setDbStation(DbStation dbStation) {
        this.dbStation = dbStation;
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

    public SourcePlanTypeEnum getBillProperty() {
        return billProperty;
    }

    public void setBillProperty(SourcePlanTypeEnum billProperty) {
        this.billProperty = billProperty;
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

    public String getBillCodePrefix() {
        return billCodePrefix;
    }

    public void setBillCodePrefix(String billCodePrefix) {
        this.billCodePrefix = billCodePrefix;
    }

    public MistakeBill getMistakeBill() {
        return mistakeBill;
    }

    public void setMistakeBill(MistakeBill mistakeBill) {
        this.mistakeBill = mistakeBill;
    }

    public BillAllotStatusEnum getAllotStatus() {
        return allotStatus;
    }

    public void setAllotStatus(BillAllotStatusEnum allotStatus) {
        this.allotStatus = allotStatus;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", billCode='" + billCode + '\'' +
                ", billType=" + billType +
                ", billPurpose=" + billPurpose +
                ", belongStationCode='" + belongStationCode + '\'' +
                ", outLocation=" + outLocation +
                ", inLocation=" + inLocation +
                ", dbStation=" + dbStation +
                ", sourceCode='" + sourceCode + '\'' +
                ", rootCode='" + rootCode + '\'' +
                ", operatorCode='" + operatorCode + '\'' +
                ", auditPersonCode='" + auditPersonCode + '\'' +
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
                "} " + super.toString();
    }
}
