package cn.sisyphe.coffee.bill.domain.adjust;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：
 * version:
 */
public interface AdjustBillExtraService {

    AdjustBill findByBillCode(String billCode);
}
