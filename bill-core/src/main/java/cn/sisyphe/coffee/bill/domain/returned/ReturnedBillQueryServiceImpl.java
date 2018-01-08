package cn.sisyphe.coffee.bill.domain.returned;


import cn.sisyphe.coffee.bill.domain.returned.ReturnedBill;
import cn.sisyphe.coffee.bill.infrastructure.returned.ReturnedBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.returned.ReturnedBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.user.repo.UserRepository;
import cn.sisyphe.coffee.bill.viewmodel.returned.ConditionQueryReturnedBill;
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
 * Created by Mayupeng on 2018/01/05.
 * remark：退货单查询服务接口实现
 * version: 1.0
 *
 * @author Mayupeng
 */
@Service
public class ReturnedBillQueryServiceImpl implements  ReturnedBillQueryService{
    @Autowired
    private ReturnedBillRepository returnedBillRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 根据单据编码查询单据信息
     *
     * @param billCode 单据编码
     * @return
     */
    @Override
    public ReturnedBill findByBillCode(String billCode) {
        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("20011", "进货单编码为空");
        }
        ReturnedBill returnedBill = returnedBillRepository.findOneByBillCode(billCode);
        if (returnedBill != null) {
            return returnedBill;
        } else {
            throw new DataException("20012", "根据该进货单编码没有查询到具体的进货单信息");
        }
    }
    @Override
    public Page<ReturnedBill> findPageByCondition(ConditionQueryReturnedBill conditionQueryReturnedBill) {
        // 组装页面
        Pageable pageable = new PageRequest(conditionQueryReturnedBill.getPage() - 1, conditionQueryReturnedBill.getPageSize());
        // SpringCloud调用查询录单人编码
        List<String> userCodeList = userRepository.findByLikeUserName(conditionQueryReturnedBill.getOperatorName());
        conditionQueryReturnedBill.setOperatorCodeList(userCodeList);

        Page<ReturnedBill> returnedBillPage;
        returnedBillPage = queryByParams(conditionQueryReturnedBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (returnedBillPage.getContent().size() < 1 && returnedBillPage.getTotalElements() > 0) {
            pageable = new PageRequest(returnedBillPage.getTotalPages() - 1, conditionQueryReturnedBill.getPageSize());
            returnedBillPage = queryByParams(conditionQueryReturnedBill, pageable);
        }
        return returnedBillPage;
    }

    private Page<ReturnedBill> queryByParams(ConditionQueryReturnedBill conditionQueryReturnedBill, Pageable pageable) {
        return returnedBillRepository.findAll((root, criteriaQuery, cb) -> {
            //去重
            criteriaQuery.distinct(true);
            Predicate predicate = cb.conjunction();

            List<Expression<Boolean>> expressions = predicate.getExpressions();
            /**
             * 录单人
             */
            if (conditionQueryReturnedBill.getOperatorCodeList() != null
                    && conditionQueryReturnedBill.getOperatorCodeList().size() > 0) {
                expressions.add(root.get("operatorCode").as(String.class).in(conditionQueryReturnedBill.getOperatorCodeList()));
            }
            /**
             * 出库单编码
             */
            if (!StringUtils.isEmpty(conditionQueryReturnedBill.getBillCode())) {
                expressions.add(cb.like(root.get("billCode").as(String.class), "%"+conditionQueryReturnedBill.getBillCode()+"%"));
            }
            /**
             * 入库站点集合
             */
            if (conditionQueryReturnedBill.getInStationCodeArray() != null && conditionQueryReturnedBill.getInStationCodeArray().size() > 0) {
                expressions.add(root.<String>get("inStationCode").in(conditionQueryReturnedBill.getInStationCodeArray()));
            }
            /**
             * 出库站点集合
             */
            if (conditionQueryReturnedBill.getOutStationCodeArray() != null && conditionQueryReturnedBill.getOutStationCodeArray().size() > 0) {
                expressions.add(root.<String>get("outStationCode").in(conditionQueryReturnedBill.getOutStationCodeArray()));
            }
            /**
             * 录单开始时间
             */
            if (!StringUtils.isEmpty(conditionQueryReturnedBill.getCreateStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryReturnedBill.getCreateStartTime()));
            }
            /**
             * 录单结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryReturnedBill.getCreateEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryReturnedBill.getCreateEndTime()));
            }

            /**
             * 入库开始时间
             */
            if (!StringUtils.isEmpty(conditionQueryReturnedBill.getInStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryReturnedBill.getInStartTime()));
            }
            /**
             * 入库结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryReturnedBill.getInEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("inWareHouseTime").as(Date.class), conditionQueryReturnedBill.getInEndTime()));
            }

            /**
             * 拼接提交状态
             */
            if (conditionQueryReturnedBill.getSubmitStateCode() != null && conditionQueryReturnedBill.getSubmitStateCode().size() > 0) {
                expressions.add(root.get("submitState").as(String.class).in(conditionQueryReturnedBill.getSubmitStateCode()));
            }

            /**
             * 拼接审核状态
             */
            if (conditionQueryReturnedBill.getAuditStateCode() != null && conditionQueryReturnedBill.getAuditStateCode().size() > 0) {
                expressions.add(root.get("auditState").as(String.class).in(conditionQueryReturnedBill.getSubmitStateCode()));
            }

            /**
             * 拼接出入库状态
             */
            if (conditionQueryReturnedBill.getInOrOutStateCode() != null && conditionQueryReturnedBill.getInOrOutStateCode().size() > 0) {
                expressions.add(root.get("inOrOutState").as(String.class).in(conditionQueryReturnedBill.getInOrOutStateCode()));
            }
            /**
             * 配送总价
             */
            if (!StringUtils.isEmpty(conditionQueryReturnedBill.getStartVariety())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("variety").as(Integer.class), conditionQueryReturnedBill.getStartVariety()));
            }
            if (!StringUtils.isEmpty(conditionQueryReturnedBill.getEndVariety())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("variety").as(Integer.class), conditionQueryReturnedBill.getEndVariety()));
            }

            /**
             * 配送总价
             */
            if (!StringUtils.isEmpty(conditionQueryReturnedBill.getStartTotalPrice())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("totalPrice").as(BigDecimal.class), conditionQueryReturnedBill.getStartTotalPrice()));
            }
            if (!StringUtils.isEmpty(conditionQueryReturnedBill.getEndTotalPrice())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("totalPrice").as(BigDecimal.class), conditionQueryReturnedBill.getEndTotalPrice()));
            }

//            SetJoin<ReturnedBill, Long> returnedBillLongSetJoin = root.join(root.getModel().getSet(""));
           /* //分组查询
            query.groupBy(root.get("billCode"));
            //*/
            return predicate;
        }, pageable);
    }
}
