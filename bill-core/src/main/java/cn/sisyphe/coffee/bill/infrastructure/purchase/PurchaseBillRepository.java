package cn.sisyphe.coffee.bill.infrastructure.purchase;

import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by heyong on 2017/12/22 10:01
 * Description: 进货单数据服务
 * @author heyong
 */
public interface PurchaseBillRepository extends BillRepository<PurchaseBill> {

}
