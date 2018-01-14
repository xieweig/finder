package cn.sisyphe.coffee.bill.infrastructure.allot;

import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 调拨单
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/4 10:04
 **/
@Service
public class AllotBillRepositoryImpl extends AbstractBillRepository<AllotBill> implements AllotBillRepository {


    @Autowired
    public AllotBillRepositoryImpl(JpaBillRepository<AllotBill> jpaBillRepository) {
        super(jpaBillRepository);
    }
}
