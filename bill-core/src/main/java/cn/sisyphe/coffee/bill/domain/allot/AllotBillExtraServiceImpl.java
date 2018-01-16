package cn.sisyphe.coffee.bill.domain.allot;

import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

//    /**
//     * 条件组装
//     *
//     * @param conditionQuery
//     * @param pageable
//     * @return
//     */
//    @Override
//    protected <Q extends ConditionQueryBill> Page<AllotBill> pageCondition(Q conditionQuery, Pageable pageable) {
//        return super.pageCondition(conditionQuery, pageable);
//    }
}
