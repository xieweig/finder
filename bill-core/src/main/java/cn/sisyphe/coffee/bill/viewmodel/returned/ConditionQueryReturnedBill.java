package cn.sisyphe.coffee.bill.viewmodel.returned;

import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;

import java.util.List;

/**
 * @date: 2018/1/12
 * @description: 多条件查询退库调拨单查询
 * @author：bifenglin
 */
public class ConditionQueryReturnedBill extends ConditionQueryBill {

    /**
     * 供应商
     */
    private List<String> supplierCodeList;

    public List<String> getSupplierCodeList() {
        return supplierCodeList;
    }

    public void setSupplierCodeList(List<String> supplierCodeList) {
        this.supplierCodeList = supplierCodeList;
    }
}
