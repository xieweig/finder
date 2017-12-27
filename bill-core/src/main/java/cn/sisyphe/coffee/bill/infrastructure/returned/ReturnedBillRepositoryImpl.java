package cn.sisyphe.coffee.bill.infrastructure.returned;

import cn.sisyphe.coffee.bill.infrastructure.returned.jpa.JPAReturnedBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ncmao
 * @Date 2017/12/27 16:35
 * @description
 */
@Service
public class ReturnedBillRepositoryImpl implements ReturnedBillRepository {

    @Autowired
    private JPAReturnedBillRepository jpaReturnedBillRepository;


}
