package cn.sisyphe.coffee.bill.viewmodel.inoutself;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;

/**
 * @date: 2018/1/19
 * @description: 其它出入库DTO
 * @author：mayupeng
 */
public class InOutSelfBillDTO extends BillDTO<InOutSelfBillDetailDTO> {
    public InOutSelfBillDTO() {
        setBillType(BillTypeEnum.IN_OUT_SELF_BILL);
    }
}

