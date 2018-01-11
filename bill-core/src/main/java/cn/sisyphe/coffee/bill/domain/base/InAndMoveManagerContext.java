package cn.sisyphe.coffee.bill.domain.base;

import ch.lambdaj.function.closure.Switcher;
import cn.sisyphe.coffee.bill.application.adjust.AdjustBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.deliverybill.DeliveryBillManager;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.application.returned.ReturnedBillManager;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**@author ncmao
 * @Date 2018/1/11 11:05
 * @description
 */
@Service
public class InAndMoveManagerContext {

    @Autowired
    private DeliveryBillManager deliveryBillManager;

    @Autowired
    private AdjustBillManager adjustBillManager;

    @Autowired
    private RestockBillManager restockBillManager;

    @Autowired
    private ReturnedBillManager returnedBillManager;


    AbstractBillManager getAbstractBillManager(BillTypeEnum billType) {
        Switcher<AbstractBillManager> managerSwitcher = new Switcher<AbstractBillManager>()
                .addCase(BillTypeEnum.DELIVERY, deliveryBillManager)
                .addCase(BillTypeEnum.ADJUST, adjustBillManager)
                .addCase(BillTypeEnum.RESTOCK, restockBillManager)
                .addCase(BillTypeEnum.RETURNED, returnedBillManager);
        return managerSwitcher.exec(billType);
    }

    /**
     * 生成入库单
     *
     * @param sourceBill
     * @return Bill
     */
    @SuppressWarnings("unchecked")
    Bill generateBill(Bill<BillDetail> sourceBill, BillPurposeEnum billPurpose) {
        Bill<BillDetail> bill = new BillFactory().createBill(sourceBill.getBillType());
        bill.setBillPurpose(billPurpose);
        bill.setSourceCode(sourceBill.getBillCode());
        bill.setRootCode(sourceBill.getRootCode());
        bill.setBelongStationCode(sourceBill.getInLocation().code());
        bill.setInLocation(sourceBill.getInLocation());
        bill.setOutLocation(sourceBill.getOutLocation());
        bill.setPlanMemo(sourceBill.getPlanMemo());
        bill.setOutStorageMemo(sourceBill.getOutStorageMemo());
        bill.setBasicEnum(sourceBill.getBasicEnum());
        bill.setTotalAmount(sourceBill.getTotalAmount());
        bill.setTotalVarietyAmount(sourceBill.getTotalVarietyAmount());
        bill.setBillProperty(sourceBill.getBillProperty());
        Set<BillDetail> details = new HashSet<>();
        for (BillDetail billDetail : sourceBill.getBillDetails()) {
            AdjustBillDetail inAdjustBillDetail = new AdjustBillDetail();
            inAdjustBillDetail.setActualAmount(billDetail.getActualAmount());
            inAdjustBillDetail.setShippedAmount(billDetail.getShippedAmount());
            inAdjustBillDetail.setGoods(billDetail.getGoods());
            inAdjustBillDetail.setAmount(billDetail.getAmount());
            inAdjustBillDetail.setProgress(billDetail.getProgress());
            details.add(inAdjustBillDetail);
        }
        bill.setBillDetails(details);

        return bill;
    }
}
