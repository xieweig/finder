package cn.sisyphe.coffee.bill.viewmodel.planbill;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;

import java.io.Serializable;
import java.util.List;

/**
 * @author bifenglin
 */
public class ConditionQueryPlanBill extends ConditionQueryBill implements Serializable {

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
    private Boolean hqBill;

    /**
     * 货物名称
     */
    private String cargoName;

    /**
     * 货物编号集合
     */
    private List<String> cargoCodes;

    /**
     * 货物编号
     */
    private String cargoCode;


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

    public Boolean getHqBill() {
        return hqBill;
    }

    public void setHqBill(Boolean hqBill) {
        this.hqBill = hqBill;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }

    public List<String> getCargoCodes() {
        return cargoCodes;
    }

    public void setCargoCodes(List<String> cargoCodes) {
        this.cargoCodes = cargoCodes;
    }
}
