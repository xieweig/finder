package cn.sisyphe.coffee.bill.domain.adjust;

import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂业务逻辑接口实现
 * version: 1.0
 *
 * @author XiongJing
 */

@Service
public class AdjustBillExtraServiceImpl extends AbstractBillExtraService<AdjustBill, ConditionQueryAdjustBill> implements AdjustBillExtraService {

    @Autowired
    public AdjustBillExtraServiceImpl(BillRepository<AdjustBill> billRepository) {
        super(billRepository);
    }

}
