package cn.sisyphe.coffee.bill.domain.base.model.enums;

/**
 * Created by heyong on 2017/12/19 17:22
 * Description: 单据种类
 *
 * @author heyong
 */
public enum BillTypeEnum {

     /**
     * 计划
     */
    PLAN,
    /**
     * 进货
     */
    PURCHASE,
    /**
     * 配送
     */
    DELIVERY,
    /**
     * 调剂
     */
    ADJUST,
    /**
     * 退库
     */
    RESTOCK,
    /**
     * 运单
     */
    TRANSMIT,
    /**
     * 退货
     */
    RETURNED,

    /**
     * 调拨
      */
    ALLOT,
    /**
     * 流转误差
     */
    TRANSFER_MISTAKE;
}
