package cn.sisyphe.coffee.adjust;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.amqp.ReceiverService;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBill;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.InStorageBillManager;
import cn.sisyphe.coffee.bill.domain.base.MoveStorageBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
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
    private InStorageBillManager inStorageBillManager;

    @Autowired
    private MoveStorageBillManager moveStorageBillManager;

    @Autowired
    private AdjustBillExtraService adjustBillExtraService;

    @Autowired
    private ReceiverService receiverService;


    @Test
    public void shouldGenerateOutStorageBillAfterOffsetDone() {
        Bill adjustBill = adjustBillExtraService.findByBillCode("TJCKCQ0420180110C9K000001");
        inStorageBillManager.convertInStorageBill(adjustBill);
    }

    @Test
    public void shouldGenerateMoveStorageBill() {
        Bill adjustBill = adjustBillExtraService.findByBillCode("TJCKCQ05201801105JC000001");
        moveStorageBillManager.convertMoveStorageBill(adjustBill);
    }

    @Test
    public void shouldFindInStorageBill() {
        AdjustBill adjustBill = adjustBillExtraService.findByBillCode("TJCKCQ05201801105JC000001");
        System.out.println(adjustBill.getBillCode());
    }
}
