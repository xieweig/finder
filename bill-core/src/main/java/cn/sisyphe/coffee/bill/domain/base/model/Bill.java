package cn.sisyphe.coffee.bill.domain.base.model;


import cn.sisyphe.coffee.bill.domain.base.model.db.DbStation;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAllotStatusEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBillDetail;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDetailDTO;
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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 单据基础类
 * 封装了单据公共的属性，和dto序列化和反序列化方法。
 * 系统中的单据实体都必须继承本类，同时实现子类中自定义的扩展属性序列化和反序列化方法
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
     * 具体的单据类型，子计划转换的单据类型
     */
    @Enumerated(value = EnumType.STRING)
    private BillTypeEnum specificBillType;

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
     * 数量
     */
    private Integer totalAmount;

    /**
     * 品种数
     */
    private Integer totalVarietyAmount;

    /**
     * 按货物还是按原料
     */
    @Enumerated(EnumType.STRING)
    private BasicEnum basicEnum;


    /**
     * 备注
     */
    private String memo;


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


    // 显示数据 ----------------------------------

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
     * 出入库状态 -- 出/入库单
     */
    @Enumerated(EnumType.STRING)
    private BillInOrOutStateEnum inOrOutState;

    /**
     * 调拨状态 -- 入库单
     */
    @Enumerated(value = EnumType.STRING)
    private BillAllotStatusEnum allotStatus;

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
     * 差错单
     */
    @JsonIgnore
    @Transient
    private MistakeBill mistakeBill;


//    /**
//     * 反序列化，DTO转换成实体
//     * @param abstractBillDTO
//     * @param billDetails
//     */
//    public void unbuild(AbstractBillDTO abstractBillDTO, Set<AbstractBillDetailDTO> billDetails){
//            this.setBillCode(abstractBillDTO.getBillCode());
//
//            //解析子类的订单详情信息
//            unbuildDetails(billDetails);
//    }
//
//    /**
//     * 子类扩展的反序列化方法
//     * 子类必须实现
//     * @param abstractBillDetailDtos
//     */
//    public abstract void unbuildDetails(Set<AbstractBillDetailDTO> abstractBillDetailDtos);
//
//    /**
//     * 具体单据传输自定义但序列化扩展
//     * @param abstractBillDTO
//     */
//    protected abstract void unbuildExcend(AbstractBillDTO abstractBillDTO);


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


    public abstract String billCodePrefix();

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

    public BillTypeEnum getSpecificBillType() {
        return specificBillType;
    }

    public void setSpecificBillType(BillTypeEnum specificBillType) {
        this.specificBillType = specificBillType;
    }


    public Integer getTotalAmount() {
        return  totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalVarietyAmount() {
        return  totalVarietyAmount;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
                ", specificBillType=" + specificBillType +
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
                ", totalAmount=" + totalAmount +
                ", totalVarietyAmount=" + totalVarietyAmount +
                ", planMemo='" + planMemo + '\'' +
                ", outStorageMemo='" + outStorageMemo + '\'' +
                ", auditMemo='" + auditMemo + '\'' +
                ", basicEnum=" + basicEnum +
                ", allotStatus=" + allotStatus +
                ", mistakeBill=" + mistakeBill +
                "} " + super.toString();
    }

    public Boolean getSelf() {
        return BillTypeEnum.NO_PLAN.equals(getSpecificBillType());
    }
}
