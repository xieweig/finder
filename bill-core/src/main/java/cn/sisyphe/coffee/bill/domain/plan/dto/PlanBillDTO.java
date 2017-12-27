package cn.sisyphe.coffee.bill.domain.plan.dto;

/**
 * @author ncmao
 * @Date 2017/12/27 14:54
 * @description
 */
public class PlanBillDTO {
    private String billCode;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }
}
