package cn.sisyphe.coffee.bill;


import cn.sisyphe.coffee.bill.application.plan.PlanBillManager;
import cn.sisyphe.coffee.bill.controller.PlanBillController;
import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.PurposeBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillDTO;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillDetailDTO;
import cn.sisyphe.coffee.bill.domain.plan.dto.PlanBillStationDTO;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.plan.AuditPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.plan.ResultPlanBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Collections;
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
    private PlanBillController planBillController;

    @Autowired
    private PlanBillRepository planBillRepository;

    @Autowired
    private PlanBillManager planBillManager;


    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    private static String billCode = "ZBJH003";

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
        planBillDetail.setShippedAmount(2);
        planBill.setInLocation(inLocation);
        planBill.setOutLocation(outLocation);
        RawMaterial rawMaterial1 = new RawMaterial("YLCODE1");
        planBillDetail.setGoods(rawMaterial1);


        PlanBillDetail planBillDetail1 = new PlanBillDetail();
        planBillDetail1.setInLocation(inLocation);
        planBillDetail1.setOutLocation(outLocation);
        planBillDetail1.setGoods(new RawMaterial("YLCODE2"));
        planBillDetail1.setShippedAmount(10);
        Set<PlanBillDetail> planBillDetailSet = new HashSet<>();
        planBillDetailSet.add(planBillDetail);
        planBillDetailSet.add(planBillDetail1);

        planBill.setBillDetails(planBillDetailSet);

        AbstractBillService billService = new BillServiceFactory().createBillService(planBill);
        billService.setBillRepository(planBillRepository);
        billService.dispose(new PurposeBehavior());
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
        ResultPlanBillDTO resultPlanBillDTO = planBillManager.findHqPlanBillByBillCode(billCode);
        System.out.println(resultPlanBillDTO);
    }

    @Test
    public void shouldCreateAPlanBill() {
        PlanBillDTO planBillDTO = new PlanBillDTO();
        planBillDTO.setBillName("总部计划1");
        planBillDTO.setMemo(billCode);
        planBillDTO.setBillType(BillTypeEnum.RESTOCK);
        planBillDTO.setBasicEnum(BasicEnum.BY_MATERIAL);

        PlanBillDetailDTO planBillDetailDTO = new PlanBillDetailDTO();
        planBillDetailDTO.setRawMaterialCode("YL001");


        Station avab01 = new Station("AVAB01");
        avab01.setStationType(StationType.STORE);
        Station duyb01 = new Station("DUYB01");
        duyb01.setStationType(StationType.STORE);

        PlanBillStationDTO planBillStationDTO = new PlanBillStationDTO();
        planBillStationDTO.setAmount(2);
        planBillStationDTO.setInStation(avab01);
        planBillStationDTO.setOutStation(duyb01);
        planBillDetailDTO.setPlanBillStationDTOS(Collections.singletonList(planBillStationDTO));
        planBillDTO.setPlanBillDetailDTOS(Collections.singletonList(planBillDetailDTO));

        planBillController.createPlanBill(planBillDTO, null);

    }

    @Test
    public void shouldSubmitAPlanBill() {
        PlanBillDTO planBillDTO = new PlanBillDTO();
        planBillDTO.setBillName("总部计划1");
        planBillDTO.setMemo(billCode);
        planBillDTO.setBillCode(billCode);
        planBillDTO.setBillType(BillTypeEnum.DELIVERY);
        planBillDTO.setBasicEnum(BasicEnum.BY_MATERIAL);

        PlanBillDetailDTO planBillDetailDTO = new PlanBillDetailDTO();
        planBillDetailDTO.setRawMaterialCode("YL001");


        Station avab01 = new Station("AVAB01");
        avab01.setStationType(StationType.STORE);
        Station duyb01 = new Station("DUYB01");
        duyb01.setStationType(StationType.STORE);

        PlanBillStationDTO planBillStationDTO = new PlanBillStationDTO();
        planBillStationDTO.setAmount(2);
        planBillStationDTO.setInStation(avab01);
        planBillStationDTO.setOutStation(duyb01);
        planBillDetailDTO.setPlanBillStationDTOS(Collections.singletonList(planBillStationDTO));
        planBillDTO.setPlanBillDetailDTOS(Collections.singletonList(planBillDetailDTO));

        planBillController.submitPlanBill(planBillDTO, null);

    }

    @Test
    public void shouldOpenAPlanBill() {

        planBillController.openPlanBill(billCode, null);

    }

    @Test
    public void shouldPassAPlanBill() {

        AuditPlanBillDTO auditPlanBillDTO = new AuditPlanBillDTO();
        auditPlanBillDTO.setBillCode(billCode);
        auditPlanBillDTO.setAuditMemo("没毛病，老铁");

        planBillController.pass(auditPlanBillDTO, null);

    }

}
