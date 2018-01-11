package cn.sisyphe.coffee.bill.application.base;

import ch.lambdaj.function.closure.Switcher;
import cn.sisyphe.coffee.bill.application.adjust.AdjustBillManager;
import cn.sisyphe.coffee.bill.application.delivery.DeliveryBillManager;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.application.returned.ReturnedBillManager;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBillDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ncmao
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
            BillDetail desBillDetail = createBillDetail(sourceBill.getBillType());
            desBillDetail.setActualAmount(billDetail.getActualAmount());
            desBillDetail.setShippedAmount(billDetail.getShippedAmount());
            desBillDetail.setGoods(billDetail.getGoods());
            desBillDetail.setAmount(billDetail.getAmount());
            desBillDetail.setProgress(billDetail.getProgress());
            details.add(desBillDetail);
        }
        bill.setBillDetails(details);

        return bill;
    }

    private BillDetail createBillDetail(BillTypeEnum billType) {
        if (BillTypeEnum.DELIVERY.equals(billType)) {
            return new DeliveryBillDetail();
        }
        if (BillTypeEnum.ADJUST.equals(billType)) {
            return new AdjustBillDetail();
        }
        if (BillTypeEnum.RESTOCK.equals(billType)) {
            return new RestockBillDetail();
        }
        if (BillTypeEnum.RETURNED.equals(billType)) {
            return new ReturnedBillDetail();
        }
        return null;
    }
}
