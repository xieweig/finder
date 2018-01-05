package cn.sisyphe.coffee.bill.domain.base.model.enums;

/**
 * Created by XiongJing on 2018/1/5.
 * remark：单据审核状态枚举
 * version: 1.0
 *
 * @author XiongJing
 */
public enum BillAuditStateEnum {
    /**
     * 未审核
     */
    UN_REVIEWED,
    /**
     * 审核中
     */
    AUDIT_ING,
    /**
     * 审核通过
     */
    AUDIT_SUCCESS,
    /**
     * 审核不通过
     */
    AUDIT_FAILURE;
}
