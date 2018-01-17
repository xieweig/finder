package cn.sisyphe.coffee.bill.domain.purchase;


import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.viewmodel.purchase.ConditionQueryPurchaseBill;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单查询服务接口
 * version: 1.0
 *
 * @author XiongJing
 */
public interface PurchaseBillExtraService extends BillExtraService<PurchaseBill, ConditionQueryPurchaseBill> {

    /**
     * 根据货运单号查询进货单据信息
     *
     * @param freightCode 货运单号
     * @return
     */
    PurchaseBill findByFreightCode(String freightCode);

}
