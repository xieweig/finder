package cn.sisyphe.coffee.bill.viewmodel.purchase;

import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单据查询条件
 * version: 1.0
 *
 * @author XiongJing
 */
public class ConditionQueryPurchaseBill extends ConditionQueryBill {

    /**
     * 供应商编码
     */
    private String supplierCode;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

}
