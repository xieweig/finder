package cn.sisyphe.coffee.bill.application.adjust;

import cn.sisyphe.coffee.bill.domain.adjust.AdjustBill;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillDetail;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调拨单据协调层
 * version: 1.0
 *
 * @author XiongJing
 */
@Service
public class AdjustBillEventProcessor {
    @Autowired
    private AdjustBillManager adjustBillManager;


    public static final String ADJUST_IN_STORAGE_PREFIX = "tjrkd";

    /**
     * 已创建事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ADJUST' and #event.billState.toString() == 'SAVED'")
    public void billSave(BehaviorEvent event) {
        System.err.println("SAVED:" + event.getBill());
    }

    /**
     * 已提交事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ADJUST' and #event.billState.toString() == 'SUBMITTED'")
    public void billSubmit(BehaviorEvent event) {
        System.err.println("SUBMITTED:" + event.getBill());
    }

    /**
     * 审核失败事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ADJUST' and #event.billState.toString() == 'AUDIT_FAILURE'")
    public void billFailure(BehaviorEvent event) {
        System.err.println("AUDIT_FAILURE:" + event.getBill());
    }

    /**
     * 审核成功事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ADJUST' and #event.billState.toString() == 'AUDIT_SUCCESS'")
    public void billSuccess(BehaviorEvent event) {
        System.err.println("AUDIT_SUCCESS:" + event.getBill());
        adjustBillManager.purpose((AdjustBill) event.getBill());
    }

    /**
     * 冲减完成事件
     *
     * @param event
     */
    @EventListener(condition = "#event.billType.toString() ==  'ADJUST' and #event.billState.toString() == 'DONE'")
    public void billDone(BehaviorEvent event) {
        //冲减完成之后需要生成调剂入库单
        AdjustBill adjustBill = (AdjustBill) event.getBill();
        AdjustBill inAdjustBill = generateInStorageBill(adjustBill);
        adjustBillManager.save(inAdjustBill);
        System.out.println("DONE:" + adjustBill);
    }

    // TODO: 2018/1/9 临时单据编码
    private String generateBillCode() {
        return ADJUST_IN_STORAGE_PREFIX + String.valueOf(System.currentTimeMillis());
    }

    private AdjustBill generateInStorageBill(AdjustBill adjustBill) {
        AdjustBill inAdjustBill = (AdjustBill) new BillFactory().createBill(BillTypeEnum.ADJUST);
        inAdjustBill.setBillPurpose(BillPurposeEnum.InStorage);
        inAdjustBill.setBillCode(generateBillCode());
        inAdjustBill.setSourceCode(adjustBill.getBillCode());
        inAdjustBill.setRootCode(adjustBill.getRootCode());
        inAdjustBill.setBelongStationCode(adjustBill.getInLocation().code());
        inAdjustBill.setInLocation(adjustBill.getInLocation());
        inAdjustBill.setOutLocation(adjustBill.getOutLocation());
        inAdjustBill.setPlanMemo(adjustBill.getPlanMemo());
        inAdjustBill.setOutStorageMemo(adjustBill.getOutStorageMemo());
        inAdjustBill.setBasicEnum(adjustBill.getBasicEnum());
        inAdjustBill.setAdjustNumber(adjustBill.getAdjustNumber());
        inAdjustBill.setVarietyNumber(adjustBill.getVarietyNumber());
        inAdjustBill.setBillProperty(adjustBill.getBillProperty());
        Set<AdjustBillDetail> details = new HashSet<>();
        for (AdjustBillDetail adjustBillDetail : adjustBill.getBillDetails()) {
            AdjustBillDetail inAdjustBillDetail = new AdjustBillDetail();
            inAdjustBillDetail.setMemo(adjustBillDetail.getMemo());
            inAdjustBillDetail.setBelongMaterialCode(adjustBillDetail.getBelongMaterialCode());
            inAdjustBillDetail.setActualAmount(adjustBillDetail.getActualAmount());
            inAdjustBillDetail.setShippedAmount(adjustBillDetail.getShippedAmount());
            inAdjustBillDetail.setGoods(adjustBillDetail.getGoods());
            inAdjustBillDetail.setAmount(adjustBillDetail.getAmount());
            inAdjustBillDetail.setProgress(adjustBillDetail.getProgress());
            details.add(inAdjustBillDetail);
        }
        inAdjustBill.setBillDetails(details);

        return inAdjustBill;
    }

}
