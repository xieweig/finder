/*
package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.*;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.purpose.BillPurpose;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.plan.enums.OperationStateEnum;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

*/
/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/8
 * @Description:仅仅服务于restock测试用，数据做的字段不全
 *//*

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class JustForPlan {

    private Random random = new Random();
    private Calendar calendar = Calendar.getInstance();
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String[] CARGOCODE = {"AD","SSA","SDS","WEA"};
    public static final String[] RAWMATERIALCODE = {"2","7","8","9"};
    @Resource
    private PlanBillRepository planBillRepository;

    private PlanBillDetail createPlanBillDetail() {
        PlanBillDetail planBillDetail = new PlanBillDetail();
        planBillDetail.setShippedAmount(random.nextInt(100));
        planBillDetail.setPackageCode("test:03" + random.nextInt(100));
        RawMaterial rawMaterial = new RawMaterial(RAWMATERIALCODE[random.nextInt(RAWMATERIALCODE.length)]);
        Cargo cargo = new Cargo(CARGOCODE[random.nextInt(CARGOCODE.length)]);
        rawMaterial.setCargo(cargo);
        rawMaterial.setRawMaterialName("测试原料名称" + random.nextInt(1000));
        planBillDetail.setGoods(rawMaterial);
        planBillDetail.setOutLocation(new Storage("03021" + random.nextInt(100)));
        return planBillDetail;
    }
//为restock生成plan测试用
    private BillFactory billFactory = new BillFactory();
    private PlanBill createPlanBill() {


        PlanBill planBill = (PlanBill)billFactory.createBill(BillTypeEnum.PLAN);
        planBill.setHqBill(false);
        */
/*
        * bill plan 生成对应的restock*//*

        planBill.setSpecificBillType(BillTypeEnum.RESTOCK);
        planBill.setAuditMemo("remarks: " + random.nextInt(1000));
        planBill.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);

        planBill.setBillName("all" + random.nextInt(100));
        planBill.setProgress(new BigDecimal(random.nextInt(1200)));
        planBill.setBillState(BillStateEnum.SAVED);
        planBill.setAuditState(BillAuditStateEnum.AUDIT_SUCCESS);
        planBill.setSubmitState(BillSubmitStateEnum.SUBMITTED);
        planBill.setAuditPersonCode(random.nextInt(100) + "0302");
        planBill.setBillCode("010" + random.nextInt(1000));
        planBill.setBillType(BillTypeEnum.PLAN);
        planBill.setOperationState(OperationStateEnum.values()[random.nextInt(OperationStateEnum.values().length)]);
        Set<PlanBillDetail> details = new HashSet<>();
        for (int i = 0; i < 3; i++) {

            details.add(this.createPlanBillDetail());
        }
        planBill.setBillDetails(details);
        return  planBill;
    }
    @Test
    public void insertTest(){
        for (int i = 0; i <6 ; i++) {
            this.planBillRepository.save(this.createPlanBill());
        }
    }
}
*/
