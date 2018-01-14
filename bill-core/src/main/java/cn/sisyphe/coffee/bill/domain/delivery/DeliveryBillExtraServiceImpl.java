package cn.sisyphe.coffee.bill.domain.delivery;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 查询配送单
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/4 11:08
 **/
@Service
public class DeliveryBillExtraServiceImpl extends AbstractBillExtraService<DeliveryBill> implements DeliveryBilExtraService {

    @Autowired
    public DeliveryBillExtraServiceImpl(BillRepository<DeliveryBill> billRepository) {
        super(billRepository);
    }
}
