package cn.sisyphe.coffee.bill.infrastructure.purchase;

import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;

/**
 * Created by heyong on 2017/12/22 10:01
 * Description: 进货单数据服务
 * @author heyong
 */
public interface PurchaseBillRepository extends BillRepository<PurchaseBill> {

    PurchaseBill findOneByBillCode(String billCode);
}
