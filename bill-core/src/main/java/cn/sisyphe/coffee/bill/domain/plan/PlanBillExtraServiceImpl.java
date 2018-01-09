package cn.sisyphe.coffee.bill.domain.plan;

import cn.sisyphe.coffee.bill.domain.base.model.db.DbGoods;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.user.repo.UserRepository;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;

@Service
public class PlanBillExtraServiceImpl implements PlanBillExtraService {

    @Autowired
    private PlanBillRepository planBillRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<PlanBill> findPageByCondition(ConditionQueryPlanBill conditionQueryPlanBill) {

        // 组装页面
        Pageable pageable = new PageRequest(conditionQueryPlanBill.getPage() - 1, conditionQueryPlanBill.getPageSize());
        // SpringCloud调用查询录单人编码
        List<String> userCodeList = userRepository.findByLikeUserName(conditionQueryPlanBill.getCreatorName());
        conditionQueryPlanBill.setCreatorCodeList(userCodeList);
        Page<PlanBill> planBillPage;
        planBillPage = queryByParams(conditionQueryPlanBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (planBillPage.getContent().size() < 1 && planBillPage.getTotalElements() > 0) {
            pageable = new PageRequest(planBillPage.getTotalPages() - 1, conditionQueryPlanBill.getPageSize());
            planBillPage = queryByParams(conditionQueryPlanBill, pageable);
        }
        return planBillPage;
    }

    @Override
    public PlanBill findByBillCode(String planBillCode) {
        if (StringUtils.isEmpty(planBillCode)) {
            throw new DataException("20011", "进货单编码为空");
        }
        PlanBill planBill = planBillRepository.findOneByBillCode(planBillCode);
        if (planBill != null) {
            return planBill;
        } else {
            throw new DataException("20012", "根据该进货单编码没有查询到具体的进货单信息");
        }
    }

    @Override
    public PlanBill findByBillCodeAndType(String billCode, BillTypeEnum billType) {
        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("20011", "进货单编码为空");
        }
        PlanBill planBill = planBillRepository.findByBillCodeAndType(billCode, billType);
        if (planBill != null) {
            return planBill;
        } else {
            throw new DataException("20012", "根据该进货单编码没有查询到具体的进货单信息");
        }
    }

    @Override
    public Page<PlanBill> findChildPlanBillBy(ConditionQueryPlanBill conditionQueryPlanBill) {

        List<String> operatorCodes = userRepository.findByLikeUserName(conditionQueryPlanBill.getCreatorName());
        conditionQueryPlanBill.setOperatorCodes(operatorCodes);
        // 组装页面
        Pageable pageable = new PageRequest(conditionQueryPlanBill.getPage() - 1, conditionQueryPlanBill.getPageSize());

        Page<PlanBill> planBillPage;
        planBillPage = queryChildByParams(conditionQueryPlanBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (planBillPage.getContent().size() < 1 && planBillPage.getTotalElements() > 0) {
            pageable = new PageRequest(planBillPage.getTotalPages() - 1, conditionQueryPlanBill.getPageSize());
            planBillPage = queryChildByParams(conditionQueryPlanBill, pageable);
        }
        return planBillPage;
    }

    @Override
    public void save(PlanBill planBill) {
        planBillRepository.save(planBill);
    }

    private Page<PlanBill> queryChildByParams(final ConditionQueryPlanBill conditionQueryPlanBill,
                                              Pageable pageable) {
        return planBillRepository.findAll((root, criteriaQuery, cb) -> {
            criteriaQuery.distinct(true);
            Predicate predicate = cb.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();

            // 计划类型
            if (conditionQueryPlanBill.getSpecificBillType() != null) {
                expressions.add(root.get("specificBillType").as(BillTypeEnum.class).in(conditionQueryPlanBill.getSpecificBillType()));
            }

            // 计划编码
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getBillCode())) {
                expressions.add(cb.like(root.get("billCode").as(String.class), "%" + conditionQueryPlanBill.getBillCode() + "%"));
            }

            // 入库站点集合
            if (conditionQueryPlanBill.getInStationCodeArray() != null && conditionQueryPlanBill.getInStationCodeArray().size() > 0) {
                expressions.add(root.<String>get("inStationCode").in(conditionQueryPlanBill.getInStationCodeArray()));
            }
            //出库站点集合
            if (conditionQueryPlanBill.getOutStationCodeArray() != null && conditionQueryPlanBill.getOutStationCodeArray().size() > 0) {
                expressions.add(root.<String>get("outStationCode").in(conditionQueryPlanBill.getOutStationCodeArray()));
            }
            /*
             * 录单开始时间
             */
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getCreateStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryPlanBill.getCreateStartTime()));
            }
            /*
             * 录单结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getCreateEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryPlanBill.getCreateEndTime()));
            }
            /*
             * 录单人
             */
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getCreatorName())) {
                expressions.add(root.get("operatorCode").as(String.class).in(conditionQueryPlanBill.getOperatorCodes()));
            }

            expressions.add(cb.equal(root.get("hqBill").as(Boolean.class), false));
            return predicate;
        }, pageable);
    }

    /**
     * @param conditionQueryPlanBill
     * @param pageable
     * @return
     * @throws DataException
     */
    private Page<PlanBill> queryByParams(final ConditionQueryPlanBill conditionQueryPlanBill,
                                         Pageable pageable) throws DataException {
        return planBillRepository.findAll((root, criteriaQuery, cb) -> {
            //去重
            criteriaQuery.distinct(true);
            Predicate predicate = cb.conjunction();
            //连接明细表
//            SetJoin<PlanBill, PlanBillDetail> planBillPlanBillDetailSetJoin = root.join(root.getModel().getSet("billDetails", PlanBillDetail.class), JoinType.LEFT);
            Join<PlanBill, PlanBillDetail> planBillPlanBillDetailSetJoin = root.join("billDetails", JoinType.LEFT);
            //连接明细表里的商品
            Join<PlanBillDetail, DbGoods> planBillDetailDbGoodsJoin = planBillPlanBillDetailSetJoin.join("dbGoods", JoinType.LEFT);
            List<Expression<Boolean>> expressions = predicate.getExpressions();

            //站点计划号未实现
            /*if (!StringUtils.isEmpty(conditionQueryPlanBill.getBillCode())) {
                expressions.add(cb.like(root.<String>get("billCode"), "%" + conditionQueryPlanBill.getBillCode() + "%"));
            }*/


            //单据的种类
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getSpecificBillType())) {
                expressions.add(cb.equal(root.<String>get("specificBillType"),
                        "" + conditionQueryPlanBill.getSpecificBillType() + ""));
            }

            // 入库站点集合
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getInStationCodeArray())) {
                expressions.add(root.<String>get("inStationCode").in(conditionQueryPlanBill.getInStationCodeArray()));
            }
            //出库站点集合
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getOutStationCodeArray())) {
                expressions.add(root.<String>get("outStationCode").in(conditionQueryPlanBill.getOutStationCodeArray()));
            }

            //录单人
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getCreatorName())) {
                expressions.add(cb.like(root.<String>get("creatorName"),
                        "%" + conditionQueryPlanBill.getCreatorName() + "%"));
            }

            /**
             * 录单开始时间
             */
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getCreateStartTime())) {
                expressions.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryPlanBill.getCreateStartTime()));
            }
            /**
             * 录单结束时间
             */
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getCreateEndTime())) {
                expressions.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class), conditionQueryPlanBill.getCreateEndTime()));
            }

//            SetJoin<PlanBill, Long> planBillLongSetJoin = root.join(root.getModel().getSet(""));
           /* //分组查询
            query.groupBy(root.get("billCode"));
            //*/

            //是否为总部计划单
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getHqBill()) && "true".equals(conditionQueryPlanBill.getHqBill())) {
                expressions.add(cb.isTrue(root.<Boolean>get("hqBill")));
            } else if (!StringUtils.isEmpty(conditionQueryPlanBill.getHqBill()) && "false".equals(conditionQueryPlanBill.getHqBill())) {
                expressions.add(cb.isFalse(root.<Boolean>get("hqBill")));
            }

            //单据名称模糊查询
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getBillName())) {
                expressions.add(cb.like(root.<String>get("billName"), "%" + conditionQueryPlanBill.getBillName() + "%"));
            }
            //货物编号集合
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getCargoCodeArray())) {
                expressions.add(planBillDetailDbGoodsJoin.<String>get("cargoCode").in(conditionQueryPlanBill.getCargoCodeArray()));
            }
            //货物编号模糊查询
            if (!StringUtils.isEmpty(conditionQueryPlanBill.getCargoCode())) {
                expressions.add(cb.like(planBillDetailDbGoodsJoin.<String>get("cargoCode"), "%" + conditionQueryPlanBill.getCargoCode() + "%"));
            }
            return predicate;
        }, pageable);
    }
}
