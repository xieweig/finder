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
    NOT_OUT,
    /**
     * 未入库
     */
    NOT_IN,
    /**
     * 入库中
     */
    IN_ING,
    /**
     * 出库中
     */
    OUT_ING,
    /**
     * 出库失败
     */
    OUT_FAILURE,
    /**
     * 出库成功
     */
    OUT_SUCCESS,
    /**
     * 入库失败
     */
    IN_FAILURE,
    /**
     * 入库成功
     */
    IN_SUCCESS;
}
