package cn.sisyphe.coffee.bill.domain.delivery;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.infrastructure.delivery.DeliveryBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.ConditionQueryDeliveryBill;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;


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
