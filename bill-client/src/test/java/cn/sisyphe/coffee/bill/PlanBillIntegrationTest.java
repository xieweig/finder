package cn.sisyphe.coffee.bill;


import cn.sisyphe.coffee.bill.application.planbill.PlanBillManager;
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
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private PlanBillManager planBillManager;


    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");



    @Test
    public void test1() {
        //第一次 调剂计划单
        //第二次 退库计划单
        //第三次 配送计划单
        PlanBill planBill = new PlanBill();
        planBill.setBillType(BillTypeEnum.PLAN);
        planBill.setSpecificBillType(BillTypeEnum.ADJUST);
        planBill.setBillPurpose(BillPurposeEnum.Plan);
        planBill.setHqBill(true);
        planBill.setBillCode(planBill.getSpecificBillType().name()+sdf.format(new Date()));
        PlanBillDetail planBillDetail = new PlanBillDetail();
//        Station outLocation = new Station("CTUB11");
//        Station inLocation = new Station("CKGC11");
//        outLocation.setStationType(StationType.STORE);
//        inLocation.setStationType(StationType.STORE);

//        PlanBillDetail planBillDetail = new PlanBillDetail();
//        Station outLocation = new Station("LGDA02");
//        Station inLocation = new Station("LGDA03");
//        outLocation.setStationType(StationType.LOGISTICS);
//        inLocation.setStationType(StationType.LOGISTICS);

//        Station outLocation = new Station("CTUB11");
//        Station inLocation = new Station("LGDA02");
//        outLocation.setStationType(StationType.STORE);
//        inLocation.setStationType(StationType.LOGISTICS);

        Station outLocation = new Station("LGDA02");
        Station inLocation = new Station("CKGC11");
        outLocation.setStationType(StationType.LOGISTICS);
        inLocation.setStationType(StationType.STORE);

        planBillDetail.setOutLocation(outLocation);
        planBillDetail.setInLocation(inLocation);
        planBill.setOutLocation(outLocation);
        planBill.setInLocation(inLocation);
        planBillDetail.setAmount(2);

        RawMaterial rawMaterial1 = new RawMaterial("YLCODE1");
        planBillDetail.setGoods(rawMaterial1);

        PlanBillDetail planBillDetail1 = new PlanBillDetail();
        planBillDetail1.setInLocation(inLocation);
        planBillDetail1.setOutLocation(outLocation);
        planBillDetail1.setGoods(new RawMaterial("YLCODE2"));
        planBillDetail1.setAmount(10);
        Set<PlanBillDetail> planBillDetailSet = new HashSet<>();
        planBillDetailSet.add(planBillDetail);
        planBillDetailSet.add(planBillDetail1);

        planBill.setBillDetails(planBillDetailSet);

        AbstractBillService billService = new BillServiceFactory().createBillService(planBill);
        billService.setBillRepository(planBillRepository);
        billService.dispose(new PurposeBehavior());
        billService.save();
    }

    @Test
    public void findByConditionQuery() {
        ConditionQueryPlanBill conditionQueryPlanBill = new ConditionQueryPlanBill();
        conditionQueryPlanBill.setPage(1);
        conditionQueryPlanBill.setPageSize(100);
        planBillManager.findPageByCondition(conditionQueryPlanBill);
    }

    @Test
    public void findByBillCode() {
        String billCode = "ADJUST20180102173046";
        ResultPlanBillDTO resultPlanBillDTO = planBillManager.findByBillCode(billCode);
        System.out.println(resultPlanBillDTO);
    }

}
