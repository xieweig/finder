package cn.sisyphe.coffee.bill.domain.plan.dto;

/**
 * @author ncmao
 * @Date 2017/12/28 15:47
 * @description
 */
public class PlanBillStation {
    private String inStationCode;
    private String outStationCode;
    private Integer amount;


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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
