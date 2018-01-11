package cn.sisyphe.coffee.bill.application.base.purpose;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import org.springframework.stereotype.Service;

/**
 * @author ncmao
 * 入库单管理器，业务逻辑
 */
@Service
public class InStorageBillManager extends InAndMoveManagerContext {

    /**
     * 出库单转换成入库单
     *
     * @param outStorageBill 出库单
     */
    @SuppressWarnings("unchecked")
    public void convertInStorageBill(Bill outStorageBill) {
        Bill inBill = generateBill(outStorageBill, BillPurposeEnum.InStorage, bill -> bill.setBillState(BillStateEnum.SAVED));
        AbstractBillManager billManager = getAbstractBillManager(inBill.getBillType());
        billManager.unAllot(inBill);
    }
}
