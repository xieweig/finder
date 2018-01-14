package cn.sisyphe.coffee.bill.infrastructure.restock;

import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 *@date: 2018/1/2
 *@description:
 *@author：xieweiguang
 */
public interface RestockBillRepository extends BillRepository<RestockBill>{


}
