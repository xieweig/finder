package cn.sisyphe.coffee.bill.domain.transmit;


import cn.sisyphe.coffee.bill.domain.base.model.BaseEntity;
import cn.sisyphe.coffee.bill.domain.transmit.enums.PackAgeTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 运货单明细
 */
@Entity
@Table
public class WayBillDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billDetailId;

//    //@ManyToOne(fetch=FetchType.LAZY,optional=true)
//    @ManyToOne(fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(referencedColumnName = "bill_code")//外键名称
//    private WayBill wayBill;

    /**
     * 出库单号(来源单号)
     */
    @Column(name = "source_code", length = 255, nullable = false)
    private String sourceCode;


    /**
     * 包号
     */
    @Column(name = "package_code", length = 255)
    private String packageCode;

    /**
     * 指来源单号录单人(和需求方已经确定)
     */
    @Column(length = 255)
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


    /**
     * 入库站点名称
     */
    @Column(length = 255)
    private String inStationName;

    /**
     * 出库站点
     */
    @Column(length = 255)
    private String outStationCode;


    /**
     * 出库站点名称
     */
    @Column(length = 255)
    private String outStationName;


    /**
     * 打包方式
     */
    @Column(length = 255)
    @Enumerated(value = EnumType.STRING)
    private PackAgeTypeEnum packAgeTypeEnum;


    /**
     * 出库时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column
    private Date outStorageTime;


    /**
     * 是否单独打包
     */
    @Column(name = "single_packing")
    public Boolean singlePacking;


    public String getInStationCode() {
        return inStationCode;
    }


    public Boolean getSinglePacking() {
        return singlePacking;
    }

    public void setSinglePacking(Boolean singlePacking) {
        this.singlePacking = singlePacking;
    }

    public Long getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Long billDetailId) {
        this.billDetailId = billDetailId;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
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

    @Override
    public String toString() {
        return "WayBillDetail{" +
                "billDetailId=" + billDetailId +
//                ", wayBill=" + wayBill +
                ", sourceCode='" + sourceCode + '\'' +
                ", packageCode='" + packageCode + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", totalCount=" + totalCount +
                ", totalAmount=" + totalAmount +
                ", inStationCode='" + inStationCode + '\'' +
                ", inStationName='" + inStationName + '\'' +
                ", outStationCode='" + outStationCode + '\'' +
                ", outStationName='" + outStationName + '\'' +
                ", packAgeTypeEnum=" + packAgeTypeEnum +
                ", outStorageTime=" + outStorageTime +
                '}';
    }
}
