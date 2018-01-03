package cn.sisyphe.coffee.bill;

import cn.sisyphe.coffee.bill.application.purchase.PurchaseBillManager;
import cn.sisyphe.coffee.bill.application.purchase.PurchaseManager;
import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBill;
import cn.sisyphe.coffee.bill.domain.purchase.PurchaseBillDetail;
import cn.sisyphe.coffee.bill.infrastructure.purchase.PurchaseBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by heyong on 2017/12/19 13:57
 * Description:
 *
 * @author heyong
 */
@SpringBootApplication
public class CoreApplication implements CommandLineRunner {

    @Autowired
    private PurchaseBillRepository purchaseBillRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);

    }


    @Autowired
    private PurchaseManager purchaseManager;

    @Override
    public void run(String... strings) {

           // System.err.println(purchaseManager.findOne("Wed Jan 03 10:13:18 CST 2018"));

        String billCode = new Date().toString();



        // 1.创建进货单主表
        BillFactory billFactory = new BillFactory();
        PurchaseBill bill = (PurchaseBill) billFactory.createBill(BillTypeEnum.PURCHASE);
        bill.setBillCode(billCode);
        bill.setBillPurpose(BillPurposeEnum.InStorage);

        // 设置站点
        Station station = new Station("CQ11");
        station.setStationName("三峡店");
        bill.setInLocation(station);


        // 2.进货单明细
        PurchaseBillDetail detail = new PurchaseBillDetail();
        Set<PurchaseBillDetail> billDetails = new HashSet<PurchaseBillDetail>();

        // 2.1 设置原料和货物
        RawMaterial rawMaterial = new RawMaterial("R001");
        rawMaterial.setCargo(new Cargo("C002"));
        detail.setGoods(rawMaterial);

        detail.setAmount(1111);
        detail.setPackageCode("000");
        billDetails.add(detail);
        bill.setBillDetails(billDetails);


        // 3.创建单据服务
        purchaseManager.save(bill);
//        BillServiceFactory serviceFactory = new BillServiceFactory();
//        AbstractBillService billService = serviceFactory.createBillService(bill);
//        // 4.处理保存动作
//        billService.dispose(new SaveBehavior());
//        System.out.println(bill);
//
//        // 处理用途动作
//        //billService.dispose(new PurposeBehavior());
//        //System.out.println(bill);
//
//        // 5.设置数据仓库和保存单据
//        billService.setBillRepository(purchaseBillRepository);
//        billService.save();
//
//        // 6.发送事件
//        billService.sendEvent(applicationEventPublisher);

        System.err.println(purchaseBillRepository.findOneByBillCode(billCode).getBillDetails());
    }
}





















