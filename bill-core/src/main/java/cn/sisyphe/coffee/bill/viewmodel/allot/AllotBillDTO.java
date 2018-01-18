package cn.sisyphe.coffee.bill.viewmodel.allot;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;

public class AllotBillDTO extends BillDTO<AllotBillDetailDTO> {

    public AllotBillDTO() {
        setBillType(BillTypeEnum.ALLOT);
    }
}
