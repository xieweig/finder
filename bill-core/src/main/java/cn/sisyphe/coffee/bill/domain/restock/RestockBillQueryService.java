package cn.sisyphe.coffee.bill.domain.restock;

import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
import org.springframework.data.domain.Page;

/**
 * @author bifenglin
 */
public interface RestockBillQueryService {

    /**
     * 多条件查询进货单数据
     *
     * @param  conditionQueryRestockBill查询条件
     * @return
     */
    Page<RestockBill> findPageByCondition(ConditionQueryRestockBill conditionQueryRestockBill);

    RestockBill findOneByBillCode(String restockBillCode);

}
