package cn.sisyphe.coffee.bill.domain.restock;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.restock.enums.BasicEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ncmao
 * @Date 2017/12/27 16:10
 * @description 退库计划单
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class RestockBill extends Bill<RestockBillDetail> {
    public RestockBill() {
        setBillType(BillTypeEnum.RESTOCK);
    }

    /**
     * 按货物还是按原料
     */
    private BasicEnum basicEnum;

    /**
     * 退货数量
     */
    private int amount;

    /**
     * 退货品种数
     */
    private int species;

    /**
     * 出库备注
     */
    private String outMemo;

    /**
     * 入库备注
     */
    private String planMemo;

    /**
     * 审核意见
     */
    private String auditMemo;

    /**
     * 入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inWareHouseTime;

    public BasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(BasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }

    public String getOutMemo() {
        return outMemo;
    }

    public void setOutMemo(String outMemo) {
        this.outMemo = outMemo;
    }

    public String getPlanMemo() {
        return planMemo;
    }

    public void setPlanMemo(String planMemo) {
        this.planMemo = planMemo;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    public Date getInWareHouseTime() {
        return inWareHouseTime;
    }

    public void setInWareHouseTime(Date inWareHouseTime) {
        this.inWareHouseTime = inWareHouseTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSpecies() {
        return species;
    }

    public void setSpecies(int species) {
        this.species = species;
    }
}
