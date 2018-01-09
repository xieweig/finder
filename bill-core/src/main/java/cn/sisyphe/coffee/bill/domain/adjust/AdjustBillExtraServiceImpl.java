package cn.sisyphe.coffee.bill.domain.adjust;

import cn.sisyphe.coffee.bill.infrastructure.adjust.AdjustBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;
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
 * Created by XiongJing on 2018/1/8.
 * remark：
 * version:
 */

@Service
public class AdjustBillExtraServiceImpl implements AdjustBillExtraService {

    @Autowired
    private AdjustBillRepository adjustBillRepository;

    @Override
    public AdjustBill findByBillCode(String billCode) {
        return adjustBillRepository.findOneByBillCode(billCode);
    }

    /**
     * 多条件查询调拨单信息
     *
     * @param conditionQueryAdjustBill 查询条件
     * @return
     */
    @Override
    public Page<AdjustBill> findByConditions(ConditionQueryAdjustBill conditionQueryAdjustBill) {
        // 组装页面
        Pageable pageable = new PageRequest(conditionQueryAdjustBill.getPage() - 1, conditionQueryAdjustBill.getPageSize());
        Page<AdjustBill> adjustBillPage = queryByParams(conditionQueryAdjustBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (adjustBillPage.getContent().size() < 1 && adjustBillPage.getTotalElements() > 0) {
            pageable = new PageRequest(adjustBillPage.getTotalPages() - 1, conditionQueryAdjustBill.getPageSize());
            adjustBillPage = queryByParams(conditionQueryAdjustBill, pageable);
        }
        return adjustBillPage;
    }

    /**
     * 组装参数
     *
     * @param conditionQueryAdjustBill 查询条件
     * @param pageable                 分页信息
     * @return
     */
    private Page<AdjustBill> queryByParams(final ConditionQueryAdjustBill conditionQueryAdjustBill, Pageable pageable) {
        return adjustBillRepository.findAll((root, query, cb) -> {
            query.distinct(true);
            Predicate predicate = cb.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();

            /**
             * 录单人名称
             */
            if (!StringUtils.isEmpty(conditionQueryAdjustBill.getOperatorName())) {
                expressions.add(root.get("operatorCode").as(String.class).in(conditionQueryAdjustBill.getOperatorCodeList()));
            }
            /**
             * 出库单号
             */
            if (!StringUtils.isEmpty(conditionQueryAdjustBill.getBillCode())) {
                expressions.add(cb.equal(root.get("billCode").as(String.class), conditionQueryAdjustBill.getBillCode()));
            }
            /**
             * 出库站点
             */
            if (conditionQueryAdjustBill.getOutStationCodeList() != null && conditionQueryAdjustBill.getOutStationCodeList().size() > 0) {
                expressions.add(root.get("outStationCode").as(String.class).in(conditionQueryAdjustBill.getOutStationCodeList()));
            }
            /**
             * 入库站点
             */
            if (conditionQueryAdjustBill.getInStationCodeList() != null && conditionQueryAdjustBill.getInStationCodeList().size() > 0) {
                expressions.add(root.get("inStationCode").as(String.class).in(conditionQueryAdjustBill.getInStationCodeList()));
            }

            /**
             * 录单开始时间
             */
            if (!StringUtils.isEmpty(conditionQueryAdjustBill.getCreateStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryAdjustBill.getCreateStartTime()));
            }
            /**
             * 录单结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryAdjustBill.getCreateEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryAdjustBill.getCreateEndTime()));
            }
            /**
             * 出库开始时间
             */
            if (!StringUtils.isEmpty(conditionQueryAdjustBill.getOutStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("inWareHouseTime").as(Date.class), conditionQueryAdjustBill.getOutStartTime()));
            }
            /**
             * 出库结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryAdjustBill.getOutEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("inWareHouseTime").as(Date.class), conditionQueryAdjustBill.getOutEndTime()));
            }
            /**
             * 提交状态
             */
            if (conditionQueryAdjustBill.getSubmitStateCode() != null && conditionQueryAdjustBill.getSubmitStateCode().size() > 0) {
                expressions.add(root.get("submitState").as(SourcePlanTypeEnum.class).in(conditionQueryAdjustBill.getSubmitStateCode()));
            }
            /**
             * 拼接审核状态
             */
            if (conditionQueryAdjustBill.getAuditStateCode() != null && conditionQueryAdjustBill.getAuditStateCode().size() > 0) {
                expressions.add(root.get("auditState").as(SourcePlanTypeEnum.class).in(conditionQueryAdjustBill.getAuditStateCode()));
            }
            /**
             * 单据属性
             */
            if (conditionQueryAdjustBill.getBillTypeCodeList() != null && conditionQueryAdjustBill.getBillTypeCodeList().size() > 0) {
                expressions.add(root.get("billTypeStr").as(SourcePlanTypeEnum.class).in(conditionQueryAdjustBill.getBillTypeCodeList()));
            }
            /**
             * 配送品种开始数量
             */
            if (conditionQueryAdjustBill.getVarietyStart() > 0) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("adjustNumber").as(Integer.class), conditionQueryAdjustBill.getVarietyStart()));
            }
            /**
             * 配送品种结束数量
             */
            if (conditionQueryAdjustBill.getVarietyEnd() > 0) {
                expressions.add(cb.lessThanOrEqualTo(root.get("adjustNumber").as(Integer.class), conditionQueryAdjustBill.getVarietyEnd()));
            }
            /**
             * 配送总价开始
             */
            if (conditionQueryAdjustBill.getTotalPriceStart().compareTo(BigDecimal.ZERO) > 0) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("totalPrice").as(BigDecimal.class), conditionQueryAdjustBill.getTotalPriceStart()));
            }
            /**
             * 配送总价结束
             */
            if (conditionQueryAdjustBill.getTotalPriceEnd().compareTo(BigDecimal.ZERO) > 0) {
                expressions.add(cb.lessThanOrEqualTo(root.get("totalPrice").as(BigDecimal.class), conditionQueryAdjustBill.getTotalPriceEnd()));
            }
            return predicate;
        }, pageable);
    }
}
