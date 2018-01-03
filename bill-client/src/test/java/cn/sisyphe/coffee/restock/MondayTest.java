package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillService;
import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class MondayTest {
    Logger logger = LoggerFactory.getLogger(RestockTest.class);
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;
    @Resource
    private RestockBillRepository restockBillRepository;
    private Random random = new Random();
    private Calendar calendar =Calendar.getInstance();
    private BillFactory billFactory = new BillFactory();

    @Test
    public void overFlow(){
        RestockBill restockBill = (RestockBill) billFactory.createBill(BillTypeEnum.RESTOCK);
        restockBill.setBillPurpose(BillPurposeEnum.OutStorage);
        restockBill.setBillType(BillTypeEnum.RESTOCK);

        restockBill.setBillCode("030212025"+random.nextInt(100));

        RestockBillDetail restockBillDetail = new RestockBillDetail();
        restockBillDetail.setDetailsRemarks("all right");
        Cargo cargo = new Cargo("cargocode 12120");
        restockBillDetail.setGoods(cargo);
        Set<RestockBillDetail> details = new HashSet();
        details.add(restockBillDetail);

        restockBill.setBillDetails(details);

        BillServiceFactory billServiceFactory = new BillServiceFactory();
        RestockBillService service =(RestockBillService) billServiceFactory.createBillService(restockBill);
        service.dispose(new SaveBehavior());
        service.setBillRepository(restockBillRepository);
        service.save();


    }
}
