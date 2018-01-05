package cn.sisyphe.coffee.bill.domain.delivery;

import cn.sisyphe.coffee.bill.infrastructure.delivery.DeliveryBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.ConditionQueryDeliveryBill;
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
 * 查询配送单
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/4 11:08
 **/
@Service
public class DeliveryBillQueryServiceImpl implements DeliveryBillQueryService {


    @Autowired
    private DeliveryBillRepository deliveryBillRepository;


    /**
     * 根据单号查询单个配送单
     *
     * @param billCode
     * @return
     */
    @Override
    public DeliveryBill findOneByBillCode(String billCode) {

        return deliveryBillRepository.findOneByBillCode(billCode);
    }


    /**
     * 多条件分页查询配送计划单
     *
     * @param conditionQuery
     * @return
     */
    @Override
    public Page<DeliveryBill> findPageByCondition(ConditionQueryDeliveryBill conditionQuery) throws DataException {

        Pageable pageable = new PageRequest(conditionQuery.getPage() - 1, conditionQuery.getPageSize());
        Page<DeliveryBill> configurePage = pageCondition(conditionQuery, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (configurePage.getContent().size() < 1 && configurePage.getTotalElements() > 0) {
            pageable = new PageRequest(configurePage.getTotalPages() - 1, conditionQuery.getPageSize());
            configurePage = pageCondition(conditionQuery, pageable);
        }
        return configurePage;

    }

    /**
     * 拼接查询条件
     *
     * @param conditionQuery
     * @param pageable
     * @return
     */
    private Page<DeliveryBill> pageCondition(ConditionQueryDeliveryBill conditionQuery, Pageable pageable)
            throws DataException {
        return deliveryBillRepository.findAll((root, query, cb) -> {

//          query.distinct(true);
            Predicate predicate = cb.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();

            /**
             * 出库单编码
             */
            if (!StringUtils.isEmpty(conditionQuery.getBillCode())) {
                expressions.add(cb.like(root.get("billCode").as(String.class), conditionQuery.getBillCode()));
            }
            /**
             * 录单人
             */
            if (conditionQuery.getOperatorCodeList() != null
                    && conditionQuery.getOperatorCodeList().size() > 0) {
                expressions.add(root.get("operatorCode").as(String.class).in(conditionQuery.getOperatorCodeList()));
            }

            /**
             *  录单时间
             */
            if (conditionQuery.getCreateStartTime() != null &&
                    conditionQuery.getCreateEndTime() != null) {
                //当 开始时间和结束时间 都不为空时 拼接sql
                expressions.add(cb.between(root.<Date>get("createTime"), conditionQuery.getCreateStartTime(),
                        conditionQuery.getCreateEndTime()));
            }
            /**
             * 出库开始时间
             */
            if (conditionQuery.getOutStockStartTime() != null &&
                    conditionQuery.getOutStockEndTime() != null) {
                //当 开始时间和结束时间 都不为空时 拼接sql
                expressions.add(cb.between(root.<Date>get("outStockTime"), conditionQuery.getOutStockStartTime(),
                        conditionQuery.getOutStockEndTime()));
            }
            /**
             * 拼接状态
             */
            if (!StringUtils.isEmpty(conditionQuery.getStatusCode())) {
                expressions.add(cb.equal(root.get("billState").as(String.class), conditionQuery.getStatusCode()));
            }
            return predicate;
        }, pageable);

    }


}
