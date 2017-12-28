package cn.sisyphe.coffee.bill.domain.transmit;

import cn.sisyphe.coffee.bill.infrastructure.transmit.WayBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.waybill.ConditionQueryWayBill;
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
 * 运货单
 * Created by Administrator on 2017/12/28.
 */
@Service
public class IWayBillServiceImpl implements IWayBillService {

    @Autowired
    private WayBillRepository wayBillRepository;


    /**
     * 分页查询运单
     *
     * @param conditionQueryWayBill
     * @return
     * @throws DataException
     */
    @Override
    public Page<WayBill> findPageByCondition(ConditionQueryWayBill conditionQueryWayBill) throws DataException {


        Pageable pageable = new PageRequest(conditionQueryWayBill.getPage() - 1, conditionQueryWayBill.getPageSize());
        Page<WayBill> configurePage = pageCondition(conditionQueryWayBill, pageable);
        // 改变页码导致的页面为空时，获取最后一页
        if (configurePage.getContent().size() < 1 && configurePage.getTotalElements() > 0) {
            pageable = new PageRequest(configurePage.getTotalPages() - 1, conditionQueryWayBill.getPageSize());
            configurePage = pageCondition(conditionQueryWayBill, pageable);
        }
        return configurePage;
    }


    /**
     * @param conditionQueryWayBill
     * @param pageable
     * @return
     * @throws DataException
     */
    private Page<WayBill> pageCondition(final ConditionQueryWayBill conditionQueryWayBill,
                                        Pageable pageable) throws DataException {
        return wayBillRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            //billCode
            if (!StringUtils.isEmpty(conditionQueryWayBill.getWayBillCode())) {
                expressions.add(cb.like(root.<String>get("billCode"), "%" + conditionQueryWayBill.getWayBillCode() + "%"));
            }
            //物流公司名称
            if (!StringUtils.isEmpty(conditionQueryWayBill.getLogisticsCompanyName())) {
                expressions.add(cb.like(root.<String>get("logisticsCompanyName"), "%" + conditionQueryWayBill.getLogisticsCompanyName() + "%"));
            }
            //操作人
            if (!StringUtils.isEmpty(conditionQueryWayBill.getOperatorName())) {
                expressions.add(cb.like(root.<String>get("operatorName"), "%" + conditionQueryWayBill.getOperatorName() + "%"));
            }
            //单据状态
            if (!StringUtils.isEmpty(conditionQueryWayBill.getWayBillStatus())) {
                expressions.add(cb.equal(root.<String>get("billState"), "%" + conditionQueryWayBill.getWayBillStatus() + "%"));
            }
            // 录单时间
            if (conditionQueryWayBill.getCreateTime() != null) {
                //当 开始时间和结束时间 都不为空时 拼接sql
                expressions.add(cb.between(root.<Date>get("createTime"), conditionQueryWayBill.getCreateStartTime(),
                        conditionQueryWayBill.getCreateEndTime()));
            }

            // 发货时间
            if (conditionQueryWayBill.getDeliverTime() != null) {
                //当 开始时间和结束时间 都不为空时 拼接sql
                expressions.add(cb.between(root.<Date>get("createTime"), conditionQueryWayBill.getDeliveryStartTime(),
                        conditionQueryWayBill.getDeliveryEndTime()));
            }

            return predicate;
        }, pageable);


    }

    /**
     * 条件查询
     *
     * @param wayBill
     * @return
     */
    @Override
    public List<WayBill> findByConditions(WayBill wayBill) {


        return wayBillRepository.findAllByCondition((root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            // bill编码
            if (!StringUtils.isEmpty(wayBill.getBillCode())) {
                expressions.add(criteriaBuilder.like(root.get("billCode").as(String.class),
                        "%" + wayBill.getBillCode() + "%"));
            }
            //物流公司名称
            if (!StringUtils.isEmpty(wayBill.getLogisticsCompanyName())) {
                expressions.add(criteriaBuilder.like(root.get("logisticsCompanyName").as(String.class),
                        "%" + wayBill.getLogisticsCompanyName() + "%"));
            }
            //id
            if (wayBill.getBillId() != null) {
                //id
                expressions.add(criteriaBuilder.equal(root.get("id").as(Long.class), wayBill.getBillId()));
            }
            //单据状态
            if (wayBill.getBillState() != null) {

                expressions.add(criteriaBuilder.equal(root.get("billState").as(Long.class), wayBill.getBillState()));
            }
            return predicate;

        });

    }

}
