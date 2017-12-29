package cn.sisyphe.coffee.bill;

import cn.sisyphe.coffee.bill.application.PurchaseBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBillDetail;
import cn.sisyphe.coffee.bill.viewmodel.purchase.AddPurchaseBillDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：进货单据单元测试
 * version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class PurchaseBillTest {

    @Autowired
    private PurchaseBillManager purchaseBillManager;

    /**
     * 测试保存单据
     */
    @Test
    public void save() {
        PurchaseBill purchaseBill = new PurchaseBill();
        purchaseBill.setBillCode("test000001");
        purchaseBill.setBillType(BillTypeEnum.PURCHASE);
        purchaseBill.setBillPurpose(BillPurposeEnum.InStorage);
        // 设置站点
        Station station = new Station("CD36");
        station.setStationName("总部");
        purchaseBill.setInLocation(station);

        // 2.进货单明细
        PurchaseBillDetail detail = new PurchaseBillDetail();
        Set<PurchaseBillDetail> billDetails = new HashSet<>();
        detail.setAmount(100);
        detail.setPackageCode("package001");
        billDetails.add(detail);
        purchaseBill.setBillDetails(billDetails);
        AddPurchaseBillDTO addPurchaseBillDTO = new AddPurchaseBillDTO();
        purchaseBillManager.saveBill(addPurchaseBillDTO);
    }

    /**
     * 测试提交单据
     */
    @Test
    public void commit() {
        PurchaseBill purchaseBill = new PurchaseBill();
        purchaseBill.setBillCode("test000002");
        purchaseBill.setBillType(BillTypeEnum.PURCHASE);
        purchaseBill.setBillPurpose(BillPurposeEnum.InStorage);
        // 设置站点
        Station station = new Station("CD37");
        station.setStationName("总部1");
        purchaseBill.setInLocation(station);

        // 2.进货单明细
        PurchaseBillDetail detail = new PurchaseBillDetail();
        Set<PurchaseBillDetail> billDetails = new HashSet<>();
        detail.setAmount(200);
        detail.setPackageCode("package002");
        billDetails.add(detail);
        purchaseBill.setBillDetails(billDetails);
//        purchaseBillManager.submitBill(purchaseBill);
    }

    /**
     * 测试打开单据，状态改为审核中
     */
    @Test
    public void auditingBill() {
        String billCode = "test000002";
        purchaseBillManager.auditingBill(billCode);
    }

    /**
     * 测试审核失败单据
     */
    @Test
    public void auditFailureBill() {
        String billCode = "test000002";
        purchaseBillManager.auditFailureBill(billCode);
    }

    /**
     * 测试审核成功单据
     */
    @Test
    public void AuditSuccessBill() {
        String billCode = "test000002";
        purchaseBillManager.AuditSuccessBill(billCode);
    }
}
