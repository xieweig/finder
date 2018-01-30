package cn.sisyphe.coffee.bill.domain.allot;

import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * @author bifenglin
 */
@Service
public class AllotBillExtraServiceImpl extends AbstractBillExtraService<AllotBill, ConditionQueryAllotBill> implements AllotBillExtraService {

    @Autowired
    public AllotBillExtraServiceImpl(BillRepository<AllotBill> billRepository) {
        super(billRepository);
    }


    @Override
    protected void addExtraExpression(ConditionQueryAllotBill conditionQuery, List<Expression<Boolean>> expressions, Root<AllotBill> root, CriteriaBuilder criteriaBuilder) {
        if (!StringUtils.isEmpty(conditionQuery.getInStorageCode())) {
            expressions.add(criteriaBuilder.equal(root.get("dbStation").get("inStorageCode").as(String.class), conditionQuery.getInStorageCode()));
        }

        if (!StringUtils.isEmpty(conditionQuery.getOutStorageCode())) {
            expressions.add(criteriaBuilder.equal(root.get("dbStation").get("outStorageCode").as(String.class), conditionQuery.getOutStorageCode()));
        }

        /*
         * 调拨开始时间
         */
        if (!StringUtils.isEmpty(conditionQuery.getAllotStartTime())) {
            expressions.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), conditionQuery.getAllotStartTime()));
        }

        /*
         * 调拨结束时间
         */
        if (!StringUtils.isEmpty(conditionQuery.getAllotEndTime())) {
            expressions.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), conditionQuery.getAllotEndTime()));
        }

        //追加误差单查询条件
        if (conditionQuery.getMistakeBillQuery()) {
            expressions.add(criteriaBuilder.isNotNull(root.get("mistakeBillCode").as(String.class)));
            //误差单号
            if (!StringUtils.isEmpty(conditionQuery.getMistakeBillCode())) {
                expressions.add(criteriaBuilder.like(root.get("mistakeBillCode").as(String.class), "%" + conditionQuery.getMistakeBillCode() + "%"));
            }
        }
    }
}
