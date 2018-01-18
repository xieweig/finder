package cn.sisyphe.coffee.bill;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by heyong on 2017/12/19 13:57
 * Description:
 *
 * @author heyong
 */
@SpringBootApplication
public class CoreApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);

    }

    @Override
    public void run(String... strings) {

//        // 1.创建进货单主表
//        BillFactory billFactory = new BillFactory();
//        AllotBill bill = (AllotBill) billFactory.createBill(BillTypeEnum.ALLOT);
//        bill.setBillPurpose(BillPurposeEnum.IN_STORAGE);
//
//        // 设置站点
//        Station station = new Station("CQ11");
//        station.setStationName("三峡店");
//        bill.setInLocation(station);
//
//
//        // 2.进货单明细
//        AllotBillDetail detail = new AllotBillDetail();
//        Set<AllotBillDetail> billDetails = new HashSet<AllotBillDetail>();
//
//        // 2.1 设置原料和货物
//        RawMaterial rawMaterial = new RawMaterial("R001");
//        rawMaterial.setCargo(new Cargo("C002"));
//        detail.setGoods(rawMaterial);
//
//        detail.setPackageCode("000");
//        billDetails.add(detail);
//        bill.setBillDetails(billDetails);

//        BillManagerFactory.getManager(bill.getBillType()).save(bill);
//
//
//        // 3.创建单据服务
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
//
//        System.err.println(purchaseBillRepository.findOneByBillCode(billCode).getBillDetails());
    }
}





















