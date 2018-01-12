package cn.sisyphe.coffee.bill.domain.allot;

import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
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
    Page<AllotBill> findPageByCondition(ConditionQueryAllotBill conditionQueryAllotBill);
    /**
     * 根据单据单号查询调拨单
     * @param conditionQueryAllotBill
     * @return
     */
    AllotBill findOneByBillCode(String billCode);
}
