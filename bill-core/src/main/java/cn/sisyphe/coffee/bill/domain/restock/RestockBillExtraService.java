package cn.sisyphe.coffee.bill.domain.restock;

import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
import org.springframework.data.domain.Page;

/**
 * @author bifenglin
 */
public interface RestockBillExtraService {

    /**
     * 根据
     *
     * @param  conditionQueryRestockBill查询条件
     * @return
     */
    RestockBill findByBillCode(String billCode);
    /**
     * 多条件查询进货单数据
     *
     * @param  conditionQueryRestockBill查询条件
     * @return
     */
    Page<RestockBill> findPageByCondition(ConditionQueryRestockBill conditionQueryRestockBill);
    /**
     * 根据源单号查询退库单据
     *
     * @param  conditionQueryRestockBill查询条件
     * @return
     */
    RestockBill findBySourceCode(String sourceCode);
}
