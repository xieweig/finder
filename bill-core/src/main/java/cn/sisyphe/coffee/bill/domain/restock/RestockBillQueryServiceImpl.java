package cn.sisyphe.coffee.bill.domain.restock;

import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.user.repo.UserRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
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
 * @author bifenglin
 */
@Service
public class RestockBillQueryServiceImpl implements RestockBillQueryService {

    @Autowired
    private RestockBillRepository restockBillRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 根据单据编码查询单据信息
     *
     * @param billCode 单据编码
     * @return
     */
    @Override
    public RestockBill findByBillCode(String billCode) {
        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("20011", "进货单编码为空");
        }
        RestockBill restockBill = restockBillRepository.findOneByBillCode(billCode);
        if (restockBill != null) {
            return restockBill;
        } else {
            throw new DataException("20012", "根据该进货单编码没有查询到具体的进货单信息");
        }
    }
    @Override
    public Page<RestockBill> findPageByCondition(ConditionQueryRestockBill conditionQueryRestockBill) {
        // 组装页面
        Pageable pageable = new PageRequest(conditionQueryRestockBill.getPage() - 1, conditionQueryRestockBill.getPageSize());
        // SpringCloud调用查询录单人编码
        List<String> userCodeList = userRepository.findByLikeUserName(conditionQueryRestockBill.getOperatorName());
        conditionQueryRestockBill.setOperatorCodeList(userCodeList);

        Page<RestockBill> restockBillPage;
        restockBillPage = queryByParams(conditionQueryRestockBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (restockBillPage.getContent().size() < 1 && restockBillPage.getTotalElements() > 0) {
            pageable = new PageRequest(restockBillPage.getTotalPages() - 1, conditionQueryRestockBill.getPageSize());
            restockBillPage = queryByParams(conditionQueryRestockBill, pageable);
        }
        return restockBillPage;
    }

    @Override
    public RestockBill findOneByBillCode(String restockBillCode) {

        return null;
    }

    private Page<RestockBill> queryByParams(ConditionQueryRestockBill conditionQueryRestockBill, Pageable pageable) {
        return restockBillRepository.findAll((root, criteriaQuery, cb) -> {
            //去重
            criteriaQuery.distinct(true);
            Predicate predicate = cb.conjunction();

            List<Expression<Boolean>> expressions = predicate.getExpressions();
            /**
             * 录单人
             */
            if (conditionQueryRestockBill.getOperatorCodeList() != null
                    && conditionQueryRestockBill.getOperatorCodeList().size() > 0) {
                expressions.add(root.get("operatorCode").as(String.class).in(conditionQueryRestockBill.getOperatorCodeList()));
            }
            /**
             * 录单开始时间
             */
            if (!StringUtils.isEmpty(conditionQueryRestockBill.getCreateStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryRestockBill.getCreateStartTime()));
            }
            /**
             * 录单结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryRestockBill.getCreateEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryRestockBill.getCreateEndTime()));
            }
            /**
             * 进货单编码
             */
            if (!StringUtils.isEmpty(conditionQueryRestockBill.getBillCode())) {
                expressions.add(cb.equal(root.get("billCode").as(String.class), conditionQueryRestockBill.getBillCode()));
            }
            /**
             * 入库开始时间
             */
            if (!StringUtils.isEmpty(conditionQueryRestockBill.getInStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryRestockBill.getInStartTime()));
            }
            /**
             * 入库结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryRestockBill.getInEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("inWareHouseTime").as(Date.class), conditionQueryRestockBill.getInEndTime()));
            }
            /**
             * 供应商
             */
            if (!StringUtils.isEmpty(conditionQueryRestockBill.getSupplierCode())) {
                expressions.add(cb.equal(root.get("supplierCode").as(String.class), conditionQueryRestockBill.getSupplierCode()));
            }
            /**
             * 拼接状态
             */
            if (!StringUtils.isEmpty(conditionQueryRestockBill.getStatusCode())) {
                expressions.add(cb.equal(root.get("billState").as(String.class), conditionQueryRestockBill.getStatusCode()));
            }

//            SetJoin<PlanBill, Long> planBillLongSetJoin = root.join(root.getModel().getSet(""));
           /* //分组查询
            query.groupBy(root.get("billCode"));
            //*/
            return predicate;
        }, pageable);
    }
}
