package cn.sisyphe.coffee.bill.viewmodel;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单据查询条件
 * version: 1.0
 *
 * @author XiongJing
 */
public class ConditionQueryPurchaseBill extends BaseConditionQuery {

    /**
     * 录单名称
     */
    private String operatorName;
    /**
     * 录单开始时间
     */
    private String createStartTime;
    /**
     * 录单结束时间
     */
    private String createEndTime;
    /**
     * 入库单号
     */
    private String billCode;
    /**
     * 入库开始时间
     */
    private String inStartTime;
    /**
     * 入库结束时间
     */
    private String inEndTime;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 状态
     */
    private String statusCode;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(String createStartTime) {
        this.createStartTime = createStartTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getInStartTime() {
        return inStartTime;
    }

    public void setInStartTime(String inStartTime) {
        this.inStartTime = inStartTime;
    }

    public String getInEndTime() {
        return inEndTime;
    }

    public void setInEndTime(String inEndTime) {
        this.inEndTime = inEndTime;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ConditionQueryPurchaseBill{" +
                "operatorName='" + operatorName + '\'' +
                ", createStartTime='" + createStartTime + '\'' +
                ", createEndTime='" + createEndTime + '\'' +
                ", billCode='" + billCode + '\'' +
                ", inStartTime='" + inStartTime + '\'' +
                ", inEndTime='" + inEndTime + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", statusCode='" + statusCode + '\'' +
                "} " + super.toString();
    }
}
