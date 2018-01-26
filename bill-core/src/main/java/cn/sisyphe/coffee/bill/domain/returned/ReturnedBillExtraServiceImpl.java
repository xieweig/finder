package cn.sisyphe.coffee.bill.domain.returned;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.domain.returned.model.ReturnedBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.purchase.ConditionQueryPurchaseBill;
import cn.sisyphe.coffee.bill.viewmodel.returned.ConditionQueryReturnedBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author bifenglin
 */
@Service
public class ReturnedBillExtraServiceImpl extends AbstractBillExtraService<ReturnedBill, ConditionQueryReturnedBill> implements ReturnedBillExtraService {

    @Autowired
    public ReturnedBillExtraServiceImpl(BillRepository<ReturnedBill> billRepository) {
        super(billRepository);
    }

    @Override
    protected void addExtraExpression(ConditionQueryReturnedBill conditionQuery, List<Expression<Boolean>> expressions, Root<ReturnedBill> root, CriteriaBuilder criteriaBuilder) {
        /**
         * 拼接供应商编码
         */
        if (conditionQuery.getSupplierCodeList() != null && conditionQuery.getSupplierCodeList().size() > 0) {
            expressions.add(root.get("dbStation").get("supplierCode").as(String.class).in(conditionQuery.getSupplierCodeList()));
        }
    }
}
