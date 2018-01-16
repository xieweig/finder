package cn.sisyphe.coffee.bill.viewmodel.allot;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;

import java.io.Serializable;

/**
 * @date: 2018/1/12
 * @description: 多条件查询退库调拨单查询
 * @author：bifenglin
 */
public class ConditionQueryAllotBill extends ConditionQueryBill implements Serializable {


    private String operatorCode;

    /**
     * 入库库位
     */
    private Storage inStorage;
    /**
     * 出库库位
     */
    private Storage outStorage;

    private BillTypeEnum specificBillType;

    public void setSpecificBillType(BillTypeEnum specificBillType) {
        this.specificBillType = specificBillType;
    }


    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public Storage getInStorage() {
        return inStorage;
    }

    public void setInStorage(Storage inStorage) {
        this.inStorage = inStorage;
    }

    public Storage getOutStorage() {
        return outStorage;
    }

    public void setOutStorage(Storage outStorage) {
        this.outStorage = outStorage;
    }
}
