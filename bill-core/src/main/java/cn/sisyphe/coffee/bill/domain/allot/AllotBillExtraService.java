package cn.sisyphe.coffee.bill.domain.allot;

import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import org.springframework.data.domain.Page;

/**
 * @author bifenglin
 */
public interface AllotBillExtraService extends BillExtraService<AllotBill> {

    /**
     * 多条件分页查询
     * @param conditionQueryAllotBill
     * @return
     */
    Page<AllotBill> findPageByCondition(ConditionQueryAllotBill conditionQueryAllotBill);

}
