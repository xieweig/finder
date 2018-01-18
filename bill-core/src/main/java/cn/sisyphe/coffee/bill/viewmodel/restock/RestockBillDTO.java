package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;
import cn.sisyphe.coffee.bill.viewmodel.purchase.PurchaseBillDetailDTO;

/**
 * Created by heyong on 2018/1/17 11:41
 * Description:
 */
public class RestockBillDTO extends BillDTO<RestockBillDetailDTO> {

    public RestockBillDTO() {
        setBillType(BillTypeEnum.RESTOCK);
    }
}
