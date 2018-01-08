package cn.sisyphe.coffee.bill.application.adjust;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillQueryService;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂业务
 * version: 1.0
 *
 * @author XiongJing
 */
@Service
public class AdjustBillManager extends AbstractBillManager<PurchaseBill> {
    @Autowired
    private AdjustBillQueryService adjustBillQueryService;
    @Autowired
    private SharedManager sharedManager;

    public AdjustBillManager(BillRepository<PurchaseBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }


}
