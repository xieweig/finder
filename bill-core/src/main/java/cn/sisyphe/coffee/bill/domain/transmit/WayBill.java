package cn.sisyphe.coffee.bill.domain.transmit;


import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 运货单
 */
@Entity
@Table
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class WayBill extends Bill<WayBillDetail> {


    @Transient
    Set<WayBillDetail> wayBillDetailSet;

    public WayBill() {
        this.setBillType(BillTypeEnum.TRANSMIT);//
    }

    /**
     * 发货时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(nullable = false)
    private Date deliveryTime;

    /**
     * 预计到货时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date planArrivalTime;

    /**
     * 物流公司名称
     */
    @Column(nullable = false)
    private String logisticsCompanyName;

    /**
     * 目的地
     */
    @Column(nullable = false)
    private String destination;


    /**
     * 运货件数
     */
    @Column(nullable = false)
    private Integer amountOfPackages;


    /**
     * 总重量
     */
    @Column
    private Long totalWeight;

    /**
     * 备注
     */
    @Column
    private String memo;


    public Set<WayBillDetail> getWayBillDetailSet() {
        return wayBillDetailSet;
    }

    public void setWayBillDetailSet(Set<WayBillDetail> wayBillDetailSet) {
        this.wayBillDetailSet = wayBillDetailSet;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getPlanArrivalTime() {
        return planArrivalTime;
    }

    public void setPlanArrivalTime(Date planArrivalTime) {
        this.planArrivalTime = planArrivalTime;
    }

    public String getLogisticsCompanyName() {
        return logisticsCompanyName;
    }

    public void setLogisticsCompanyName(String logisticsCompanyName) {
        this.logisticsCompanyName = logisticsCompanyName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getAmountOfPackages() {
        return amountOfPackages;
    }

    public void setAmountOfPackages(Integer amountOfPackages) {
        this.amountOfPackages = amountOfPackages;
    }

    public Long getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Long totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
