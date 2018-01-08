package cn.sisyphe.coffee.bill.domain.returned;


import cn.sisyphe.coffee.bill.domain.returned.ReturnedBill;
import cn.sisyphe.coffee.bill.viewmodel.returned.ConditionQueryReturnedBill;
import org.springframework.data.domain.Page;

/**
 * Created by Mayupeng on 2018/01/05.
 * remark：退货单查询服务接口
 * version: 1.0
 *
 * @author Mayupeng
 */
public interface ReturnedBillQueryService {
    ReturnedBill findByBillCode(String billCode);
    /**
     * 多条件查询进货单数据
     *
     * @param  conditionQueryReturnedBill查询条件
     * @return
     */
    Page<ReturnedBill> findPageByCondition(ConditionQueryReturnedBill conditionQueryReturnedBill);
}
