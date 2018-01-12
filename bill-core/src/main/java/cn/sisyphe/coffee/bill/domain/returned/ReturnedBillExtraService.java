package cn.sisyphe.coffee.bill.domain.returned;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.viewmodel.returned.ConditionQueryReturnedBill;
import org.springframework.data.domain.Page;

/**
 * @author bifenglin
 */
public interface ReturnedBillExtraService {

    /**
     * 根据
     *
     * @param  conditionQueryReturnedBill查询条件
     * @return
     */
    ReturnedBill findByBillCode(String billCode);
    /**
     * 多条件查询进货单数据
     *
     * @param  conditionQueryReturnedBill查询条件
     * @return
     */
    Page<ReturnedBill> findPageByCondition(ConditionQueryReturnedBill conditionQueryReturnedBill);
    /**
     * 根据源单号查询退库单据
     *
     * @param  conditionQueryReturnedBill查询条件
     * @return
     */
    ReturnedBill findBySourceCode(String sourceCode);

}
