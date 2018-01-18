package cn.sisyphe.coffee.allot;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.application.delivery.DeliveryBillManager;
import cn.sisyphe.coffee.delivery.InstanceIterat;
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
@SpringBootTest(classes = {CoreApplication.class})
public class NewAllotTest extends InstanceIterat {

    @Resource
    private DeliveryBillManager deliveryBillManager;

    @Test
    public void saveDTO(){
        for (int i = 0; i <6; i++) {
            deliveryBillManager.saveBill(this.nextRandomAddDeliveryBillDTO(random.nextInt(3)+1));

        }

    }

}