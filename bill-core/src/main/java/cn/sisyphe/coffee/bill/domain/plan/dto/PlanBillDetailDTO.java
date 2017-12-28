package cn.sisyphe.coffee.bill.domain.plan.dto;

import java.util.List;

/**
 * @author ncmao
 * @Date 2017/12/28 15:45
 * @description
 */
public class PlanBillDetailDTO {

    private String cargoCode;
    private String rawMaterialCode;
    private List<PlanBillStation> planBillStations;

    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }

    public String getRawMaterialCode() {
        return rawMaterialCode;
    }

    public void setRawMaterialCode(String rawMaterialCode) {
        this.rawMaterialCode = rawMaterialCode;
    }

    public List<PlanBillStation> getPlanBillStations() {
        return planBillStations;
    }

    public void setPlanBillStations(List<PlanBillStation> planBillStations) {
        this.planBillStations = planBillStations;
    }
}
