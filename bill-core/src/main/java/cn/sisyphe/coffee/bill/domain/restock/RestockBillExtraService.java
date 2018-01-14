package cn.sisyphe.coffee.bill.domain.restock;

import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
import org.springframework.data.domain.Page;

/**
 * @author bifenglin
 */
public interface RestockBillExtraService extends BillExtraService<RestockBill> {


    /**
     * 多条件查询进货单数据
     *
     * @param conditionQueryRestockBill
     * @return
     */
    Page<RestockBill> findPageByCondition(ConditionQueryRestockBill conditionQueryRestockBill);


}
