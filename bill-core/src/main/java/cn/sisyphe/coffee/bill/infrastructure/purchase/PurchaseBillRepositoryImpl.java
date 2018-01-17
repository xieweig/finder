package cn.sisyphe.coffee.bill.infrastructure.purchase;

import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.purchase.jpa.JpaPurchaseBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * Created by heyong on 2017/12/22 10:02
 * Description: 进货单数据库实现
 *
 * @author heyong
 */
@Repository
public class PurchaseBillRepositoryImpl extends AbstractBillRepository<PurchaseBill> implements PurchaseBillRepository {

    @Autowired
    public PurchaseBillRepositoryImpl(JpaBillRepository<PurchaseBill> jpaBillRepository) {
        super(jpaBillRepository);
    }

    /**
     * 根据货源单号查询进货单据信息
     *
     * @param freightCode 货运单号
     * @return
     */
    @Override
    public PurchaseBill findOneByFreightCode(String freightCode) {
        return ((JpaPurchaseBillRepository) getJpaBillRepository()).findOneByFreightCode(freightCode);
    }
}
