package cn.sisyphe.coffee.bill.domain.returned;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.infrastructure.returned.ReturnedBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.returned.ConditionQueryReturnedBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author bifenglin
 */
@Service
public class ReturnedBillExtraServiceImpl implements ReturnedBillExtraService {

    @Autowired
    private ReturnedBillRepository returnedBillRepository;

    /**
     * 根据单据编码查询单据信息
     *
     * @param billCode 单据编码
     * @return
     */
    @Override
    public ReturnedBill findByBillCode(String billCode) {
        ReturnedBill returnedBill = returnedBillRepository.findOneByBillCode(billCode);
        return returnedBill;
    }

    @Override
    public Page<ReturnedBill> findPageByCondition(ConditionQueryReturnedBill conditionQueryReturnedBill) {
        // 组装页面
        Pageable pageable = new PageRequest(conditionQueryReturnedBill.getPage() - 1, conditionQueryReturnedBill.getPageSize());

        Page<ReturnedBill> returnedBillPage;
        returnedBillPage = queryByParams(conditionQueryReturnedBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (returnedBillPage.getContent().size() < 1 && returnedBillPage.getTotalElements() > 0) {
            pageable = new PageRequest(returnedBillPage.getTotalPages() - 1, conditionQueryReturnedBill.getPageSize());
            returnedBillPage = queryByParams(conditionQueryReturnedBill, pageable);
        }
        return returnedBillPage;
    }

    @Override
    public ReturnedBill findBySourceCode(String sourceCode) {
        return returnedBillRepository.findOneBySourceCode(sourceCode);
    }

    private Page<ReturnedBill> queryByParams(ConditionQueryReturnedBill conditionQueryReturnedBill, Pageable pageable) {
        return returnedBillRepository.findAll((root, criteriaQuery, cb) -> {
            //去重
            criteriaQuery.distinct(true);
            Predicate predicate = cb.conjunction();

            List<Expression<Boolean>> expressions = predicate.getExpressions();

            // 计划类型
            if (conditionQueryReturnedBill.getBillType() != null) {
                expressions.add(root.get("billType").as(BillTypeEnum.class).in(conditionQueryReturnedBill.getBillType()));
            }

            //计划类型
            if (conditionQueryReturnedBill.getBillPurpose() != null) {
                expressions.add(root.get("billPurpose").as(BillPurposeEnum.class).in(conditionQueryReturnedBill.getBillPurpose()));
            }

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
                expressions.add(cb.like(root.get("billCode").as(String.class), "%" + conditionQueryReturnedBill.getBillCode() + "%"));
            }
            /**
             * 入库站点集合
             */
            if (!StringUtils.isEmpty(conditionQueryReturnedBill.getInStationCodeArray())) {
                String[] inStationCodeArr = conditionQueryReturnedBill.getInStationCodeArray().split(",");
                expressions.add(root.get("dbStation").get("inStationCode").in(Arrays.asList(inStationCodeArr)));
            }
            /**
             * 出库站点集合
             */
            if (!StringUtils.isEmpty(conditionQueryReturnedBill.getOutStationCodeArray())) {
                String[] outStationCodeArr = conditionQueryReturnedBill.getOutStationCodeArray().split(",");
                expressions.add(root.get("dbStation").get("outStationCode").in(Arrays.asList(outStationCodeArr)));
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
                expressions.add(cb.greaterThanOrEqualTo(root.get("inWareHouseTime").as(Date.class), conditionQueryReturnedBill.getInStartTime()));
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
