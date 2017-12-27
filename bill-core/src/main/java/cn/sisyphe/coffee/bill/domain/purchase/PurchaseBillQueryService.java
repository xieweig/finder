package cn.sisyphe.coffee.bill.domain.purchase;


import cn.sisyphe.coffee.bill.viewmodel.ConditionQueryPurchaseBill;
import org.springframework.data.domain.Page;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单查询服务接口
 * version: 1.0
 *
 * @author XiongJing
 */
public interface PurchaseBillQueryService {

    Page<PurchaseBill> findByConditions (ConditionQueryPurchaseBill conditionQueryPurchaseBill);

    PurchaseBill findByBillCode(String billCode);

}
