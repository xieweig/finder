package cn.sisyphe.coffee.bill.viewmodel.deliverybill;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018/1/4.
 */
public class DeliveryBillDTO implements Serializable {

    /**
     * item
     */
    private Set<DeliveryBillDetailDTO> billDetails = new HashSet<>();
    /**
     * 完成度
     */
    private BigDecimal progress;
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
     * 备注
     */
    private String memo;

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
}
