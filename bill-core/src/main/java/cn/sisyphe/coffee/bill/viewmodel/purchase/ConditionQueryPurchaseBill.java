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
    private List<String> supplierCodes;

    /**
     * 入库库位编码集合
     */
    private List<String> inStorageCodes;

    public List<String> getSupplierCodes() {
        return supplierCodes;
    }

    public void setSupplierCodes(List<String> supplierCodes) {
        this.supplierCodes = supplierCodes;
    }

    public List<String> getInStorageCodes() {
        return inStorageCodes;
    }

    public void setInStorageCodes(List<String> inStorageCodes) {
        this.inStorageCodes = inStorageCodes;
    }
}
