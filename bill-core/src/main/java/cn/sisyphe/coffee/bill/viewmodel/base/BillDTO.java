package cn.sisyphe.coffee.bill.viewmodel.base;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAllotStatusEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.sum;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.BillOutStateEnum.NOT_OUTBOUND;

/**
 * 单据DTO基类
 *
 * @param
 * @author bifenglin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillDTO<T extends BillDetailDTO> {

    /**
     * 录单时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 单据号
     */
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
    private Station outLocation;

    /**
     * 入库位置
     */
    private Station inLocation;
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
     * 操作人代码
     */
    private String operatorName;

    /**
     * 审核人编码
     */
    private String auditPersonCode;

    /**
     * 审核人编码
     */
    private String auditPersonName;

    /**
     * 物品明细
     */
    private Set<T> billDetails;

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
     * 出库时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outWareHouseTime;

    /**
     * 入库时间
     */
    @Temporal(TemporalType.TIMESTAMP)
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
     * 单据来源类型
     */
    @Enumerated(EnumType.STRING)
    private BillTypeEnum specificBillType;

    /**
     * 调拨状态
     */
    @Enumerated(value = EnumType.STRING)
    private BillAllotStatusEnum allotStatus;

    /**
     * 单据编码前缀
     */
    private String billCodePrefix;


    /**
     * 差错单
     */
    @JsonIgnore
    private MistakeBill mistakeBill;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Station getOutLocation() {
        return outLocation;
    }

    public void setOutLocation(Station outLocation) {
        this.outLocation = outLocation;
    }

    public Station getInLocation() {
        return inLocation;
    }

    public void setInLocation(Station inLocation) {
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

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getAuditPersonCode() {
        return auditPersonCode;
    }

    public void setAuditPersonCode(String auditPersonCode) {
        this.auditPersonCode = auditPersonCode;
    }

    public Set<T> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(Set<T> billDetails) {
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
        return sum(this.billDetails, on(BillDetailDTO.class).getActualAmount());
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalVarietyAmount() {
        return billDetails.size();
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

    public BillTypeEnum getSpecificBillType() {
        return specificBillType;
    }

    public void setSpecificBillType(BillTypeEnum specificBillType) {
        this.specificBillType = specificBillType;
    }

    public BillAllotStatusEnum getAllotStatus() {
        return allotStatus;
    }

    public void setAllotStatus(BillAllotStatusEnum allotStatus) {
        this.allotStatus = allotStatus;
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
}
