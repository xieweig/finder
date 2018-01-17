package cn.sisyphe.coffee.bill.viewmodel.planbill;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDTO;

/**
 * Created by heyong on 2018/1/17 11:41
 * Description:
 */
public class PlanBillDTO extends BillDTO<PlanBillDetailDTO> {

    public PlanBillDTO() {
        setBillType(BillTypeEnum.PLAN);
    }
}
