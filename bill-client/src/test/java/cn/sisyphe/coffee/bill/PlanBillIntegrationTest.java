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
        planBill.setSpecificBillType(BillTypeEnum.DELIVERY);
        planBill.setBillPurpose(BillPurposeEnum.Plan);
        planBill.setHqBill(true);
        planBill.setBillCode("testcode3");

        PlanBillDetail planBillDetail = new PlanBillDetail();
        Station inLocation = new Station("CQ00");
        inLocation.setStationType(StationType.STORE);
        planBillDetail.setInLocation(inLocation);
        Station outLocation = new Station("CQ01");
        planBillDetail.setOutLocation(outLocation);
        planBillDetail.setAmount(2);
        planBill.setInLocation(inLocation);
        planBill.setOutLocation(outLocation);
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
