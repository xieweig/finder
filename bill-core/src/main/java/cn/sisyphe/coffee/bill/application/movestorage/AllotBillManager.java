package cn.sisyphe.coffee.bill.application.movestorage;

import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.domain.allot.AllotBill;
import cn.sisyphe.coffee.bill.domain.allot.AllotBillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 调拨单管理
 *
 * @author ncmao
 */
@Service
public class AllotBillManager extends AbstractBillManager<AllotBill> {


    @Autowired
    public AllotBillManager(BillRepository<AllotBill> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }


    /**
     * 生成入库单
     *
     * @param sourceBill
     * @return Bill
     */
    @SuppressWarnings("unchecked")
    private AllotBill generateBill(Bill<BillDetail> sourceBill, BillPurposeEnum billPurpose) {
        AllotBill allotBill = (AllotBill) new BillFactory().createBill(BillTypeEnum.ALLOT);
        allotBill.setBillPurpose(billPurpose);
        allotBill.setSourceCode(sourceBill.getBillCode());
        allotBill.setRootCode(sourceBill.getRootCode());
        allotBill.setBelongStationCode(sourceBill.getInLocation().code());
        allotBill.setSpecificBillType(sourceBill.getBillType());
        allotBill.setInLocation(sourceBill.getInLocation());
        allotBill.setOutLocation(sourceBill.getOutLocation());
        allotBill.setPlanMemo(sourceBill.getPlanMemo());
        allotBill.setOutStorageMemo(sourceBill.getOutStorageMemo());
        allotBill.setBasicEnum(sourceBill.getBasicEnum());
        allotBill.setTotalAmount(sourceBill.getTotalAmount());
        allotBill.setTotalVarietyAmount(sourceBill.getTotalVarietyAmount());
        allotBill.setBillProperty(sourceBill.getBillProperty());
        Set<AllotBillDetail> details = new HashSet<>();
        for (BillDetail billDetail : allotBill.getBillDetails()) {
            AllotBillDetail allotBillDetail = new AllotBillDetail();
            allotBillDetail.setActualAmount(billDetail.getActualAmount());
            allotBillDetail.setShippedAmount(billDetail.getShippedAmount());
            allotBillDetail.setGoods(billDetail.getGoods());
            details.add(allotBillDetail);
        }
        allotBill.setBillDetails(details);

        return allotBill;
    }
}

