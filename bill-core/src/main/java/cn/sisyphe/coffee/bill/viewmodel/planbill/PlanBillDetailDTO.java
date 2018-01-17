package cn.sisyphe.coffee.bill.viewmodel.planbill;

import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillStationDTO;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDetailDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heyong on 2018/1/17 11:41
 * Description:
 */
public class PlanBillDetailDTO extends BillDetailDTO {

    private List<PlanBillStationDTO> planBillStationDTOS = new ArrayList<>();

    public List<PlanBillStationDTO> getPlanBillStationDTOS() {
        return planBillStationDTOS;
    }

    public void setPlanBillStationDTOS(List<PlanBillStationDTO> planBillStationDTOS) {
        this.planBillStationDTOS = planBillStationDTOS;
    }
}
