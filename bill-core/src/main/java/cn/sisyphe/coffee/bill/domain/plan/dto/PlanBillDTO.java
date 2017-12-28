package cn.sisyphe.coffee.bill.domain.plan.dto;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;

/**
 * @author ncmao
 * @Date 2017/12/27 14:54
 * @description
 */
public class PlanBillDTO {
    private String billCode;

    private String billName;

    private BillTypeEnum billType;

    private String memo;

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public BillTypeEnum getBillType() {
        return billType;
    }

    public void setBillType(BillTypeEnum billType) {
        this.billType = billType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
