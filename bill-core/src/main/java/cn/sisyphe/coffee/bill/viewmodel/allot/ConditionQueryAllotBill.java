package cn.sisyphe.coffee.bill.viewmodel.allot;

import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @date: 2018/1/12
 * @description: 多条件查询退库调拨单查询
 * @author：bifenglin
 */
public class ConditionQueryAllotBill extends ConditionQueryBill {


    /**
     * 调拨开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date allotStartTime;
    /**
     * 调拨结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date allotEndTime;


    /**
     * 调出库位
     */
    private String outStorageCode;

    /**
     * 调入库位
     */
    private String inStorageCode;
    /**
     * 是否为误差单查询
     */
    private Boolean isMistakeBillQuery=false;
    /**
     * 误差单号
     */
    private String mistakeBillCode;

    public Date getAllotStartTime() {
        return allotStartTime;
    }

    public void setAllotStartTime(Date allotStartTime) {
        this.allotStartTime = allotStartTime;
    }

    public Date getAllotEndTime() {
        return allotEndTime;
    }

    public void setAllotEndTime(Date allotEndTime) {
        this.allotEndTime = allotEndTime;
    }

    public String getOutStorageCode() {
        return outStorageCode;
    }

    public void setOutStorageCode(String outStorageCode) {
        this.outStorageCode = outStorageCode;
    }

    public String getInStorageCode() {
        return inStorageCode;
    }

    public void setInStorageCode(String inStorageCode) {
        this.inStorageCode = inStorageCode;
    }

    public Boolean getMistakeBillQuery() {
        return isMistakeBillQuery;
    }

    public void setMistakeBillQuery(Boolean mistakeBillQuery) {
        isMistakeBillQuery = mistakeBillQuery;
    }

    public String getMistakeBillCode() {
        return mistakeBillCode;
    }

    public void setMistakeBillCode(String mistakeBillCode) {
        this.mistakeBillCode = mistakeBillCode;
    }
}
