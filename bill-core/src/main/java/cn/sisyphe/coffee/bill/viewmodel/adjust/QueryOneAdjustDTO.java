package cn.sisyphe.coffee.bill.viewmodel.adjust;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by XiongJing on 2018/1/9.
 * remark：
 * version:
 *
 * @author XiongJing
 */
public class QueryOneAdjustDTO {

    /**
     * 单据编码
     */
    private String billCode;
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
     * 录单人
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
     * 出库库位
     */
    private String outStorageCode;
    /**
     * 入库站点
     */
    private String inStationCode;
    /**
     * 单据属性
     */
    private String billTypeStr;
    /**
     * 出库状态
     */
    private BillOutStateEnum outStateEnum;
    /**
     * 提交状态
     */
    private BillSubmitStateEnum submitState;
    /**
     * 审核状态
     */
    private BillAuditStateEnum auditState;
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
     * 调剂货物计划详情
     */
    private List<AdjustBillDetailDTO> details;
    /**
     * 调剂原料计划详情
     */
    private List<AdjustBillMaterialDetailDTO> materialDetails;

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

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getOutStationCode() {
        return outStationCode;
    }

    public void setOutStationCode(String outStationCode) {
        this.outStationCode = outStationCode;
    }

    public String getOutStorageCode() {
        return outStorageCode;
    }

    public void setOutStorageCode(String outStorageCode) {
        this.outStorageCode = outStorageCode;
    }

    public String getInStationCode() {
        return inStationCode;
    }

    public void setInStationCode(String inStationCode) {
        this.inStationCode = inStationCode;
    }

    public String getBillTypeStr() {
        return billTypeStr;
    }

    public void setBillTypeStr(String billTypeStr) {
        this.billTypeStr = billTypeStr;
    }

    public BillOutStateEnum getOutStateEnum() {
        return outStateEnum;
    }

    public void setOutStateEnum(BillOutStateEnum outStateEnum) {
        this.outStateEnum = outStateEnum;
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

    public List<AdjustBillMaterialDetailDTO> getMaterialDetails() {
        return materialDetails;
    }

    public void setMaterialDetails(List<AdjustBillMaterialDetailDTO> materialDetails) {
        this.materialDetails = materialDetails;
    }

    @Override
    public String toString() {
        return "QueryOneAdjustDTO{" +
                "billCode='" + billCode + '\'' +
                ", createTime=" + createTime +
                ", outWareHouseTime=" + outWareHouseTime +
                ", operatorName='" + operatorName + '\'' +
                ", auditorName='" + auditorName + '\'' +
                ", outStationCode='" + outStationCode + '\'' +
                ", outStorageCode='" + outStorageCode + '\'' +
                ", inStationCode='" + inStationCode + '\'' +
                ", billTypeStr='" + billTypeStr + '\'' +
                ", outStateEnum=" + outStateEnum +
                ", submitState=" + submitState +
                ", auditState=" + auditState +
                ", adjustNumber=" + adjustNumber +
                ", varietyNumber=" + varietyNumber +
                ", planMemo='" + planMemo + '\'' +
                ", outStorageMemo='" + outStorageMemo + '\'' +
                ", auditMemo='" + auditMemo + '\'' +
                ", details=" + details +
                ", materialDetails=" + materialDetails +
                '}';
    }
}
