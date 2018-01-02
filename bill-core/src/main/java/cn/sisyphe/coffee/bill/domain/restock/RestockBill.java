package cn.sisyphe.coffee.bill.domain.restock;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ncmao
 * @Date 2017/12/27 16:10
 * @description 退库计划单
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class RestockBill extends Bill<RestockBillDetail> {
    /**
     *
     *notes :
     *  退库 出库单 起始终止时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(nullable = false, updatable = false)
    private Date outStorageStartTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(nullable = false, updatable = false)
    private Date outStorageEndTime;
    /**
     *
     *notes :
     *  退库 入库单 起始终止时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(nullable = false, updatable = false)
    private Date inStorageStartTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(nullable = false, updatable = false)
    private Date inStorageEndTime;

    @Column(length = 300)
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getOutStorageStartTime() {
        return outStorageStartTime;
    }

    public void setOutStorageStartTime(Date outStorageStartTime) {
        this.outStorageStartTime = outStorageStartTime;
    }

    public Date getOutStorageEndTime() {
        return outStorageEndTime;
    }

    public void setOutStorageEndTime(Date outStorageEndTime) {
        this.outStorageEndTime = outStorageEndTime;
    }

    public Date getInStorageStartTime() {
        return inStorageStartTime;
    }

    public void setInStorageStartTime(Date inStorageStartTime) {
        this.inStorageStartTime = inStorageStartTime;
    }

    public Date getInStorageEndTime() {
        return inStorageEndTime;
    }

    public void setInStorageEndTime(Date inStorageEndTime) {
        this.inStorageEndTime = inStorageEndTime;
    }


}
