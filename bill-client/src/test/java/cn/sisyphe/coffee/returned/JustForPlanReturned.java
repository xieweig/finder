package cn.sisyphe.coffee.returned;


import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;

import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.restock.JustForPlan;
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
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class JustForPlanReturned extends JustForPlan {
    private Random random = new Random();
    private Calendar calendar = Calendar.getInstance();
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PlanBillRepository planBillRepository;

    //为returned生成plan测试用
    private BillTypeEnum billType = BillTypeEnum.RETURNED;

    private PlanBill returnedPlanBill() {
        PlanBill planBill = this.createPlanBill();

        planBill.setSpecificBillType(billType);

        return  planBill;
    }
    @Test
    public void insertTest(){
        for (int i = 0; i <10 ; i++) {
            this.planBillRepository.save(this.returnedPlanBill());
        }
    }


}