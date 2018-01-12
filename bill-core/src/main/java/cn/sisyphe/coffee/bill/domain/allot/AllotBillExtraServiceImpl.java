package cn.sisyphe.coffee.bill.domain.allot;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.infrastructure.allot.AllotBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
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
 * @author bifenglin
 */
@Service
public class AllotBillExtraServiceImpl implements AllotBillExtraService {

    @Autowired
    private AllotBillRepository allotBillRepository;
    
    @Override
    public Page<AllotBill> findPageByCondition(ConditionQueryAllotBill conditionQueryAllotBill) {
        // 组装页面
        Pageable pageable = new PageRequest(conditionQueryAllotBill.getPage() - 1, conditionQueryAllotBill.getPageSize());

        Page<AllotBill> allotBillPage;
        allotBillPage = queryByParams(conditionQueryAllotBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (allotBillPage.getContent().size() < 1 && allotBillPage.getTotalElements() > 0) {
            pageable = new PageRequest(allotBillPage.getTotalPages() - 1, conditionQueryAllotBill.getPageSize());
            allotBillPage = queryByParams(conditionQueryAllotBill, pageable);
        }
        return allotBillPage;
    }

    private Page<AllotBill> queryByParams(ConditionQueryAllotBill conditionQueryAllotBill, Pageable pageable) {
        return allotBillRepository.findAll((root, criteriaQuery, cb) -> {
            //去重
            criteriaQuery.distinct(true);
            Predicate predicate = cb.conjunction();

            List<Expression<Boolean>> expressions = predicate.getExpressions();

            // 计划类型
            if (conditionQueryAllotBill.getSpecificBillType() != null) {
                expressions.add(root.get("specificBillType").as(BillTypeEnum.class).in(conditionQueryAllotBill.getSpecificBillType()));
            }
            /**
             * 录单人
             */
            if (conditionQueryAllotBill.getOperatorCodeList() != null
                    && conditionQueryAllotBill.getOperatorCodeList().size() > 0) {
                expressions.add(root.get("operatorCode").as(String.class).in(conditionQueryAllotBill.getOperatorCodeList()));
            }
            /**
             * 出库单编码
             */
            if (!StringUtils.isEmpty(conditionQueryAllotBill.getBillCode())) {
                expressions.add(cb.like(root.get("billCode").as(String.class), "%" + conditionQueryAllotBill.getBillCode() + "%"));
            }
            /**
             * 入库站点集合
             */
            if (!StringUtils.isEmpty(conditionQueryAllotBill.getInStationCodeArray())) {
                String[] inStationCodeArr = conditionQueryAllotBill.getInStationCodeArray().split(",");
                expressions.add(root.get("dbStation").get("inStationCode").in(Arrays.asList(inStationCodeArr)));
            }
            /**
             * 出库站点集合
             */
            if (!StringUtils.isEmpty(conditionQueryAllotBill.getOutStationCodeArray())) {
                String[] outStationCodeArr = conditionQueryAllotBill.getOutStationCodeArray().split(",");
                expressions.add(root.get("dbStation").get("outStationCode").in(Arrays.asList(outStationCodeArr)));
            }
            /**
             * 录单开始时间
             */
            if (!StringUtils.isEmpty(conditionQueryAllotBill.getCreateStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryAllotBill.getCreateStartTime()));
            }
            /**
             * 录单结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryAllotBill.getCreateEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryAllotBill.getCreateEndTime()));
            }
            return predicate;
        }, pageable);
    }
}
