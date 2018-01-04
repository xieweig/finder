package cn.sisyphe.coffee.bill.domain.restock;

import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
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
public class RestockBillQueryServiceImpl implements RestockBillQueryService{

    @Autowired
    private RestockBillRepository restockBillRepository;


    @Override
    public Page<RestockBill> findPageByCondition(ConditionQueryRestockBill conditionQueryRestockBill) {
        // 组装页面
        Pageable pageable = new PageRequest(conditionQueryRestockBill.getPage() - 1, conditionQueryRestockBill.getPageSize());

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


//            SetJoin<PlanBill, Long> planBillLongSetJoin = root.join(root.getModel().getSet(""));
           /* //分组查询
            query.groupBy(root.get("billCode"));
            //*/
            return predicate;
        }, pageable);
    }
}
