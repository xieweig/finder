package cn.sisyphe.coffee.bill.viewmodel.deliverybill;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 配送DTO
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/4 10:21
 **/
public class DeliveryBillDTO implements Serializable {

    /**
     * item
     */
    private Set<DeliveryBillDetailDTO> billDetails = new HashSet<>();


    @JsonIgnore
    private Long billId;
    /**
     * 单据号
     */
    private String billCode;


    /**
     * type
     */
    @Enumerated(EnumType.STRING)
    private BillTypeEnum billType;

    /**
     * 单据作用
     */
    @Enumerated(EnumType.STRING)
    private BillPurposeEnum billPurpose;

    /**
     * 源单号
     */
    private String sourceCode;


    /**
     * 审核人编码
     */
    private String auditPersonCode;


    /**
     * 总数量
     */
    private Integer totalAmount;

    /**
     * 总品种
     */
    private Integer totalCount;

    /**
     * 操作人code
     */
    private String operatorCode;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 完成度
     */
    @Column
    private BigDecimal progress;

    /**
     * 出库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outStockTime;

    /**
     * 备注
     */
    private String memo;

    /**
     * convertBillDtoToBill
     *
     * @param deliveryBillDTO
     * @return
     */
    public DeliveryBill convertBillDtoToBill(DeliveryBillDTO deliveryBillDTO) {
        DeliveryBill deliveryBill = new DeliveryBill();
        //code
        deliveryBill.setBillCode(deliveryBillDTO.getBillCode());


        return deliveryBill;
    }

    /**
     * convertBillToDTO
     *
     * @param deliveryBill
     * @return
     */
    public DeliveryBillDTO convertBillToDTO(DeliveryBill deliveryBill) {
        this.billCode = deliveryBill.getBillCode();
        //单据类别
        this.billType = deliveryBill.getBillType();
        //单据用途
        this.billPurpose = deliveryBill.getBillPurpose();
        //总数量
        this.totalAmount = deliveryBill.getTotalAmount();
        //总品种
        this.totalCount = deliveryBill.getTotalCount();//
        // 备注
        this.memo = deliveryBill.getMemo();
        // 出库时间
        this.outStockTime = deliveryBill.getOutStockTime();
        // 来源单号
        this.sourceCode = deliveryBill.getSourceCode();
        // 录单人
        this.operatorName = deliveryBill.getOperatorName();
        //审核人code
        this.auditPersonCode = deliveryBill.getAuditPersonCode();
        // 总品种
        this.totalCount = deliveryBill.getTotalCount();
        //总数量
        this.totalAmount = deliveryBill.getTotalAmount();
        //
        this.billDetails = convertBillItemsToDTO(deliveryBill);
        return this;
    }

    /**
     * @param deliveryBill
     */
    private Set<DeliveryBillDetailDTO> convertBillItemsToDTO(DeliveryBill deliveryBill) {

        Set<DeliveryBillDetailDTO> billDetails = new HashSet<>();
        for (DeliveryBillDetail item : deliveryBill.getBillDetails()) {

            DeliveryBillDetailDTO deliveryBillDetailDTO = new DeliveryBillDetailDTO();

            //



            deliveryBillDetailDTO.setProgress(item.getProgress());// 进度
            billDetails.add(deliveryBillDetailDTO);
        }
        return billDetails;
    }

    public Date getOutStockTime() {
        return outStockTime;
    }

    public void setOutStockTime(Date outStockTime) {
        this.outStockTime = outStockTime;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public Set<DeliveryBillDetailDTO> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(Set<DeliveryBillDetailDTO> billDetails) {
        this.billDetails = billDetails;
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getAuditPersonCode() {
        return auditPersonCode;
    }

    public void setAuditPersonCode(String auditPersonCode) {
        this.auditPersonCode = auditPersonCode;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    @Override
    public String toString() {
        return "DeliveryBillDTO{" +
                "billDetails=" + billDetails +
                ", billId=" + billId +
                ", billCode='" + billCode + '\'' +
                ", billType=" + billType +
                ", billPurpose=" + billPurpose +
                ", sourceCode='" + sourceCode + '\'' +
                ", auditPersonCode='" + auditPersonCode + '\'' +
                ", totalAmount=" + totalAmount +
                ", totalCount=" + totalCount +
                ", operatorCode='" + operatorCode + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", progress=" + progress +
                ", outStockTime=" + outStockTime +
                ", memo='" + memo + '\'' +
                '}';
    }
}
