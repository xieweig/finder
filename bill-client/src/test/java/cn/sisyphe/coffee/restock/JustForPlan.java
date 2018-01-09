package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
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

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/8
 * @Description:仅仅服务于restock测试用，数据做的字段不全
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class JustForPlan {

    private Random random = new Random();
    private Calendar calendar = Calendar.getInstance();
    Logger logger = LoggerFactory.getLogger(SaveCommitTest.class);

    @Resource
    private PlanBillRepository planBillRepository;

    private PlanBillDetail createPlanBillDetail() {
        PlanBillDetail planBillDetail = new PlanBillDetail();
        planBillDetail.setAmount(random.nextInt(100));
        planBillDetail.setPackageCode("test:03" + random.nextInt(100));
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setCargo(new Cargo("3002" + random.nextInt()));
        rawMaterial.setRawMaterialName("测试原料名称" + random.nextInt(1000));
        planBillDetail.setGoods(rawMaterial);
        planBillDetail.setOutLocation(new Storage("03021" + random.nextInt(100)));
        return planBillDetail;
    }
//为restock生成plan测试用
    private PlanBill createPlanBill() {
        PlanBill planBill = new PlanBill();
        planBill.setHqBill(false);
        /*
        * bill plan 生成对应的restock*/
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
