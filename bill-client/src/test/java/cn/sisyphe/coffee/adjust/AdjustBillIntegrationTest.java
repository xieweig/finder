package cn.sisyphe.coffee.adjust;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.amqp.ReceiverService;
import cn.sisyphe.coffee.bill.application.adjust.AdjustBillManager;
import cn.sisyphe.coffee.bill.application.base.InStorageBillManager;
import cn.sisyphe.coffee.bill.application.base.MoveStorageBillManager;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBill;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AddAdjustBillDTO;
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
    private AdjustBillManager adjustBillManager;

    @Autowired
    private ReceiverService receiverService;


    @Test
    public void shouldGenerateOutStorageBillAfterOffsetDone() {
        Bill adjustBill = adjustBillExtraService.findByBillCode("TJCKCQ0420180110C9K000001");
        inStorageBillManager.convertInStorageBill(adjustBill);
    }


    @Test
    public void saveBill() {

        AddAdjustBillDTO billDTO = new AddAdjustBillDTO();
        // 源单据编码
        billDTO.setRootCode("planBill001");
        // 录单人编码
        billDTO.setOperatorCode("operator001");
        // 出库站点
        billDTO.setOutStationCode("HDQA01");
        // 入库站点
        billDTO.setInStationCode("HDQA02");
        // 出库库房
        Storage outStorage = new Storage();
        outStorage.setStorageCode("Noraml001");
        // 入库库房
        Storage inStorage = new Storage();
        inStorage.setStorageCode("Noraml001");
        // 调剂数量
        billDTO.setAdjustNumber(10);
        // 调剂品种数
        billDTO.setVarietyNumber(10);
        // 计划备注
        billDTO.setPlanMemo("计划单备注测试信息");
        // 出库备注
        billDTO.setOutStorageMemo("务必三日之内送到");
        // 按照货物还是原料拣货
        billDTO.setBasicEnum(BasicEnum.BY_CARGO);
        //
        adjustBillManager.create(billDTO);
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
