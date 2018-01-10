package cn.sisyphe.coffee.bill.viewmodel.adjust;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：多条件查询中的单条调剂单据信息
 * version: 1.0
 *
 * @author XiongJing
 */
public class AdjustBillDTO {

    /**
     * 调剂单号
     */
    private String billCode;
    /**
     * 单据属性
     */
    private SourcePlanTypeEnum billTypeStr;
    /**
     * 出库状态
     */
    private BillOutStateEnum outStatusCode;
    /**
     * 提交状态
     */
    private String submitState;
    /**
     * 审核状态
     */
    private String auditState;
    /**
     * 发起单号
     */
    private String rootCode;

    /**
     * 来源单号
     */
    private String sourceCode;
    /**
     * 录单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 出库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outWareHouseTime;
    /**
     * 录单人名称
     */
    private String operatorName;
    /**
     * 审核人
     */
    private String auditorName;
    /**
     * 出库站点
     */
    private String outStationCode;
    /**
     * 入库站点
     */
    private String inStationCode;
    /**
     * 按照原料还是按照货物拣货
     */
    private BasicEnum basicEnum;
    /**
     * 配送数量
     */
    private Integer adjustNumber;
    /**
     * 单据主状态
     */
    private BillStateEnum billState;
    /**
     * 配送品种数
     */
    private Integer varietyNumber;

    public SourcePlanTypeEnum getBillTypeStr() {
        return billTypeStr;
    }

    public void setBillTypeStr(SourcePlanTypeEnum billTypeStr) {
        this.billTypeStr = billTypeStr;
    }

    public BillOutStateEnum getOutStatusCode() {
        return outStatusCode;
    }

    public void setOutStatusCode(BillOutStateEnum outStatusCode) {
        this.outStatusCode = outStatusCode;
    }

    public String getSubmitState() {
        return submitState;
    }

    public void setSubmitState(String submitState) {
        this.submitState = submitState;
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    public String getRootCode() {
        return rootCode;
    }

    public void setRootCode(String rootCode) {
        this.rootCode = rootCode;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOutWareHouseTime() {
        return outWareHouseTime;
    }

    public void setOutWareHouseTime(Date outWareHouseTime) {
        this.outWareHouseTime = outWareHouseTime;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOutStationCode() {
        return outStationCode;
    }

    public void setOutStationCode(String outStationCode) {
        this.outStationCode = outStationCode;
    }

    public String getInStationCode() {
        return inStationCode;
    }

    public void setInStationCode(String inStationCode) {
        this.inStationCode = inStationCode;
    }

    public BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }

    public Integer getAdjustNumber() {
        return adjustNumber;
    }

    public void setAdjustNumber(Integer adjustNumber) {
        this.adjustNumber = adjustNumber;
    }

    public BillStateEnum getBillState() {
        return billState;
    }

    public void setBillState(BillStateEnum billState) {
        this.billState = billState;
    }

    public Integer getVarietyNumber() {
        return varietyNumber;
    }

    public void setVarietyNumber(Integer varietyNumber) {
        this.varietyNumber = varietyNumber;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Override
    public String toString() {
        return "AdjustBillDTO{" +
                "billTypeStr='" + billTypeStr + '\'' +
                ", outStatusCode=" + outStatusCode +
                ", submitState='" + submitState + '\'' +
                ", auditState='" + auditState + '\'' +
                ", rootCode='" + rootCode + '\'' +
                ", billCode='" + billCode + '\'' +
                ", createTime=" + createTime +
                ", outWareHouseTime=" + outWareHouseTime +
                ", operatorName='" + operatorName + '\'' +
                ", auditorName='" + auditorName + '\'' +
                ", outStationCode='" + outStationCode + '\'' +
                ", inStationCode='" + inStationCode + '\'' +
                ", basicEnum=" + basicEnum +
                ", adjustNumber=" + adjustNumber +
                ", billState=" + billState +
                ", varietyNumber=" + varietyNumber +
                '}';
    }
}
