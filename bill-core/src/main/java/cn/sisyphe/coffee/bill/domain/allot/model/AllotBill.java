package cn.sisyphe.coffee.bill.domain.allot.model;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

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
     * 具体的单据类型
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    private BillTypeEnum specificBillType;


    @Override
    public String billCodePrefix() {
        return "ALLO";
    }

    @Override
    public BillTypeEnum getSpecificBillType() {
        return specificBillType;
    }

    @Override
    public void setSpecificBillType(BillTypeEnum specificBillType) {
        this.specificBillType = specificBillType;
    }

}
