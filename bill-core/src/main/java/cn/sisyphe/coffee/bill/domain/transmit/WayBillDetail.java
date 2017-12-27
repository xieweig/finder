package cn.sisyphe.coffee.bill.domain.transmit;


import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.transmit.enums.PackAgeTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * 运货单明细
 */
@Entity
@Table
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class WayBillDetail extends BillDetail {


    /**
     * 出库单号
     */
    @Column
    private String outStorageBillCode;//

    /**
     * 录单人
     */
    @Column
    private String operatorName;

    /**
     * 打包方式
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private PackAgeTypeEnum packAgeTypeEnum = PackAgeTypeEnum.DEFAULT;

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
     * 出库时间
     */
    @Column
    private String outStorageTime;


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

    public String getOutStorageTime() {
        return outStorageTime;
    }

    public void setOutStorageTime(String outStorageTime) {
        this.outStorageTime = outStorageTime;
    }
}
