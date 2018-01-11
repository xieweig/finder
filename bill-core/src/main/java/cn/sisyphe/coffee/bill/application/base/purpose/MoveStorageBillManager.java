package cn.sisyphe.coffee.bill.application.base.purpose;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import org.springframework.stereotype.Service;

/**
 * 调拨单管理
 *
 * @author ncmao
 */
@Service
public class MoveStorageBillManager extends InAndMoveManagerContext {


    /**
     * 入库单转换成调拨单
     *
     * @param inStorageBill 入库单
     */
    @SuppressWarnings("unchecked")
    public void convertMoveStorageBill(Bill inStorageBill) {
        Bill moveBill = generateBill(inStorageBill, BillPurposeEnum.moveStorage, bill -> bill.setBillState(BillStateEnum.SAVED));
        AbstractBillManager billManager = getAbstractBillManager(moveBill.getBillType());
        //生成调拨单，未调拨
        billManager.allot(moveBill);
    }

}
