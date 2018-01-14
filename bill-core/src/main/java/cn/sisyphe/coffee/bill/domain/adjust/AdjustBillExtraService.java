package cn.sisyphe.coffee.bill.domain.adjust;

import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
import org.springframework.data.domain.Page;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂业务逻辑接口
 * version: 1.0
 *
 * @author XiongJing
 */
public interface AdjustBillExtraService extends BillExtraService<AdjustBill> {

    /**
     * 多条件查询调拨单信息
     *
     * @param conditionQueryAdjustBill 查询条件
     * @return
     */
    Page<AdjustBill> findByConditions(ConditionQueryAdjustBill conditionQueryAdjustBill);
}
