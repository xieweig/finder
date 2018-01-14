package cn.sisyphe.coffee.bill.domain.purchase;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单查询服务接口实现
 * version: 1.0
 *
 * @author XiongJing
 */
@Service
public class PurchaseBillExtraServiceImpl extends AbstractBillExtraService<PurchaseBill> implements PurchaseBillExtraService {

    @Autowired
    public PurchaseBillExtraServiceImpl(BillRepository<PurchaseBill> billRepository) {
        super(billRepository);
    }
}
