package cn.sisyphe.coffee.bill.domain.transmit;


import cn.sisyphe.coffee.bill.domain.base.model.BaseEntity;
import cn.sisyphe.coffee.bill.domain.transmit.enums.ReceivedStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 运货单
 */
@Entity
@Table
public class WayBill extends BaseEntity {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "wayBill")
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Set<WayBillDetail> wayBillDetailSet = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;

    /**
     * 单据code
     * tip: 必须加name="bill_code" ,否则子类找不到外键
     */
    @Column(name = "bill_code", length = 255, nullable = false, unique = true)
    private String billCode;


    /**
     * 出库站点
     */
    @Column(length = 255, nullable = false)
    private String outStationCode;

    /**
     * 入库站点
     */
    @Column(length = 255)
    private String inStationCode;

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
    @Column(length = 255, nullable = false)
    private String logisticsCompanyName;

    /**
     * 目的地
     */
    @Column(length = 255)
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
     * 操作人code
     */
    @Column(length = 255)
    private String operatorCode;

    /**
     * 操作人姓名
     */
    @Column(length = 255)
    private String operatorName;

    /**
     * 收货状态
     */
    @Column(length = 255)
    @Enumerated(value = EnumType.STRING)
    private ReceivedStatusEnum receivedStatus;

    /**
     * 备注
     */
    @Column(length = 255)
    private String memo;


    /**
     * 总运货件数
     *
     * @return
     */
//    private int calcTotalPackageAmount() {
//        //包号
//        List<String> packAgesList = new ArrayList<>();
//        if (wayBillDetailSet == null || wayBillDetailSet.isEmpty()) {
//            return 0;
//        }
//        for (WayBillDetail wayBillDetail : wayBillDetailSet) {
//            //包号不为空
//            if (!StringUtils.isEmpty(wayBillDetail.getPackageCode())) {
//                //
//                if (!packAgesList.contains(wayBillDetail.getPackageCode())) {
//                    packAgesList.add(wayBillDetail.getPackageCode());//
//                }
//            }
//        }
//        return packAgesList.size();
//    }
    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public ReceivedStatusEnum getReceivedStatus() {
        return receivedStatus;
    }

    public void setReceivedStatus(ReceivedStatusEnum receivedStatus) {
        this.receivedStatus = receivedStatus;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }


    public String getInStationCode() {
        return inStationCode;
    }

    public void setInStationCode(String inStationCode) {
        this.inStationCode = inStationCode;
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

    /**
     * 计算运货件数
     *
     * @return
     */
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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }


    public Set<WayBillDetail> getWayBillDetailSet() {
        return wayBillDetailSet;
    }

    public void setWayBillDetailSet(Set<WayBillDetail> wayBillDetailSet) {
        this.wayBillDetailSet = wayBillDetailSet;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getOutStationCode() {
        return outStationCode;
    }

    public void setOutStationCode(String outStationCode) {
        this.outStationCode = outStationCode;
    }


    @Override
    public String toString() {
        return "WayBill{" +
                ", billId=" + billId +
                ", billCode='" + billCode + '\'' +
                ", outStationCode='" + outStationCode + '\'' +
                ", inStationCode='" + inStationCode + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", planArrivalTime=" + planArrivalTime +
                ", logisticsCompanyName='" + logisticsCompanyName + '\'' +
                ", destination='" + destination + '\'' +
                ", amountOfPackages=" + amountOfPackages +
                ", totalWeight=" + totalWeight +
                ", operatorCode='" + operatorCode + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", receivedStatus=" + receivedStatus +
                ", memo='" + memo + '\'' +
                '}';
    }
}
