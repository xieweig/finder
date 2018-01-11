package cn.sisyphe.coffee.bill.application.base;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
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
     * @param inStorageBill 出库单
     */
    @SuppressWarnings("unchecked")
    public void convertMoveStorageBill(Bill inStorageBill) {
        Bill moveBill = generateBill(inStorageBill, BillPurposeEnum.moveStorage);
        AbstractBillManager billManager = getAbstractBillManager(moveBill.getBillType());
        billManager.save(moveBill);
    }

}
