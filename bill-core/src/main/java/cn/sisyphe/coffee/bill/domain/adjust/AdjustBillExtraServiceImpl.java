package cn.sisyphe.coffee.bill.domain.adjust;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂业务逻辑接口实现
 * version: 1.0
 *
 * @author XiongJing
 */

@Service
public class AdjustBillExtraServiceImpl implements AdjustBillExtraService {

    @Autowired
    private AdjustBillRepository adjustBillRepository;

    @Override
    public AdjustBill findByBillCode(String billCode) {
        return adjustBillRepository.findOneByBillCode(billCode);
    }

    @Override
    public AdjustBill findBySourceCode(String sourceCode) {
        return adjustBillRepository.findOneBySourceCode(sourceCode);
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
                expressions.add(cb.like(root.get("billCode").as(String.class), "%"+conditionQueryAdjustBill.getBillCode()+"%"));
            }
            /**
             * 出库站点集合
             */
            if (!StringUtils.isEmpty(conditionQueryAdjustBill.getOutStationCodeArray())) {
                String[] outStationCodeArr = conditionQueryAdjustBill.getOutStationCodeArray().split(",");
                expressions.add(root.<String>get("dbStation").get("outStationCode").in(Arrays.asList(outStationCodeArr)));
            }
            /**
             * 入库站点集合
             */
            if (!StringUtils.isEmpty(conditionQueryAdjustBill.getInStationCodeArray())) {
                String[] inStationCodeArr = conditionQueryAdjustBill.getInStationCodeArray().split(",");
                expressions.add(root.<String>get("dbStation").get("inStationCode").in(Arrays.asList(inStationCodeArr)));
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
            if (!StringUtils.isEmpty(conditionQueryAdjustBill.getInOrOutStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("inWareHouseTime").as(Date.class), conditionQueryAdjustBill.getInOrOutStartTime()));
            }
            /**
             * 出库结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryAdjustBill.getInOrOutEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("inWareHouseTime").as(Date.class), conditionQueryAdjustBill.getInOrOutEndTime()));
            }
            /**
             * 提交状态
             */
            if (conditionQueryAdjustBill.getSubmitStateCode() != null && conditionQueryAdjustBill.getSubmitStateCode().size() > 0) {
                expressions.add(root.get("submitState").as(BillSubmitStateEnum.class).in(conditionQueryAdjustBill.getSubmitStateCode()));
            }
            /**
             * 拼接审核状态
             */
            if (conditionQueryAdjustBill.getAuditStateCode() != null && conditionQueryAdjustBill.getAuditStateCode().size() > 0) {
                expressions.add(root.get("auditState").as(BillAuditStateEnum.class).in(conditionQueryAdjustBill.getAuditStateCode()));
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
            if (conditionQueryAdjustBill.getVarietyStart() != null && conditionQueryAdjustBill.getVarietyStart() > 0) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("adjustNumber").as(Integer.class), conditionQueryAdjustBill.getVarietyStart()));
            }
            /**
             * 配送品种结束数量
             */
            if (conditionQueryAdjustBill.getVarietyEnd() != null && conditionQueryAdjustBill.getVarietyEnd() > 0) {
                expressions.add(cb.lessThanOrEqualTo(root.get("adjustNumber").as(Integer.class), conditionQueryAdjustBill.getVarietyEnd()));
            }
            /**
             * 单据作用
             */
            if (conditionQueryAdjustBill.getPurposeEnum() != null) {
                expressions.add(cb.equal(root.get("billPurpose").as(BillPurposeEnum.class), conditionQueryAdjustBill.getPurposeEnum()));
            }
            /**
             * 单据主状态
             */
            if (conditionQueryAdjustBill.getBillStateEnum() != null) {
                expressions.add(cb.equal(root.get("billState").as(BillStateEnum.class), conditionQueryAdjustBill.getBillStateEnum()));
            }

            return predicate;
        }, pageable);
    }
}
