package cn.sisyphe.coffee.bill.viewmodel.plan;

import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;

import java.util.List;

/**
 * Created by heyong on 2018/1/17 11:41
 * Description:
 */
public class PlanBillDTO extends BillDTO<PlanBillDetailDTO> {

    private String billName;

    /**
     * 站点站点验证
     */
    private List<String> outStationCodes;


    private List<String> inStationCodes;


    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public List<String> getOutStationCodes() {
        return outStationCodes;
    }

    public void setOutStationCodes(List<String> outStationCodes) {
        this.outStationCodes = outStationCodes;
    }

    public List<String> getInStationCodes() {
        return inStationCodes;
    }

    public void setInStationCodes(List<String> inStationCodes) {
        this.inStationCodes = inStationCodes;
    }
}
