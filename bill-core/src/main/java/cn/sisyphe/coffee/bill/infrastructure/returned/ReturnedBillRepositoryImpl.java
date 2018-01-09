package cn.sisyphe.coffee.bill.infrastructure.returned;

import cn.sisyphe.coffee.bill.domain.returned.ReturnedBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.returned.jpa.JPAReturnedBillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    private JPAReturnedBillRepository jpaReturnedBillRepository;


    /**
     * 多条件查询
     * @param ta
     * @param pageable
     * @return
     */
    @Override
    public Page<ReturnedBill> findAll(Specification<ReturnedBill> ta, Pageable pageable) {
        return jpaReturnedBillRepository.findAll(ta, pageable);
    }

    @Override
    public ReturnedBill findOneByBillCode(String billCode) {

        return jpaReturnedBillRepository.findOneByBillCode(billCode);
    }
    @Override
    public ReturnedBill findOneBySourceCode(String sourceCode) {
        return jpaReturnedBillRepository.findOneBySourceCode(sourceCode);
    }
}
