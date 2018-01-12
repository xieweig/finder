package cn.sisyphe.coffee.adjust;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.amqp.ReceiverService;
import cn.sisyphe.coffee.bill.application.adjust.AdjustBillManager;
import cn.sisyphe.coffee.bill.application.base.purpose.InStorageBillManager;
import cn.sisyphe.coffee.bill.application.movestorage.AllotBillManager;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBill;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AddAdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AddAdjustBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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
    private AllotBillManager allotBillManager;

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
        billDTO.setRootCode("planBill002");
        // 录单人编码
        billDTO.setOperatorCode("operator002");
        // 出库站点
        billDTO.setOutStationCode("HDQA02");
        // 入库站点
        billDTO.setInStationCode("HDQA02");
        // 出库库房
        Storage outStorage = new Storage();
        outStorage.setStorageCode("On001");
        billDTO.setOutStorage(outStorage);

        // 入库库房
        Storage inStorage = new Storage();
        inStorage.setStorageCode("Noraml002");
        billDTO.setInStorage(inStorage);

        // 调剂数量
        billDTO.setAdjustNumber(10);
        // 调剂品种数
        billDTO.setVarietyNumber(10);
        // 计划备注
        billDTO.setPlanMemo("计划单备注测试信息");
        // 出库备注
        billDTO.setOutStorageMemo("务必2日之内送到");
        // 按照货物还是原料拣货
        billDTO.setBasicEnum(BasicEnum.BY_CARGO);
        // 单据来源类型
        billDTO.setBillProperty(SourcePlanTypeEnum.ADJUST);

        List<AddAdjustBillDetailDTO> dtoList = new ArrayList<>();
        AddAdjustBillDetailDTO detailDTO1 = new AddAdjustBillDetailDTO();
        detailDTO1.setShippedAmount(10);
        detailDTO1.setActualAmount(10);
        RawMaterial rawMaterial1 = new RawMaterial();
        Cargo cargo = new Cargo();
        cargo.setCargoCode("cargoCode002");
        rawMaterial1.setCargo(cargo);
        detailDTO1.setRawMaterial(rawMaterial1);
        detailDTO1.setBelongMaterialCode("YL002");
        dtoList.add(detailDTO1);


        AddAdjustBillDetailDTO detailDTO2 = new AddAdjustBillDetailDTO();
        detailDTO2.setShippedAmount(10);
        detailDTO2.setActualAmount(10);
        RawMaterial rawMaterial2 = new RawMaterial();
        Cargo cargo2 = new Cargo();
        cargo2.setCargoCode("cargoCode003");
        rawMaterial2.setCargo(cargo2);
        detailDTO2.setRawMaterial(rawMaterial2);
        detailDTO2.setBelongMaterialCode("YL003");
        dtoList.add(detailDTO2);

        billDTO.setDetails(dtoList);

        adjustBillManager.create(billDTO);
    }

    @Test
    public void submitBill() {

        AddAdjustBillDTO billDTO = new AddAdjustBillDTO();
        // 源单据编码
        billDTO.setRootCode("planBill003");
        // 录单人编码
        billDTO.setOperatorCode("operator001");


        // 出库站点
        billDTO.setOutStationCode("HDQA01");
        // 入库站点
        billDTO.setInStationCode("HDQA02");
        // 出库库房
        Storage outStorage = new Storage();
        outStorage.setStorageCode("On001");
        billDTO.setOutStorage(outStorage);
        // 入库库房
        Storage inStorage = new Storage();
        inStorage.setStorageCode("Noraml002");
        billDTO.setInStorage(inStorage);


        // 调剂数量
        billDTO.setAdjustNumber(10);
        // 调剂品种数
        billDTO.setVarietyNumber(10);
        // 计划备注
        billDTO.setPlanMemo("计划单备注测试信息");
        // 出库备注
        billDTO.setOutStorageMemo("务必1日之内送到");
        // 按照货物还是原料拣货
        billDTO.setBasicEnum(BasicEnum.BY_CARGO);
        // 单据来源类型
        billDTO.setBillProperty(SourcePlanTypeEnum.ADJUST);

        List<AddAdjustBillDetailDTO> dtoList = new ArrayList<>();
        AddAdjustBillDetailDTO detailDTO1 = new AddAdjustBillDetailDTO();
        detailDTO1.setShippedAmount(10);
        detailDTO1.setActualAmount(10);
        RawMaterial rawMaterial1 = new RawMaterial();
        Cargo cargo = new Cargo();
        cargo.setCargoCode("cargoCode004");
        rawMaterial1.setCargo(cargo);
        detailDTO1.setRawMaterial(rawMaterial1);
        detailDTO1.setBelongMaterialCode("YL004");
        dtoList.add(detailDTO1);


        AddAdjustBillDetailDTO detailDTO2 = new AddAdjustBillDetailDTO();
        detailDTO2.setShippedAmount(10);
        detailDTO2.setActualAmount(10);
        RawMaterial rawMaterial2 = new RawMaterial();
        Cargo cargo2 = new Cargo();
        cargo2.setCargoCode("cargoCode005");
        rawMaterial2.setCargo(cargo2);
        detailDTO2.setRawMaterial(rawMaterial2);
        detailDTO2.setBelongMaterialCode("YL005");
        dtoList.add(detailDTO2);

        billDTO.setDetails(dtoList);

        adjustBillManager.submit(billDTO);
    }


//    @Test
//    public void shouldGenerateMoveStorageBill() {
//        Bill adjustBill = adjustBillExtraService.findByBillCode("TJCKCQ04201801104RG000001");
//        moveStorageBillManager.convertMoveStorageBill(adjustBill, bill -> {
//
//        });
//    }

    @Test
    public void shouldFindInStorageBill() {
        AdjustBill adjustBill = adjustBillExtraService.findByBillCode("TJCKCQ05201801105JC000001");
        System.out.println(adjustBill.getBillCode());
    }
}
