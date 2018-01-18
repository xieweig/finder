package cn.sisyphe.coffee.bill.infrastructure.purchase.jpa;

import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;

/**
 * Created by heyong on 2017/12/22 10:02
 * Description: 进货单JPA
 *
 * @author heyong
 */
public interface JpaPurchaseBillRepository extends JpaBillRepository<PurchaseBill> {

    /**
     * 根据货运单号查询进货单据信息
     *
     * @param freightCode 货运单号
     * @return
     */
    PurchaseBill findOneByFreightCode(String freightCode);

}
