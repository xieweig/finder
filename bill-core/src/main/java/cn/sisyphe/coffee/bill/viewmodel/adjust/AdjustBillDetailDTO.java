package cn.sisyphe.coffee.bill.viewmodel.adjust;

import cn.sisyphe.coffee.bill.viewmodel.base.BillDetailDTO;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂单据明细DTO
 * version: 1.0
 *
 * @author XiongJing
 */
public class AdjustBillDetailDTO extends BillDetailDTO {

    private String belongMaterialCode;


    public String getBelongMaterialCode() {
        return belongMaterialCode;
    }

    public void setBelongMaterialCode(String belongMaterialCode) {
        this.belongMaterialCode = belongMaterialCode;
    }
}
