package cn.sisyphe.coffee.bill.infrastructure.mistake;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Amy on 2018/1/18.
 * describe：
 */
@Repository
public class MistakeBillRepositoryImpl extends AbstractBillRepository<MistakeBill> implements MistakeBillRepository {
    @Autowired
    public MistakeBillRepositoryImpl(JpaBillRepository<MistakeBill> jpaBillRepository) {
        super(jpaBillRepository);
    }
}
