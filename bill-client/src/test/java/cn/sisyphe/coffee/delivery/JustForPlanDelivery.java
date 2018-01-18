package cn.sisyphe.coffee.delivery;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.restock.JustForPlan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/18
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class JustForPlanDelivery extends JustForPlan {

    @Resource
    private PlanBillRepository planBillRepository;

    //为returned生成plan测试用
    private BillTypeEnum billType = BillTypeEnum.DELIVERY;

    private PlanBill deliveryPlanBill() {
        PlanBill planBill = this.createPlanBill();

        planBill.setSpecificBillType(billType);

        return  planBill;
    }
    @Test
    public void insertTest(){
        for (int i = 0; i <6 ; i++) {
            this.planBillRepository.save(this.deliveryPlanBill());
        }
    }

}
