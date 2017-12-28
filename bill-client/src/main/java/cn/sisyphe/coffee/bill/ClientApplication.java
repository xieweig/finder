package cn.sisyphe.coffee.bill;

import cn.sisyphe.coffee.bill.infrastructure.purchase.PurchaseBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author heyong
 */
@EnableSwagger2
@SpringBootApplication
@EnableSwagger2
public class ClientApplication implements CommandLineRunner {


    @Autowired
    private PurchaseBillRepository purchaseBillRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

//	@Override
//	public void run(String... strings) {
//		String billCode = new Date().toString();
//
//
//
//		// 1.创建进货单主表
//		BillFactory billFactory = new BillFactory();
//		PurchaseBill bill = (PurchaseBill) billFactory.createBill(BillTypeEnum.PURCHASE);
//		bill.setBillCode(billCode);
//		bill.setBillPurpose(BillPurposeEnum.InStorage);
//
//		// 设置站点
//		Station station = new Station("CQ11");
//		station.setStationName("三峡店");
//		bill.setInLocation(station);
//
//
//		// 2.进货单明细
//		PurchaseBillDetail detail = new PurchaseBillDetail();
//		Set<PurchaseBillDetail> billDetails = new HashSet<PurchaseBillDetail>();
//		detail.setAmount(1111);
//		detail.setPackageCode("000");
//		billDetails.add(detail);
//		bill.setBillDetails(billDetails);
//
//
//		// 3.创建单据服务
//		BillServiceFactory serviceFactory = new BillServiceFactory();
//		AbstractBillService billService = serviceFactory.createBillService(bill);
//		// 4.处理保存动作
//		billService.dispose(new SaveBehavior());
//		System.out.println(bill);
//
//		// 处理用途动作
//		//billService.dispose(new PurposeBehavior());
//		//System.out.println(bill);
//
//		// 5.设置数据仓库和保存单据
//		billService.setBillRepository(purchaseBillRepository);
//		billService.save();
//
//		// 6.发送事件
//		billService.sendEvent(applicationEventPublisher);
//
//		System.err.println(purchaseBillRepository.findOneByBillCode(billCode).getBillDetails());
//	}
}
