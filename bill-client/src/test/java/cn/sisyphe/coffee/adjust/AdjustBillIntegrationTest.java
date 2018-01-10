package cn.sisyphe.coffee.adjust;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.application.adjust.AdjustBillEventProcessor;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBill;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.behavior.BehaviorEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Date 2018/1/10 10:03
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class AdjustBillIntegrationTest {

    @Autowired
    private AdjustBillEventProcessor adjustBillEventProcessor;

    @Autowired
    private AdjustBillExtraService adjustBillExtraService;


    @Test
    public void shouldGenerateOutStorageBillAfterOffsetDone() {
        AdjustBill adjustBill = adjustBillExtraService.findByBillCode("tjckd1515552192738");
        BehaviorEvent<AdjustBill> adjustBillBehaviorEvent = new BehaviorEvent<>(adjustBill);
        adjustBillEventProcessor.billDone(adjustBillBehaviorEvent);

    }
}
