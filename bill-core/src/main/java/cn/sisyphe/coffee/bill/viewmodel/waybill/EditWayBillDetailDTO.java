package cn.sisyphe.coffee.bill.viewmodel.waybill;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 运单编辑(添加，修改明细DTO)
 */
public class EditWayBillDetailDTO implements Serializable {


    /**
     * 包号
     */
    private String packageNumbers;

    /**
     * 明细id
     */
    private Long billDetailId;

    /**
     * 运单
     */
    private EditWayBillDTO editWayBillDTO;

    /**
     * 明细code
     */
    @Transient
    private String billDetailCode;


    public EditWayBillDetailDTO() {
    }

    public EditWayBillDetailDTO(EditWayBillDTO editWayBillDTO) {
        this.editWayBillDTO = editWayBillDTO;
        billDetailCode = editWayBillDTO.getWayBillCode();//
    }

    /**
     * 出库单号
     */
    private String outStorageBillCode;//

    /**
     * 录单人
     */
    private String operatorName;


    /**
     * 打包方式
     */
    private String packageType;

    /**
     * 品种数
     */

    private Integer totalCount;

    /**
     * 货物数
     */
    private Integer totalAmount;
    /**
     * 入库站点
     */
    private String inStationCode;

    /**
     * 出库站点
     */
    private String outStationCode;

    /**
     * 是否单独打包
     */
    public Boolean singlePacking;



    public Boolean getSinglePacking() {
        return singlePacking;
    }

    public void setSinglePacking(Boolean singlePacking) {
        this.singlePacking = singlePacking;
    }

    /**
     * 出库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outStorageTime;

    public String getBillDetailCode() {
        return billDetailCode;
    }

    public void setBillDetailCode(String billDetailCode) {
        this.billDetailCode = billDetailCode;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public EditWayBillDTO getEditWayBillDTO() {
        return editWayBillDTO;
    }

    public void setEditWayBillDTO(EditWayBillDTO editWayBillDTO) {
        this.editWayBillDTO = editWayBillDTO;
    }


    public String getOutStorageBillCode() {
        return outStorageBillCode;
    }

    public void setOutStorageBillCode(String outStorageBillCode) {
        this.outStorageBillCode = outStorageBillCode;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getInStationCode() {
        return inStationCode;
    }

    public void setInStationCode(String inStationCode) {
        this.inStationCode = inStationCode;
    }

    public String getOutStationCode() {
        return outStationCode;
    }

    public void setOutStationCode(String outStationCode) {
        this.outStationCode = outStationCode;
    }


    public Date getOutStorageTime() {
        return outStorageTime;
    }

    public void setOutStorageTime(Date outStorageTime) {
        this.outStorageTime = outStorageTime;
    }

    public Long getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Long billDetailId) {
        this.billDetailId = billDetailId;
    }

    public String getPackageNumbers() {
        return packageNumbers;
    }

    public void setPackageNumbers(String packageNumbers) {
        this.packageNumbers = packageNumbers;
    }
}
