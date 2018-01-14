package cn.sisyphe.coffee.bill.domain.purchase;


import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.viewmodel.purchase.ConditionQueryPurchaseBill;
import org.springframework.data.domain.Page;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单查询服务接口
 * version: 1.0
 *
 * @author XiongJing
 */
public interface PurchaseBillExtraService extends BillExtraService<PurchaseBill> {

    /**
     * 多条件查询进货单数据
     *
     * @param conditionQueryPurchaseBill 查询条件
     * @return
     */
    Page<PurchaseBill> findByConditions(ConditionQueryPurchaseBill conditionQueryPurchaseBill);

}
