package cn.sisyphe.coffee.bill;


import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.PurposeBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.plan.CargoDetail;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.plan.enums.PlanTypeEnum;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.plan.jpa.CargoDetailRepository;
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

    @Autowired
    private CargoDetailRepository cargoDetailRepository;

    @Test
    public void createSuccess() {
        PlanBill planBill = new PlanBill();
        planBill.setPlanName("2017总部调剂计划");
        planBill.setPlanCode("HTJ001");
        planBill.setPlanType(PlanTypeEnum.ADJUST);
        planBill.setBillPurpose(BillPurposeEnum.Plan);
        planBill.setBasicEnum(BasicEnum.BY_CARGO);

        jpaPlanBillRepository.save(planBill);

    }

    @Test
    public void shouldDoTheSplitBehavior() {
        PlanBill planBill = new PlanBill();
        planBill.setPlanName("2017总部调剂计划");
        planBill.setPlanCode("HTJ001");
        planBill.setPlanType(PlanTypeEnum.ADJUST);
        planBill.setBillPurpose(BillPurposeEnum.Plan);
        planBill.setBasicEnum(BasicEnum.BY_CARGO);
        CargoDetail cargoDetail = new CargoDetail();
        cargoDetail.setCargoCode("TESTCODE");
        cargoDetail.setCargoName("货物001");

        CargoDetail savedCargoDetail = cargoDetailRepository.save(cargoDetail);
        PlanBillDetail planBillDetail = new PlanBillDetail();
        planBillDetail.setCargoDetail(savedCargoDetail);

        Set<PlanBillDetail> billDetails = new HashSet<>();
        billDetails.add(planBillDetail);

        planBill.setBillDetails(billDetails);


        BillServiceFactory billServiceFactory = new BillServiceFactory();
        AbstractBillService billService = billServiceFactory.createBillService(planBill);
        billService.setBillRepository(planBillRepository);
        billService.dispose(new PurposeBehavior());
    }
}
