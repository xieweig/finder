package cn.sisyphe.coffee.bill.viewmodel.adjust;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillOutStateEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：多条件查询中的单条调拨单据信息
 * version: 1.0
 *
 * @author XiongJing
 */
public class AdjustBillDTO {

    /**
     * 单据属性
     */
    private String billTypeStr;

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
     * 来源单号
     */
    private String rootCode;

    /**
     * 出库单号
     */
    private String outStrogeCode;

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
     * 录单人编码
     */
    private String operatorCode;

    /**
     * 审核人
     */
    private String auditPersonCode;

    /**
     * 出库站点
     */
    private String outStationCode;

    /**
     * 入库站点
     */
    private String inStationCode;

    /**
     * 配送数量
     */
    private Integer adjustNumber;

    /**
     * 配送品种数
     */
    private Integer varietyNumber;

    public String getBillTypeStr() {
        return billTypeStr;
    }

    public void setBillTypeStr(String billTypeStr) {
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

    public String getOutStrogeCode() {
        return outStrogeCode;
    }

    public void setOutStrogeCode(String outStrogeCode) {
        this.outStrogeCode = outStrogeCode;
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

    public Integer getAdjustNumber() {
        return adjustNumber;
    }

    public void setAdjustNumber(Integer adjustNumber) {
        this.adjustNumber = adjustNumber;
    }

    public Integer getVarietyNumber() {
        return varietyNumber;
    }

    public void setVarietyNumber(Integer varietyNumber) {
        this.varietyNumber = varietyNumber;
    }

    @Override
    public String toString() {
        return "AdjustBillDTO{" +
                "billTypeStr='" + billTypeStr + '\'' +
                ", outStatusCode=" + outStatusCode +
                ", submitState='" + submitState + '\'' +
                ", auditState='" + auditState + '\'' +
                ", rootCode='" + rootCode + '\'' +
                ", outStrogeCode='" + outStrogeCode + '\'' +
                ", createTime=" + createTime +
                ", outWareHouseTime=" + outWareHouseTime +
                ", operatorCode='" + operatorCode + '\'' +
                ", auditPersonCode='" + auditPersonCode + '\'' +
                ", outStationCode='" + outStationCode + '\'' +
                ", inStationCode='" + inStationCode + '\'' +
                ", adjustNumber=" + adjustNumber +
                ", varietyNumber=" + varietyNumber +
                '}';
    }
}
