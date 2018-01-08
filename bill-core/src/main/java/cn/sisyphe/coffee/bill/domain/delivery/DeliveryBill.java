package cn.sisyphe.coffee.bill.domain.delivery;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yichuan on 2017/12/19 17:33
 * Description: 配送单
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class DeliveryBill extends Bill<DeliveryBillDetail> {

    public DeliveryBill() {
        setBillType(BillTypeEnum.DELIVERY);
    }

    /**
     * 总数量
     */
    @Column
    private Integer totalAmount;

    /**
     * 总品种
     */
    @Column
    private Integer totalCount;

    /**
     * 操作人code
     */
    @Column
    private String operatorCode;

    /**
     * 操作人姓名
     */
    @Column
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
    @Column(length = 255)
    private String memo;


    /**
     *  审核意见
     */
    private String auditOpinion;


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

    @Override
    public String getOperatorCode() {
        return operatorCode;
    }

    @Override
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

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public Date getOutStockTime() {
        return outStockTime;
    }

    public void setOutStockTime(Date outStockTime) {
        this.outStockTime = outStockTime;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    @Override
    public String toString() {
        return "DeliveryBill{" +
                "totalAmount=" + totalAmount +
                ", totalCount=" + totalCount +
                ", operatorCode='" + operatorCode + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", progress=" + progress +
                ", outStockTime=" + outStockTime +
                ", memo='" + memo + '\'' +
                ", auditOpinion='" + auditOpinion + '\'' +
                '}';
    }
}
