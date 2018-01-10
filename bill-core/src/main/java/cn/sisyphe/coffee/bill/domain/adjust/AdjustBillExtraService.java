package cn.sisyphe.coffee.bill.domain.adjust;

import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
import org.springframework.data.domain.Page;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：
 * version:
 */
public interface AdjustBillExtraService {

    /**
     * 根据单据编码查询单据信息
     *
     * @param billCode 单据编码
     * @return
     */
    AdjustBill findByBillCode(String billCode);

    /**
     * 多条件查询调拨单信息
     *
     * @param conditionQueryAdjustBill 查询条件
     * @return
     */
    Page<AdjustBill> findByConditions(ConditionQueryAdjustBill conditionQueryAdjustBill);
}
