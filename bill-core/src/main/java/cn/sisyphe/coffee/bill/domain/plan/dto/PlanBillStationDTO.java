package cn.sisyphe.coffee.bill.domain.plan.dto;

import cn.sisyphe.coffee.bill.domain.base.model.location.Station;

/**
 * @author ncmao
 * @Date 2017/12/28 15:47
 * @description
 */
public class PlanBillStationDTO {
    private Station inStation;
    private Station outStation;
    private Integer amount;

    public Station getInStation() {
        return inStation;
    }

    public void setInStation(Station inStation) {
        this.inStation = inStation;
    }

    public Station getOutStation() {
        return outStation;
    }

    public void setOutStation(Station outStation) {
        this.outStation = outStation;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
