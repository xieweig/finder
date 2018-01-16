package cn.sisyphe.coffee.bill.application.base.purpose;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.base.BillManagerFactory;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAllotStatusEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ncmao
 * 入库单管理器，业务逻辑
 */
@Service
public class InStorageBillManager {

    /**
     * 出库单转换成入库单
     *
     * @param outStorageBill 出库单
     */
    @SuppressWarnings("unchecked")
    public Bill convertInStorageBill(Bill outStorageBill) {
        Bill inBill = generateBill(outStorageBill, BillPurposeEnum.IN_STORAGE);
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
        AbstractBillManager billManager = BillManagerFactory.getManager(bill.getBillType());
        Bill foundBill = billManager.findOneByBillCode(bill.getBillCode());
        billManager.committed(foundBill);
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
    public Bill committing(String billCode, BillTypeEnum inStorageBillType) {
        AbstractBillManager billManager = BillManagerFactory.getManager(inStorageBillType);
        Bill foundBill = billManager.findOneByBillCode(billCode);
        billManager.committing(foundBill);
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
            BillDetail desBillDetail = new BillDetail();
            desBillDetail.setActualAmount(billDetail.getActualAmount());
            desBillDetail.setShippedAmount(billDetail.getShippedAmount());
            desBillDetail.setGoods(billDetail.getGoods());
            desBillDetail.setProgress(billDetail.getProgress());
            details.add(desBillDetail);
        }
        bill.setBillDetails(details);

        return bill;
    }

}
