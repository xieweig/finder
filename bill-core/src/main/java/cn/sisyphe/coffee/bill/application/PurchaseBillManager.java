package cn.sisyphe.coffee.bill.application;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.*;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBillQueryService;
import cn.sisyphe.coffee.bill.viewmodel.ConditionQueryPurchaseBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
    BillServiceFactory serviceFactory;

    @Autowired
    PurchaseBillQueryService purchaseBillQueryService;

    /**
     * 保存进货单
     *
     * @param purchaseBill
     */
    public void saveBill(PurchaseBill purchaseBill) {
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        SaveBehavior saveBehavior = new SaveBehavior();
        purchaseBillService.dispose(saveBehavior);
    }

    /**
     * 提交进货单
     *
     * @param purchaseBill
     */
    public void submitBill(PurchaseBill purchaseBill) {
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        SubmitBehavior submitBehavior = new SubmitBehavior();
        purchaseBillService.dispose(submitBehavior);
    }

    /**
     * 审核中进货单
     *
     * @param purchaseBillCode
     */
    public PurchaseBill auditingBill(String purchaseBillCode) {
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(purchaseBillCode);
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        AuditingBehavior auditingBehavior = new AuditingBehavior();
        purchaseBillService.dispose(auditingBehavior);
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
        AuditFailureBehavior auditFailureBehavior = new AuditFailureBehavior();
        purchaseBillService.dispose(auditFailureBehavior);
    }

    /**
     * 审核成功进货单
     *
     * @param purchaseBillCode
     */
    public void AuditSuccessBill(String purchaseBillCode) {
        PurchaseBill purchaseBill = purchaseBillQueryService.findByBillCode(purchaseBillCode);
        AbstractBillService purchaseBillService = serviceFactory.createBillService(purchaseBill);
        AuditSuccessBehavior auditSuccessBehavior = new AuditSuccessBehavior();
        purchaseBillService.dispose(auditSuccessBehavior);
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
