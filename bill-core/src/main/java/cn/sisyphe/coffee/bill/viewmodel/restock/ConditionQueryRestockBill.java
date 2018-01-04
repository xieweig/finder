package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.base.model.db.DbGoods;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.viewmodel.BaseConditionQuery;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *@date: 2018/1/2
 *@description: 
 *@author：xieweiguang
 */

public class ConditionQueryRestockBill extends BaseConditionQuery implements Serializable {
    /**
     *
     *notes :
     *  操作人员 ref Bill
     */
    private String operatorCode;
    /**
     *
     *notes :
     *  单据编号 ref Bill
     */
    private String billCode;
    /**
     *
     *notes : ref Bill.DBStation
     *  入库站点
     */
    private String inStationCode;
    /**
     *
     *notes : ref Bill.DBStation
     *  出库站点
     */
    private String outStationCode;
    /**
     * 录单开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createStartTime;
    /**
     * 录单结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createEndTime;
    /**
     * 入库开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inStartTime;
    /**
     * 入库结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inEndTime;
    /**
     *
     *notes :ref Bill
     *  单据状态 only distinct 已提交 未提交
     */
    private BillStateEnum billStateEnum;

    /**
     *
     *notes :ref Bill
     *  审核状态 only distinct 审核中 审核通过 审核未通过
     */
    private BillStateEnum billStateEnum2;
    /**
     *
     *notes :ref BillDetail
     *  配送数量
     */
    private int number;
    /**
     *
     *notes :暂时理解为goods  ref BillDetail.DBGoods
     *  配送品种
     */
    private DbGoods dbGoods;
    /**
     *
     *notes :ref RestockBill
     *  配送总价
     */
    private int totalPrice;


}
