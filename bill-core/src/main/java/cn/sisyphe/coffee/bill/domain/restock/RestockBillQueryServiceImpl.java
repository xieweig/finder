package cn.sisyphe.coffee.bill.domain.restock;

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
import java.math.BigDecimal;
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
             * 出库单编码
             */
            if (!StringUtils.isEmpty(conditionQueryRestockBill.getBillCode())) {
                expressions.add(cb.like(root.get("billCode").as(String.class), "%"+conditionQueryRestockBill.getBillCode()+"%"));
            }
            /**
             * 入库站点集合
             */
            if (conditionQueryRestockBill.getInStationCodeArray() != null && conditionQueryRestockBill.getInStationCodeArray().size() > 0) {
                expressions.add(root.<String>get("inStationCode").in(conditionQueryRestockBill.getInStationCodeArray()));
            }
            /**
             * 出库站点集合
             */
            if (conditionQueryRestockBill.getOutStationCodeArray() != null && conditionQueryRestockBill.getOutStationCodeArray().size() > 0) {
                expressions.add(root.<String>get("outStationCode").in(conditionQueryRestockBill.getOutStationCodeArray()));
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
             * 拼接提交状态
             */
            if (conditionQueryRestockBill.getSubmitStateCode() != null && conditionQueryRestockBill.getSubmitStateCode().size() > 0) {
                expressions.add(root.get("submitState").as(String.class).in(conditionQueryRestockBill.getSubmitStateCode()));
            }

            /**
             * 拼接审核状态
             */
            if (conditionQueryRestockBill.getAuditStateCode() != null && conditionQueryRestockBill.getAuditStateCode().size() > 0) {
                expressions.add(root.get("auditState").as(String.class).in(conditionQueryRestockBill.getSubmitStateCode()));
            }

            /**
             * 拼接出入库状态
             */
            if (conditionQueryRestockBill.getInOrOutStateCode() != null && conditionQueryRestockBill.getInOrOutStateCode().size() > 0) {
                expressions.add(root.get("inOrOutState").as(String.class).in(conditionQueryRestockBill.getInOrOutStateCode()));
            }
            /**
             * 配送总价
             */
            if (!StringUtils.isEmpty(conditionQueryRestockBill.getStartVariety())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("variety").as(Integer.class), conditionQueryRestockBill.getStartVariety()));
            }
            if (!StringUtils.isEmpty(conditionQueryRestockBill.getEndVariety())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("variety").as(Integer.class), conditionQueryRestockBill.getEndVariety()));
            }

            /**
             * 配送总价
             */
            if (!StringUtils.isEmpty(conditionQueryRestockBill.getStartTotalPrice())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("totalPrice").as(BigDecimal.class), conditionQueryRestockBill.getStartTotalPrice()));
            }
            if (!StringUtils.isEmpty(conditionQueryRestockBill.getEndTotalPrice())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("totalPrice").as(BigDecimal.class), conditionQueryRestockBill.getEndTotalPrice()));
            }

//            SetJoin<RestockBill, Long> restockBillLongSetJoin = root.join(root.getModel().getSet(""));
           /* //分组查询
            query.groupBy(root.get("billCode"));
            //*/
            return predicate;
        }, pageable);
    }
}
