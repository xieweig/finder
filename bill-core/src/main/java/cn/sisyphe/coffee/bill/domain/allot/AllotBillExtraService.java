package cn.sisyphe.coffee.bill.domain.allot;

import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import org.springframework.data.domain.Page;

public interface AllotBillExtraService {
    Page<AllotBill> findPageByCondition(ConditionQueryPlanBill conditionQueryAllotBill);
}
