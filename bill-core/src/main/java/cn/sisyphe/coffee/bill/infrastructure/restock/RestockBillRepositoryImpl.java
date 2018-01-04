package cn.sisyphe.coffee.bill.infrastructure.restock;

import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.restock.jpa.JPARestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<RestockBill> ConditionSearch(ConditionQueryRestockBill conditions) {
        return jpaRestockBillRepository.findAll((root, query, cb) -> {
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
    public Page<RestockBill> cargoQuery(String cargoName, String cargoCode){

        return jpaRestockBillRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            List<Expression<Boolean>> expressions=predicate.getExpressions();
            if(StringUtils.isEmpty(cargoCode)){
                expressions.add(
                        cb.equal(root.get("").as(String.class), cargoCode)
                );
            }
            if(StringUtils.isEmpty(cargoName)){
                expressions.add(
                        cb.equal(root.get("").as(String.class), cargoName)
                );
            }

            return predicate;
        },new PageRequest(1,10));

    }
//    public Page<RestockBill> cargo(){
//        return jpaRestockBillRepository.findAll((root, query, cb)->{
//
//        }, new PageRequest(1,10));
//    }

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
