package cn.sisyphe.coffee.bill.domain.purchase;

import cn.sisyphe.coffee.bill.infrastructure.purchase.PurchaseBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.purchase.ConditionQueryPurchaseBill;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单查询服务接口实现
 * version: 1.0
 *
 * @author XiongJing
 */
@Service
public class PurchaseBillExtraServiceImpl implements PurchaseBillExtraService {

    @Autowired
    private PurchaseBillRepository purchaseBillRepository;

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
     * 多条件查询
     *
     * @param conditionQueryPurchaseBill 查询条件
     * @return
     */
    @Override
    public Page<PurchaseBill> findByConditions(ConditionQueryPurchaseBill conditionQueryPurchaseBill) {
        // 组装页面
        Pageable pageable = new PageRequest(conditionQueryPurchaseBill.getPage() - 1, conditionQueryPurchaseBill.getPageSize());
        Page<PurchaseBill> purchaseBillPage = queryByParams(conditionQueryPurchaseBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (purchaseBillPage.getContent().size() < 1 && purchaseBillPage.getTotalElements() > 0) {
            pageable = new PageRequest(purchaseBillPage.getTotalPages() - 1, conditionQueryPurchaseBill.getPageSize());
            purchaseBillPage = queryByParams(conditionQueryPurchaseBill, pageable);
        }
        return purchaseBillPage;
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
             * 录单人
             */
            if (!StringUtils.isEmpty(conditionQueryPurchaseBill.getOperatorName())) {
                expressions.add(root.get("operatorCode").as(String.class).in(conditionQueryPurchaseBill.getOperatorCodeList()));
            }
            /**
             * 录单开始时间
             */
            if (!StringUtils.isEmpty(conditionQueryPurchaseBill.getCreateStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryPurchaseBill.getCreateStartTime()));
            }
            /**
             * 录单结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryPurchaseBill.getCreateEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryPurchaseBill.getCreateEndTime()));
            }
            /**
             * 进货单编码
             */
            if (!StringUtils.isEmpty(conditionQueryPurchaseBill.getBillCode())) {
                expressions.add(cb.equal(root.get("billCode").as(String.class), conditionQueryPurchaseBill.getBillCode()));
            }
            /**
             * 入库开始时间
             */
            if (!StringUtils.isEmpty(conditionQueryPurchaseBill.getInStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("inWareHouseTime").as(Date.class), conditionQueryPurchaseBill.getInStartTime()));
            }
            /**
             * 入库结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryPurchaseBill.getInEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("inWareHouseTime").as(Date.class), conditionQueryPurchaseBill.getInEndTime()));
            }
            /**
             * 供应商
             */
            if (!StringUtils.isEmpty(conditionQueryPurchaseBill.getSupplierCode())) {
                expressions.add(cb.equal(root.get("supplierCode").as(String.class), conditionQueryPurchaseBill.getSupplierCode()));
            }
            /**
             * 拼接提交状态
             */
            if (conditionQueryPurchaseBill.getSubmitStateCode() != null && conditionQueryPurchaseBill.getSubmitStateCode().size() > 0) {
                expressions.add(root.get("submitState").as(String.class).in(conditionQueryPurchaseBill.getSubmitStateCode()));
            }

            /**
             * 拼接审核状态
             */
            if (conditionQueryPurchaseBill.getAuditStateCode() != null && conditionQueryPurchaseBill.getAuditStateCode().size() > 0) {
                expressions.add(root.get("auditState").as(String.class).in(conditionQueryPurchaseBill.getSubmitStateCode()));
            }

            /**
             * 拼接出入库状态
             */
            if (conditionQueryPurchaseBill.getInOrOutStateCode() != null && conditionQueryPurchaseBill.getInOrOutStateCode().size() > 0) {
                expressions.add(root.get("inOrOutState").as(String.class).in(conditionQueryPurchaseBill.getInOrOutStateCode()));
            }
            /**
             * 拼接入库站点
             */
            if (conditionQueryPurchaseBill.getInStationCodeList() != null && conditionQueryPurchaseBill.getInStationCodeList().size() > 0) {
                expressions.add(root.get("inStationCode").as(String.class).in(conditionQueryPurchaseBill.getInStationCodeList()));
            }
            /**
             * 拼接入库库位
             */
            if (conditionQueryPurchaseBill.getInStorageCodeList() != null && conditionQueryPurchaseBill.getInStorageCodeList().size() > 0) {
                expressions.add(root.get("inStorageCode").as(String.class).in(conditionQueryPurchaseBill.getInStorageCodeList()));
            }

            return predicate;
        }, pageable);
    }
}
