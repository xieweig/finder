package cn.sisyphe.coffee.bill.infrastructure.inoutself;

import cn.sisyphe.coffee.bill.domain.inoutself.model.InOutSelfBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @date: 2018/1/19
 * @description: 其它出入库
 * @author：mayupeng
 */
@Repository
public class InOutSelfBillRepositoryImpl extends AbstractBillRepository<InOutSelfBill> implements InOutSelfBillRepository {
    @Autowired
    public InOutSelfBillRepositoryImpl(JpaBillRepository<InOutSelfBill> jpaBillRepository) {
        super(jpaBillRepository);
    }

}
