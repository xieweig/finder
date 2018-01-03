package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.base.model.db.DbGoods;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.viewmodel.BaseConditionQuery;

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
     *
     *notes :ref BaseBill
     *   录单日期起始
     */
    private Date createTime;
    /**
     *
     *notes :ref BaseBill
     *   录单日期终止
     */

    private Date updateTime;
    /**
     *
     *notes :ref RestockBill
     *  退库 出库单 起始终止时间
     */
    private Date outStorageStartTime;
    private Date outStorageEndTime;
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

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getInStationCode() {
        return inStationCode;
    }

    public void setInStationCode(String inStationCode) {
        this.inStationCode = inStationCode;
    }

    public String getOutStationCode() {
        return outStationCode;
    }

    public void setOutStationCode(String outStationCode) {
        this.outStationCode = outStationCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getOutStorageStartTime() {
        return outStorageStartTime;
    }

    public void setOutStorageStartTime(Date outStorageStartTime) {
        this.outStorageStartTime = outStorageStartTime;
    }

    public Date getOutStorageEndTime() {
        return outStorageEndTime;
    }

    public void setOutStorageEndTime(Date outStorageEndTime) {
        this.outStorageEndTime = outStorageEndTime;
    }

    public BillStateEnum getBillStateEnum() {
        return billStateEnum;
    }

    public void setBillStateEnum(BillStateEnum billStateEnum) {
        this.billStateEnum = billStateEnum;
    }

    public BillStateEnum getBillStateEnum2() {
        return billStateEnum2;
    }

    public void setBillStateEnum2(BillStateEnum billStateEnum2) {
        this.billStateEnum2 = billStateEnum2;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public DbGoods getDbGoods() {
        return dbGoods;
    }

    public void setDbGoods(DbGoods dbGoods) {
        this.dbGoods = dbGoods;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
