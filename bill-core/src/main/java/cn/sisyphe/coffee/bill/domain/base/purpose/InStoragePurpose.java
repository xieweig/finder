package cn.sisyphe.coffee.bill.domain.base.purpose;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAllotStatusEnum;

/**
 * Created by heyong on 2017/12/19 14:07
 * Description: 入库单用途处理器
 *
 * @author heyong
 */
public class InStoragePurpose extends AbstractBillPurpose {

    /**
     * 用途处理器
     */
    @Override
    public void handle() {
        Bill bill = getBillService().getBill();
        bill.setAllotStatus(BillAllotStatusEnum.NOT_ALLOT);
    }
}
