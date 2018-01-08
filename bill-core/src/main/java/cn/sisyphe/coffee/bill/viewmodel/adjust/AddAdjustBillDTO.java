package cn.sisyphe.coffee.bill.viewmodel.adjust;

import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;

import java.util.Date;
import java.util.List;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：新增和修改调剂单DTO
 * version: 1.0
 *
 * @author XiongJing
 */
public class AddAdjustBillDTO {

    /**
     * 单据编码
     */
    private String billCode;
    /**
     * 出库时间
     */
    private Date outWareHouseTime;
    /**
     * 录单人编码
     */
    private String operatorCode;
    /**
     * 审核人编码
     */
    private String auditPersonCode;
    /**
     * 出库站点编码
     */
    private String outStationCode;
    /**
     * 出库库位
     */
    private Storage outStorage;

    /**
     * 入库站点编码
     */
    private String inStationCode;

    /**
     * 单据属性
     */
    private String billType;

    /**
     * 出库状态编码
     */
    private String outStatusCode;

    /**
     * 提交状态
     */
    private String submitState;

    /**
     * 审核状态
     */
    private String auditState;

    /**
     * 调剂数量
     */
    private Integer adjustNumber;

    /**
     * 调剂品种数
     */
    private Integer varietyNumber;

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
     * 货物/原料明细信息
     */
    private List<AdjustBillDetailDTO> details;

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
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

    public Storage getOutStorage() {
        return outStorage;
    }

    public void setOutStorage(Storage outStorage) {
        this.outStorage = outStorage;
    }

    public String getInStationCode() {
        return inStationCode;
    }

    public void setInStationCode(String inStationCode) {
        this.inStationCode = inStationCode;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getOutStatusCode() {
        return outStatusCode;
    }

    public void setOutStatusCode(String outStatusCode) {
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

    public List<AdjustBillDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<AdjustBillDetailDTO> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "AddAdjustBillDTO{" +
                "billCode='" + billCode + '\'' +
                ", outWareHouseTime=" + outWareHouseTime +
                ", operatorCode='" + operatorCode + '\'' +
                ", auditPersonCode='" + auditPersonCode + '\'' +
                ", outStationCode='" + outStationCode + '\'' +
                ", outStorage=" + outStorage +
                ", inStationCode='" + inStationCode + '\'' +
                ", billType='" + billType + '\'' +
                ", outStatusCode='" + outStatusCode + '\'' +
                ", submitState='" + submitState + '\'' +
                ", auditState='" + auditState + '\'' +
                ", adjustNumber=" + adjustNumber +
                ", varietyNumber=" + varietyNumber +
                ", planMemo='" + planMemo + '\'' +
                ", outStorageMemo='" + outStorageMemo + '\'' +
                ", auditMemo='" + auditMemo + '\'' +
                ", details=" + details +
                '}';
    }
}
