package cn.sisyphe.coffee.bill.domain.allot.model;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Date 2018/1/12 10:56
 * @description
 */

@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class AllotBill extends Bill<AllotBillDetail> {


    public AllotBill() {
        setBillType(BillTypeEnum.ALLOT);
    }

    /**
     * 调拨备注
     */
    private String allotMemo;


    /**
     * 入库单入库时间
     */
    private Date inStorageBillInStorageDate;

    /**
     * 入库单入库站点
     */
    private String inStorageBillInStationCode;

    /**
     * 入库单出库站点
     */
    private String inStorageBillOutStationCode;

    /**
     * 入库单编号
     */
    private String inStorageBillCode;

    private BillTypeEnum inStorageBillType;

    /**
     * 差错单单号
     */
    private String mistakeBillCode;


    @Override
    public String billCodePrefix() {
        return "ALLO";
    }

    public String getAllotMemo() {
        return allotMemo;
    }

    public void setAllotMemo(String allotMemo) {
        this.allotMemo = allotMemo;
    }

    public Date getInStorageBillInStorageDate() {
        return inStorageBillInStorageDate;
    }

    public void setInStorageBillInStorageDate(Date inStorageBillInStorageDate) {
        this.inStorageBillInStorageDate = inStorageBillInStorageDate;
    }

    public String getInStorageBillInStationCode() {
        return inStorageBillInStationCode;
    }

    public void setInStorageBillInStationCode(String inStorageBillInStationCode) {
        this.inStorageBillInStationCode = inStorageBillInStationCode;
    }

    public String getInStorageBillOutStationCode() {
        return inStorageBillOutStationCode;
    }

    public void setInStorageBillOutStationCode(String inStorageBillOutStationCode) {
        this.inStorageBillOutStationCode = inStorageBillOutStationCode;
    }

    public String getMistakeBillCode() {
        return mistakeBillCode;
    }

    public void setMistakeBillCode(String mistakeBillCode) {
        this.mistakeBillCode = mistakeBillCode;
    }

    public String getInStorageBillCode() {
        return inStorageBillCode;
    }

    public void setInStorageBillCode(String inStorageBillCode) {
        this.inStorageBillCode = inStorageBillCode;
    }

    public BillTypeEnum getInStorageBillType() {
        return inStorageBillType;
    }

    public void setInStorageBillType(BillTypeEnum inStorageBillType) {
        this.inStorageBillType = inStorageBillType;
    }
}
