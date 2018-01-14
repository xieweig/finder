package cn.sisyphe.coffee.bill.domain.allot;

import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.infrastructure.allot.AllotBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author bifenglin
 */
@Service
public class AllotBillExtraServiceImpl extends AbstractBillExtraService<AllotBill> implements AllotBillExtraService {

    @Autowired
    public AllotBillExtraServiceImpl(BillRepository<AllotBill> billRepository) {
        super(billRepository);
    }
}
