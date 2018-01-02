package cn.sisyphe.coffee.bill.infrastructure.returned;

import cn.sisyphe.coffee.bill.domain.returned.ReturnedBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.returned.jpa.JPAReturnedBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ncmao
 * @Date 2017/12/27 16:35
 * @description
 */
/**
 *@date: 2018/1/2
 *@description:
 *@author：xieweiguang
 */
@Service
public class ReturnedBillRepositoryImpl extends AbstractBillRepository<ReturnedBill> implements ReturnedBillRepository {

    @Autowired
    private JPAReturnedBillRepository jpaReturnedBillRepository;

}
