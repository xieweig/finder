package cn.sisyphe.coffee.bill.domain.allot;

import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import org.springframework.data.domain.Page;

/**
 * @author bifenglin
 */
public interface AllotBillExtraService {

    /**
     * 多条件分页查询
     * @param conditionQueryAllotBill
     * @return
     */
    Page<AllotBill> findPageByCondition(ConditionQueryPlanBill conditionQueryAllotBill);
}
