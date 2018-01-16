package cn.sisyphe.coffee.bill.viewmodel.planbill;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;

import java.io.Serializable;
import java.util.Set;

/**
 * @author bifenglin
 */
public class ConditionQueryPlanBill extends ConditionQueryBill implements Serializable {

    /**
     * 单据作用
     */
    private BillPurposeEnum billPurpose;
    /**
     * 单据种类
     */
    private BillTypeEnum specificBillType;

    /**
     * 单据名称
     */
    private String billName;

    /**
     * 是否为总部计划单据
     */
    private String hqBill;

    /**
     * 货物编号集合
     */
    private Set<String> cargoCodeArray;

    /**
     * 货物编号
     */
    private String cargoCode;


    public BillPurposeEnum getBillPurpose() {
        return billPurpose;
    }

    public void setBillPurpose(BillPurposeEnum billPurpose) {
        this.billPurpose = billPurpose;
    }

    public BillTypeEnum getSpecificBillType() {
        return specificBillType;
    }

    public void setSpecificBillType(BillTypeEnum specificBillType) {
        this.specificBillType = specificBillType;
    }


    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getHqBill() {
        return hqBill;
    }

    public void setHqBill(String hqBill) {
        this.hqBill = hqBill;
    }

    public Set<String> getCargoCodeArray() {
        return cargoCodeArray;
    }

    public void setCargoCodeArray(Set<String> cargoCodeArray) {
        this.cargoCodeArray = cargoCodeArray;
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }

}
