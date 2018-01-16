package cn.sisyphe.coffee.bill.domain.allot;

import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bifenglin
 */
@Service
public class AllotBillExtraServiceImpl extends AbstractBillExtraService<AllotBill, ConditionQueryAllotBill> implements AllotBillExtraService {

    @Autowired
    public AllotBillExtraServiceImpl(BillRepository<AllotBill> billRepository) {
        super(billRepository);
    }
}
