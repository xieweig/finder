package cn.sisyphe.coffee.bill.domain.purchase;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.purchase.ConditionQueryPurchaseBill;
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
public class PurchaseBillExtraServiceImpl extends AbstractBillExtraService<PurchaseBill, ConditionQueryPurchaseBill> implements PurchaseBillExtraService {

    @Autowired
    public PurchaseBillExtraServiceImpl(BillRepository<PurchaseBill> billRepository) {
        super(billRepository);
    }

    /**
     * 根据货运单号查询进货单号信息
     *
     * @param freightCode 货运单号
     * @return
     */
    @Override
    public PurchaseBill findByFreightCode(String freightCode) {
        return ((PurchaseBillExtraService) getBillRepository()).findByFreightCode(freightCode);
    }
}
