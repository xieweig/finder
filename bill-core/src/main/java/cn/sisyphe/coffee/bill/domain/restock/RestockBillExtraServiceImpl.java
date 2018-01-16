package cn.sisyphe.coffee.bill.domain.restock;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bifenglin
 */
@Service
public class RestockBillExtraServiceImpl extends AbstractBillExtraService<RestockBill, ConditionQueryRestockBill> implements RestockBillExtraService {

    @Autowired
    public RestockBillExtraServiceImpl(BillRepository<RestockBill> billRepository) {
        super(billRepository);
    }
}
