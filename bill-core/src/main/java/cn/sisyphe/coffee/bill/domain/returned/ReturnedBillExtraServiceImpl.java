package cn.sisyphe.coffee.bill.domain.returned;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bifenglin
 */
@Service
public class ReturnedBillExtraServiceImpl extends AbstractBillExtraService<ReturnedBill> implements ReturnedBillExtraService {

    @Autowired
    public ReturnedBillExtraServiceImpl(BillRepository<ReturnedBill> billRepository) {
        super(billRepository);
    }
}
