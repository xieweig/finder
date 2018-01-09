package cn.sisyphe.coffee.bill.viewmodel.deliverybill;

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


    private String billCode;
    //总数量
    private Integer totalAmount;
    //总品种
    private Integer totalCount;
    private String operatorCode;
    //包号
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

    @Override
    public String toString() {
        return "ScanFillBillDTO{" +
                "billCode='" + billCode + '\'' +
                ", totalAmount=" + totalAmount +
                ", totalCount=" + totalCount +
                ", packNumbers=" + packNumbers +
                '}';
    }
}
