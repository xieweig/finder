package cn.sisyphe.coffee.bill.viewmodel.purchase;

import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;

import java.util.List;

/**
 * @date: 2018/1/12
 * @description: 多条件查询退库调拨单查询
 * @author：bifenglin
 */
public class ConditionQueryPurchaseBill extends ConditionQueryBill  {

    /**
     * 供应商编码集合
     */
    private List<String> supplierCodeList;

    /**
     * 入库库位编码集合
     */
    private List<String> inStorageCodeList;

    public List<String> getSupplierCodeList() {
        return supplierCodeList;
    }

    public void setSupplierCodeList(List<String> supplierCodeList) {
        this.supplierCodeList = supplierCodeList;
    }

    public List<String> getInStorageCodeList() {
        return inStorageCodeList;
    }

    public void setInStorageCodeList(List<String> inStorageCodeList) {
        this.inStorageCodeList = inStorageCodeList;
    }
}
