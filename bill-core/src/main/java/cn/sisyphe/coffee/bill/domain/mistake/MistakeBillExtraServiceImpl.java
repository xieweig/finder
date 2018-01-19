package cn.sisyphe.coffee.bill.domain.mistake;
import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.mistake.ConditionQueryMistakeBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author Amy on 2018/1/18.
 * describe：
 */
@Service
public class MistakeBillExtraServiceImpl extends AbstractBillExtraService<MistakeBill, ConditionQueryMistakeBill> implements MistakeBillExtraService {
    @Autowired
    public MistakeBillExtraServiceImpl(BillRepository<MistakeBill> billRepository) {
        super(billRepository);
    }
}
