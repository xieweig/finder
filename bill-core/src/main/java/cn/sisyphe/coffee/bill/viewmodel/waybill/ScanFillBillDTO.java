package cn.sisyphe.coffee.bill.viewmodel.waybill;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/9 16:16
 **/
public class ScanFillBillDTO implements Serializable {

    /**
     * 单据类型
     */
    private String billType;

    private String billCode;
    //总数量
    private Integer totalAmount;
    //总品种
    private Integer totalCount;
    // 录单人
    private String operatorCode;

    /**
     * 出库站点code
     */
    private String outStationCode;//

    /**
     * 入库站点
     */
    private String inStationCode;

    /**
     * 入库站点名称
     */
    private String inStationName;


    /**
     * 出库站点名称
     */
    private String outStationName;

    private String operatorName;
    //包号
    //包号可以不自动提取
    private List<String> packNumbers;
    /**
     * 出库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outStockTime;


    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
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

    public List<String> getPackNumbers() {
        return packNumbers;
    }

    public void setPackNumbers(List<String> packNumbers) {
        this.packNumbers = packNumbers;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public Date getOutStockTime() {
        return outStockTime;
    }

    public void setOutStockTime(Date outStockTime) {
        this.outStockTime = outStockTime;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getBillType() {

        return billType;
    }

    public String getInStationName() {

        return inStationName;
    }

    public void setInStationName(String inStationName) {
        this.inStationName = inStationName;
    }

    public String getOutStationName() {
        return outStationName;
    }

    public void setOutStationName(String outStationName) {
        this.outStationName = outStationName;
    }

    public void setBillType(String billType) {
        this.billType = billType;
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

    @Override
    public String toString() {
        return "ScanFillBillDTO{" +
                "billType='" + billType + '\'' +
                ", billCode='" + billCode + '\'' +
                ", totalAmount=" + totalAmount +
                ", totalCount=" + totalCount +
                ", operatorCode='" + operatorCode + '\'' +
                ", outStationCode='" + outStationCode + '\'' +
                ", inStationCode='" + inStationCode + '\'' +
                ", inStationName='" + inStationName + '\'' +
                ", outStationName='" + outStationName + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", packNumbers=" + packNumbers +
                ", outStockTime=" + outStockTime +
                '}';
    }
}
