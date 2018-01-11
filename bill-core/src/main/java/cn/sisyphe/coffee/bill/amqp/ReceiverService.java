package cn.sisyphe.coffee.bill.amqp;

import ch.lambdaj.function.closure.Switcher;
import cn.sisyphe.coffee.bill.application.adjust.AdjustBillManager;
import cn.sisyphe.coffee.bill.application.base.AbstractBillManager;
import cn.sisyphe.coffee.bill.application.deliverybill.DeliveryBillManager;
import cn.sisyphe.coffee.bill.application.purchase.PurchaseBillManager;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.application.returned.ReturnedBillManager;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.util.Constant;
import cn.sisyphe.framework.web.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Hypier on 2016/10/9.
 * 消息队列接收服务
 */
@Component
public class ReceiverService {

    @Autowired
    private PurchaseBillManager purchaseBillManager;

    @Autowired
    private DeliveryBillManager deliveryBillManager;

    @Autowired
    private AdjustBillManager adjustBillManager;

    @Autowired
    private RestockBillManager restockBillManager;

    @Autowired
    private ReturnedBillManager returnedBillManager;
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(ReceiverService.class);

    /**
     * 接收库存系统冲减后的单据消息
     *
     * @param responseResult
     */
    @RabbitListener(queues = "#{'${rabbit.listener.queue}'}")
    public void receiveQueue(ResponseResult responseResult) {

        if (Constant.COMMON_NAME.equals(responseResult.getCommandName())) {
            log.info("接收到库存系统冲减后的单据消息：", responseResult.getResult());
            purchaseBillManager.doneBill(responseResult);
            return;
        }
        Bill bill = responseResult.toClassObject(responseResult.getResult().get("bill"), Bill.class);
        Bill inBill = generateInStorageBill(bill);
        AbstractBillManager billManager = getBillManager(inBill.getBillType());
        billManager.save(inBill);
    }

    /**
     * 生成入库单
     *
     * @param sourceBill
     * @return Bill
     */
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
