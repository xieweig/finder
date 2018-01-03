package cn.sisyphe.coffee.bill.infrastructure.restock;

import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.restock.jpa.JPARestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 *@date: 2018/1/2
 *@description:
 *@author：xieweiguang
 */
@Repository
public class RestockBillRepositoryImpl extends AbstractBillRepository<RestockBill> implements RestockBillRepository{

    @Resource
    private JPARestockBillRepository jpaRestockBillRepository;
    // 外界调用从此门面类调用
    public void ConditionSearch(ConditionQueryRestockBill conditions) {
        jpaRestockBillRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            //   if expressions.add()
            if (StringUtils.isEmpty(conditions.getBillCode())){
                expressions.add(cb.equal(root.get("billCode").as(String.class),conditions.getBillCode()));
            }
            if (StringUtils.isEmpty(conditions.getOperatorCode())) {
                expressions.add(
                        cb.equal(root.get("operatorCode").as(String.class), conditions.getOperatorCode())
                );
            }
            if (conditions.getCreateTime()!=null){
                expressions.add(
                        cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), conditions.getCreateTime())
                );
            }
            if (conditions.getUpdateTime()!=null){
                expressions.add(
                        cb.lessThanOrEqualTo(root.get("updateTime").as(Date.class),conditions.getUpdateTime())
                );
            }

            return predicate;
        }, new PageRequest(conditions.getPage(), conditions.getPageSize()));
    }

    /**
     * 按单号查询
     *
     * @param billCode
     * @return
     */
    @Override
    public RestockBill findOneByBillCode(String billCode) {
        return null;
    }
}
