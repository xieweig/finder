package cn.sisyphe.coffee.bill.application.base.purpose;

import ch.lambdaj.function.closure.Switcher;
import cn.sisyphe.coffee.bill.application.adjust.AdjustBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.base.BillManagerFactory;
import cn.sisyphe.coffee.bill.application.delivery.DeliveryBillManager;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.application.returned.ReturnedBillManager;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAllotStatusEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
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
    public Bill convertInStorageBill(Bill outStorageBill) {
        Bill inBill = generateBill(outStorageBill, BillPurposeEnum.InStorage);
        inBill.setBillState(BillStateEnum.AUDIT_SUCCESS);
        AbstractBillManager billManager = BillManagerFactory.getManager(inBill.getBillType());
        billManager.purpose(inBill);
        return inBill;
    }

    /**
     * 更新入库单状态为已调拨
     *
     * @param bill 入库单
     * @return
     */
    @SuppressWarnings("unchecked")
    public Bill allotedForInStorageBill(Bill bill) {
        AbstractBillManager abstractBillManager = BillManagerFactory.getManager(bill.getBillType());
        Bill foundBill = abstractBillManager.findEntityByBillCode(bill.getBillCode());
        abstractBillManager.committed(foundBill);
        return foundBill;
    }

    /**
     * 更新入库单状态为调拨中
     *
     * @param billCode          入库单
     * @param inStorageBillType
     * @return
     */
    @SuppressWarnings("unchecked")
    public Bill commiting(String billCode, BillTypeEnum inStorageBillType) {
        AbstractBillManager abstractBillManager = BillManagerFactory.getManager(inStorageBillType);
        Bill foundBill = abstractBillManager.findEntityByBillCode(billCode);
        abstractBillManager.committing(foundBill);
        return foundBill;
    }
    /**
     * 生成入库单
     *
     * @param sourceBill
     * @return Bill
     */
    @SuppressWarnings("unchecked")
    private Bill generateBill(Bill<BillDetail> sourceBill, BillPurposeEnum billPurpose) {
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
        bill.setTotalPrice(sourceBill.getTotalPrice());
        bill.setAllotStatus(BillAllotStatusEnum.NOT_ALLOT);
        bill.setProgress(sourceBill.getProgress());
        bill.setOperatorCode(sourceBill.getOperatorCode());
        Set<BillDetail> details = new HashSet<>();
        for (BillDetail billDetail : sourceBill.getBillDetails()) {
            BillDetail desBillDetail = createBillDetail(sourceBill.getBillType());
            desBillDetail.setActualAmount(billDetail.getActualAmount());
            desBillDetail.setShippedAmount(billDetail.getShippedAmount());
            desBillDetail.setGoods(billDetail.getGoods());
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
