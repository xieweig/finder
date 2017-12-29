package cn.sisyphe.coffee.bill.domain.purchase;

import cn.sisyphe.coffee.bill.infrastructure.purchase.PurchaseBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.ConditionQueryPurchaseBill;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单查询服务接口实现
 * version: 1.0
 *
 * @author XiongJing
 */
@Service
public class PurchaseBillQueryServiceImpl implements PurchaseBillQueryService {

    @Autowired
    private PurchaseBillRepository purchaseBillRepository;

    /**
     * 多条件查询
     *
     * @param conditionQueryPurchaseBill 查询条件
     * @return
     */
    @Override
    public Page<PurchaseBill> findByConditions(ConditionQueryPurchaseBill conditionQueryPurchaseBill) {
        // 组装页面
        Pageable pageable = new PageRequest(conditionQueryPurchaseBill.getPage() - 1, conditionQueryPurchaseBill.getPageSize());

        Page<PurchaseBill> purchaseBillPage;
        purchaseBillPage = queryByParams(conditionQueryPurchaseBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (purchaseBillPage.getContent().size() < 1 && purchaseBillPage.getTotalElements() > 0) {
            pageable = new PageRequest(purchaseBillPage.getTotalPages() - 1, conditionQueryPurchaseBill.getPageSize());
            purchaseBillPage = queryByParams(conditionQueryPurchaseBill, pageable);
        }
        return purchaseBillPage;
    }

    /**
     * 根据单据编码查询单据信息
     *
     * @param billCode 单据编码
     * @return
     */
    @Override
    public PurchaseBill findByBillCode(String billCode) {
        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("20011", "进货单编码为空");
        }
        PurchaseBill purchaseBill = purchaseBillRepository.findOneByBillCode(billCode);
        if (purchaseBill != null) {
            return purchaseBill;
        } else {
            throw new DataException("20012", "根据该进货单编码没有查询到具体的进货单信息");
        }
    }

    /**
     * 组装参数
     *
     * @param conditionQueryPurchaseBill
     * @param pageable
     * @return
     */
    private Page<PurchaseBill> queryByParams(final ConditionQueryPurchaseBill conditionQueryPurchaseBill, Pageable pageable) {
        return purchaseBillRepository.findAll((root, query, cb) -> {
            query.distinct(true);
            Predicate predicate = cb.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();

            /**
             * 进货单编码
             */
            if (!StringUtils.isEmpty(conditionQueryPurchaseBill.getBillCode())) {
                expressions.add(cb.equal(root.get("billCode").as(String.class), conditionQueryPurchaseBill.getBillCode()));
            }
            /**
             *
             */


            return predicate;
        }, pageable);
    }
}
