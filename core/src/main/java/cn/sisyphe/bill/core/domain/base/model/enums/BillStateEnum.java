package cn.sisyphe.bill.core.domain.base.model.enums;

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
    AUDITING,

    /**
     * 审核失败
     */
    AUDITFAILURE,

    /**
     * 审核成功
     */
    AUDITSUCCESS,
    /**
     * 出入库完成
     */
    DONE,
}
