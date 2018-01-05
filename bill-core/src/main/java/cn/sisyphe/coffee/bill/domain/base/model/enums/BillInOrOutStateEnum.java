package cn.sisyphe.coffee.bill.domain.base.model.enums;

/**
 * Created by XiongJing on 2018/1/5.
 * remark：单据进出库状态
 * version: 1.0
 *
 * @author XiongJing
 */
public enum BillInOrOutStateEnum {
    /**
     * 未出库
     */
    NOTOUT,
    /**
     * 出库失败
     */
    OUTFAILURE,
    /**
     * 出库成功
     */
    OUTSUCCESS;
}
