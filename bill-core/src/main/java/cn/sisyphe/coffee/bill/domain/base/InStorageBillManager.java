package cn.sisyphe.coffee.bill.domain.base;

import ch.lambdaj.function.closure.Switcher;
import cn.sisyphe.coffee.bill.application.adjust.AdjustBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.delivery.DeliveryBillManager;
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

/**
 * 入库单管理器，业务逻辑
 */
@Service
public class InStorageBillManager {

    @Autowired
    private DeliveryBillManager deliveryBillManager;

    @Autowired
    private AdjustBillManager adjustBillManager;

    @Autowired
    private RestockBillManager restockBillManager;

    @Autowired
    private ReturnedBillManager returnedBillManager;


    /**
     * 出库单转换成入库单
     *
     * @param outStorageBill 出库单
     */
    @SuppressWarnings("unchecked")
    public void convertInStorageBill(Bill outStorageBill) {
        Bill inBill = generateInStorageBill(outStorageBill);
        AbstractBillManager billManager = getBillManager(inBill.getBillType());
        billManager.save(inBill);
    }

    /**
     * 生成入库单
     *
     * @param sourceBill
     * @return Bill
     */
    @SuppressWarnings("unchecked")
    private Bill generateInStorageBill(Bill<BillDetail> sourceBill) {
        Bill<BillDetail> inBill = new BillFactory().createBill(sourceBill.getBillType());
        inBill.setBillPurpose(BillPurposeEnum.InStorage);
        inBill.setSourceCode(sourceBill.getBillCode());
        inBill.setRootCode(sourceBill.getRootCode());
        inBill.setBelongStationCode(sourceBill.getInLocation().code());
        inBill.setInLocation(sourceBill.getInLocation());
        inBill.setOutLocation(sourceBill.getOutLocation());
        inBill.setPlanMemo(sourceBill.getPlanMemo());
        inBill.setOutStorageMemo(sourceBill.getOutStorageMemo());
        inBill.setBasicEnum(sourceBill.getBasicEnum());
        inBill.setTotalAmount(sourceBill.getTotalAmount());
        inBill.setTotalVarietyAmount(sourceBill.getTotalVarietyAmount());
        inBill.setBillProperty(sourceBill.getBillProperty());
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
        inBill.setBillDetails(details);

        return inBill;
    }

    private AbstractBillManager getBillManager(BillTypeEnum billType) {
        Switcher<AbstractBillManager> managerSwitcher = new Switcher<AbstractBillManager>()
                .addCase(BillTypeEnum.DELIVERY, deliveryBillManager)
                .addCase(BillTypeEnum.ADJUST, adjustBillManager)
                .addCase(BillTypeEnum.RESTOCK, restockBillManager)
                .addCase(BillTypeEnum.RETURNED, returnedBillManager);
        return managerSwitcher.exec(billType);
    }
}
