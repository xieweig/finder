package cn.sisyphe.coffee.bill.viewmodel.plan;

import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;

/**
 * Created by heyong on 2018/1/17 11:41
 * Description:
 */
public class PlanBillDTO extends BillDTO<PlanBillDetailDTO> {

    private String billName;

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }
}
