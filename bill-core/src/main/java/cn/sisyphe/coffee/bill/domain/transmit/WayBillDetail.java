package cn.sisyphe.coffee.bill.domain.transmit;


import cn.sisyphe.coffee.bill.domain.base.model.BaseEntity;
import cn.sisyphe.coffee.bill.domain.transmit.enums.PackAgeTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * 运货单明细
 */
@Entity
@Table
public class WayBillDetail extends BaseEntity {


    //
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billDetailId;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = true)
    @JsonIgnore
    @JoinColumn(name = "parent_id")//外键名称
    private WayBill wayBill;

    /**
     * 出库单号
     */
    @Column(length = 255)
    private String outStorageBillCode;//

    @Column(length = 255)
    private String cargoCode;


    @Column(length = 255)
    private String rawMaterialCode;


    private String packageCode;

    /**
     * 录单人
     */
    @Column
    private String operatorName;

    /**
     * 品种数
     */
    @Column
    private Integer totalCount;

    /**
     * 货物数
     */
    @Column
    private Integer totalAmount;


    /**
     * 入库站点
     */
    @Column(length = 255)
    private String inStationCode;


    @Column(length = 255)
    private String inStationName;

    /**
     * 出库站点
     */
    @Column(length = 255)
    private String outStationCode;


    @Column(length = 255)
    private String outStationName;


    public Long getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Long billDetailId) {
        this.billDetailId = billDetailId;
    }

    /**
     * 打包方式
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private PackAgeTypeEnum packAgeTypeEnum;


    /**
     * 出库时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(nullable = false)
    private Date outStorageTime;


    public String getInStationCode() {

        return inStationCode;
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

    public WayBill getWayBill() {
        return wayBill;
    }

    public void setWayBill(WayBill wayBill) {
        this.wayBill = wayBill;
    }


    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }

    public String getRawMaterialCode() {
        return rawMaterialCode;
    }

    public void setRawMaterialCode(String rawMaterialCode) {
        this.rawMaterialCode = rawMaterialCode;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public void setInStationCode(String inStationCode) {
        this.inStationCode = inStationCode;
    }

    public String getOutStationCode() {
        return outStationCode;
    }

    public void setOutStationCode(String outStationCode) {
        this.outStationCode = outStationCode;
    }

    public String getOutStorageBillCode() {
        return outStorageBillCode;
    }

    public void setOutStorageBillCode(String outStorageBillCode) {
        this.outStorageBillCode = outStorageBillCode;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public PackAgeTypeEnum getPackAgeTypeEnum() {
        return packAgeTypeEnum;
    }

    public void setPackAgeTypeEnum(PackAgeTypeEnum packAgeTypeEnum) {
        this.packAgeTypeEnum = packAgeTypeEnum;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOutStorageTime() {
        return outStorageTime;
    }

    public void setOutStorageTime(Date outStorageTime) {
        this.outStorageTime = outStorageTime;
    }
}
