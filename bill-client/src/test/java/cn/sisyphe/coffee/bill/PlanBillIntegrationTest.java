package cn.sisyphe.coffee.bill;


import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.PurposeBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillDetail;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.plan.jpa.JPAPlanBillRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ncmao
 * @Date 2017/12/25 16:38
 * @description
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class PlanBillIntegrationTest {


    @Autowired
    private JPAPlanBillRepository jpaPlanBillRepository;

    @Autowired
    private PlanBillRepository planBillRepository;


    @Test
    public void test1() {
        PlanBill planBill = new PlanBill();
        planBill.setBillType(BillTypeEnum.DELIVERY);
        planBill.setBillPurpose(BillPurposeEnum.Plan);
        planBill.setHqBill(true);

        PlanBillDetail planBillDetail = new PlanBillDetail();
        Station inLocation = new Station("CQ00");
        inLocation.setStationType(StationType.STORE);
        planBillDetail.setInLocation(inLocation);
        Station outLocation = new Station("CQ01");
        planBillDetail.setOutLocation(outLocation);
        planBill.setInLocation(inLocation);
        planBill.setOutLocation(outLocation);

        RawMaterial rawMaterial1 = new RawMaterial("YLCODE1");
        RawMaterial rawMaterial2 = new RawMaterial("YLCODE2");
        planBillDetail.setGoods(rawMaterial1);
        Set<PlanBillDetail> planBillDetailSet = new HashSet<>();
        planBillDetailSet.add(planBillDetail);
        planBill.setBillDetails(planBillDetailSet);
        AbstractBillService billService = new BillServiceFactory().createBillService(planBill);
        billService.setBillRepository(planBillRepository);
        billService.dispose(new PurposeBehavior());
        billService.save();
    }

}
