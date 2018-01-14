package cn.sisyphe.coffee.bill.infrastructure.allot;

import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * 配送单
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/4 10:04
 **/
public interface AllotBillRepository extends BillRepository<AllotBill> {

}
