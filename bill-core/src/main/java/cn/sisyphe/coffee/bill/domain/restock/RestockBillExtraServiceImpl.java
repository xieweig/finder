package cn.sisyphe.coffee.bill.domain.restock;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bifenglin
 */
@Service
public class RestockBillExtraServiceImpl extends AbstractBillExtraService<RestockBill> implements RestockBillExtraService {

    @Autowired
    public RestockBillExtraServiceImpl(BillRepository<RestockBill> billRepository) {
        super(billRepository);
    }
}
