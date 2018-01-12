package cn.sisyphe.coffee.bill.domain.base.model.enums;

/**
 * 单据状态
 *
 * @author heyong
 */

public enum BillStateEnum {
    /**
     * 已保存
     */
    SAVED,
    /**
     * 已提交
     */
    SUBMITTED,
    /**
     * 审核中
     */
    OPEN,
    /**
     * 审核失败
     */
    AUDIT_FAILURE,
    /**
     * 审核成功
     */
    AUDIT_SUCCESS,

    /**
     * 出库中
     */
    OUT_STORAGING,

    /**
     * 入库中
     */
    IN_STORAGING,
    /**
     * 完成
     */
    DONE,

    /**
     * 未调拨
     */
    UN_ALLOT,

    /**
     * 已调拨
     */
    ALLOT,

    /**
     * 调拨中
     */
    ALLOTING,


}
