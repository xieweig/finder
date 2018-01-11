package cn.sisyphe.coffee.adjust;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.amqp.ReceiverService;
import cn.sisyphe.coffee.bill.application.adjust.AdjustBillEventProcessor;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillExtraService;
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

    @Autowired
    private ReceiverService receiverService;


    @Test
    public void shouldGenerateOutStorageBillAfterOffsetDone() {
//        Bill adjustBill = adjustBillExtraService.findByBillCode("1515570200185");
//        Bill inStorageBill = receiverService.generateInStorageBill(adjustBill);
//        AbstractBillManager billManager = receiverService.getBillManager(adjustBill.getBillType());
//        billManager.save(inStorageBill);

    }
}
