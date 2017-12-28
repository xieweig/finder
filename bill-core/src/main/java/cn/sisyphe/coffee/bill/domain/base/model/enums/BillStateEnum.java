package cn.sisyphe.coffee.bill.domain.base.model.enums;

/**
 * 单据状态
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
    AUDITFAILURE,
    /**
     * 审核成功
     */
    AUDITSUCCESS,
    /**
     * 完成
     */
    DONE,
}
