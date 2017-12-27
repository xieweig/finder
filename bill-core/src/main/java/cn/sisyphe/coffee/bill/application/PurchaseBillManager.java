package cn.sisyphe.coffee.bill.application;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.*;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBillQueryService;
import cn.sisyphe.coffee.bill.infrastructure.purchase.PurchaseBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.ConditionQueryPurchaseBill;
import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单协调层
 * version: 1.0
 *
 * @author XiongJing
 */
@Service
public class PurchaseBillManager {

    @Autowired
    private BillServiceFactory serviceFactory;

    @Autowired
    private PurchaseBillQueryService purchaseBillQueryService;

    @Autowired
    private PurchaseBillRepository purchaseBillRepository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 保存进货单
     *
     * @param purchaseBill
     */
    public void saveBill(PurchaseBill purchaseBill) {
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);

        // 动作调用
        purchaseBillService.dispose(new SaveBehavior());
        // 设置数据库仓库
        purchaseBillService.setBillRepository(purchaseBillRepository);
        // 保存数据到数据库中
        purchaseBillService.save();
        // 发送事件
        purchaseBillService.sendEvent(applicationEventPublisher);
    }

    /**
     * 提交进货单
     *
     * @param purchaseBill
     */
    public void submitBill(PurchaseBill purchaseBill) {
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        purchaseBillService.dispose(new SubmitBehavior());
        purchaseBillService.setBillRepository(purchaseBillRepository);
        purchaseBillService.save();
        // 发送事件
        purchaseBillService.sendEvent(applicationEventPublisher);

    }

    /**
     * 审核中进货单
     *
     * @param purchaseBillCode
     */
    public PurchaseBill auditingBill(String purchaseBillCode) {
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(purchaseBillCode);
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        purchaseBillService.dispose(new AuditingBehavior());
        purchaseBillService.setBillRepository(purchaseBillRepository);
        purchaseBillService.save();
        return purchaseBill;
    }

    /**
     * 审核失败进货单
     *
     * @param purchaseBillCode
     */
    public void auditFailureBill(String purchaseBillCode) {
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(purchaseBillCode);
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        purchaseBillService.dispose(new AuditFailureBehavior());
        purchaseBillService.setBillRepository(purchaseBillRepository);
        purchaseBillService.save();
        // 发送事件
        purchaseBillService.sendEvent(applicationEventPublisher);
    }

    /**
     * 审核成功进货单
     *
     * @param purchaseBillCode
     */
    public void AuditSuccessBill(String purchaseBillCode) {
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(purchaseBillCode);
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        purchaseBillService.dispose(new AuditSuccessBehavior());
        purchaseBillService.dispose(new PurposeBehavior());
        purchaseBillService.setBillRepository(purchaseBillRepository);
        purchaseBillService.save();
        // 发送事件
        purchaseBillService.sendEvent(applicationEventPublisher);
    }

    /**
     * 单据出入库完成
     *
     * @param responseResult
     */
    public void DoneBill(ResponseResult responseResult) {
        Map<String, Object> resultMap = responseResult.getResult();
        PurchaseBill bill = responseResult.toClassObject(resultMap.get("bill"), PurchaseBill.class);
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(bill.getBillCode());
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        purchaseBillService.dispose(new PurposeBehavior());
        purchaseBillService.setBillRepository(purchaseBillRepository);
        purchaseBillService.save();
        // 发送事件
        purchaseBillService.sendEvent(applicationEventPublisher);
    }

    /**
     * 根据多条件查询进货单据信息
     *
     * @param conditionQueryPurchaseBill 查询条件
     * @return
     */
    public Page<PurchaseBill> findByConditions(ConditionQueryPurchaseBill conditionQueryPurchaseBill) {
        return purchaseBillQueryService.findByConditions(conditionQueryPurchaseBill);
    }

}
