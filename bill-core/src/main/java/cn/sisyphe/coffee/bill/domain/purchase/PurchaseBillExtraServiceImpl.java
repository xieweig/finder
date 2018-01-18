package cn.sisyphe.coffee.bill.domain.purchase;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillExtraService;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.infrastructure.purchase.PurchaseBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.purchase.ConditionQueryPurchaseBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单查询服务接口实现
 * version: 1.0
 *
 * @author XiongJing
 */
@Service
public class PurchaseBillExtraServiceImpl extends AbstractBillExtraService<PurchaseBill, ConditionQueryPurchaseBill> implements PurchaseBillExtraService {

    @Autowired
    public PurchaseBillExtraServiceImpl(BillRepository<PurchaseBill> billRepository) {
        super(billRepository);
    }

    /**
     * 根据货运单号查询进货单号信息
     *
     * @param freightCode 货运单号
     * @return
     */
    @Override
    public PurchaseBill findByFreightCode(String freightCode) {
        return ((PurchaseBillRepository) getBillRepository()).findOneByFreightCode(freightCode);
    }

    /**
     * 增加通用方法不满足的条件
     *
     * @param conditionQuery
     * @param expressions
     * @param root
     * @param criteriaBuilder
     */
    @Override
    protected void addExtraExpression(ConditionQueryPurchaseBill conditionQuery, List<Expression<Boolean>> expressions, Root<PurchaseBill> root, CriteriaBuilder criteriaBuilder) {
        /**
         * 拼接供应商编码
         */
        if (conditionQuery.getSupplierCodeList() != null && conditionQuery.getSupplierCodeList().size() > 0) {
            expressions.add(root.get("supplierCode").as(String.class).in(conditionQuery.getSupplierCodeList()));
        }
        /**
         * 拼接入库库位
         */
        if (conditionQuery.getInStorageCodeList() != null && conditionQuery.getInStorageCodeList().size() > 0) {
            expressions.add(root.get("inStorageCode").as(String.class).in(conditionQuery.getInStorageCodeList()));
        }

    }
}
