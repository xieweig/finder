package cn.sisyphe.coffee.bill.domain.inoutself;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.inoutself.model.InOutSelfBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.inoutself.ConditionQueryInOutSelfBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2018/1/19
 * @description: 其它出入库业务逻辑接口实现
 * @author：mayupeng
 */
@Service
public class InOutSelfBillExtraServiceImpl extends AbstractBillExtraService<InOutSelfBill, ConditionQueryInOutSelfBill> implements InOutSelfBillExtraService {
    @Autowired
    public InOutSelfBillExtraServiceImpl(BillRepository<InOutSelfBill> billRepository) {
        super(billRepository);
    }
}
