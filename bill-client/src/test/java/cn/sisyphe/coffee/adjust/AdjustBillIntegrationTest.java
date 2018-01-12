package cn.sisyphe.coffee.adjust;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.application.adjust.AdjustBillManager;
import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.base.purpose.InStorageBillManager;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBill;
import cn.sisyphe.coffee.bill.domain.adjust.AdjustBillExtraService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillExtraService;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AddAdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AddAdjustBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.AdjustBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.adjust.ConditionQueryAdjustBill;
import cn.sisyphe.coffee.bill.viewmodel.adjust.QueryOneAdjustDTO;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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
    private RestockBillExtraService restockBillExtraService;


    @Test
    public void shouldGenerateOutStorageBillAfterOffsetDone() {
        Bill adjustBill = restockBillExtraService.findByBillCode("TKCKCKG00120180110P10507428");
        Bill inStorageBill = inStorageBillManager.convertInStorageBill(adjustBill);
        inStorageBillManager.allotedForInStorageBill(inStorageBill);
    }



    /**
     * 批量生成---保存
     */
    @Test
    public void batchSaveBill() {
        for (int i = 0; i < 10; i++) {
            AddAdjustBillDTO billDTO = new AddAdjustBillDTO();
            // 源单据编码
            billDTO.setRootCode("planBill001" + i);
            // 录单人编码
            billDTO.setOperatorCode("operator001" + i);
            // 出库站点
            billDTO.setOutStationCode("HDQA01" + i);
            // 入库站点
            billDTO.setInStationCode("HDQA02" + i);
            // 出库库房
            Storage outStorage = new Storage();
            outStorage.setStorageCode("On001" + i);
            billDTO.setOutStorage(outStorage);

            // 入库库房
            Storage inStorage = new Storage();
            inStorage.setStorageCode("Noraml002" + i);
            billDTO.setInStorage(inStorage);

            // 调剂数量
            billDTO.setAdjustNumber(10 + i);
            // 调剂品种数
            billDTO.setVarietyNumber(10 + i);
            // 计划备注
            billDTO.setPlanMemo("计划单备注测试信息" + i);
            // 出库备注
            billDTO.setOutStorageMemo("务必1日之内送到" + i);
            // 按照货物还是原料拣货
            billDTO.setBasicEnum(BasicEnum.BY_CARGO);
            // 单据来源类型
            billDTO.setBillProperty(SourcePlanTypeEnum.ADJUST);

            List<AddAdjustBillDetailDTO> dtoList = new ArrayList<>();
            AddAdjustBillDetailDTO detailDTO1 = new AddAdjustBillDetailDTO();
            detailDTO1.setShippedAmount(10 + i);
            detailDTO1.setActualAmount(10 + i);
            detailDTO1.setBelongMaterialCode("YL001" + i);
            RawMaterial rawMaterial1 = new RawMaterial();
            Cargo cargo = new Cargo();
            cargo.setCargoCode("cargoCode001" + i);
            rawMaterial1.setCargo(cargo);
            rawMaterial1.setRawMaterialCode(detailDTO1.getBelongMaterialCode());
            detailDTO1.setRawMaterial(rawMaterial1);
            dtoList.add(detailDTO1);


            AddAdjustBillDetailDTO detailDTO2 = new AddAdjustBillDetailDTO();
            detailDTO2.setShippedAmount(10 + i);
            detailDTO2.setActualAmount(10 + i);
            detailDTO2.setBelongMaterialCode("YL002" + i);
            RawMaterial rawMaterial2 = new RawMaterial();
            Cargo cargo2 = new Cargo();
            cargo2.setCargoCode("cargoCode002" + i);
            rawMaterial2.setCargo(cargo2);
            rawMaterial2.setRawMaterialCode(detailDTO2.getBelongMaterialCode());
            detailDTO2.setRawMaterial(rawMaterial2);
            dtoList.add(detailDTO2);

            billDTO.setDetails(dtoList);

            adjustBillManager.create(billDTO);
        }

    }

    /**
     * 批量生成---保存
     */
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
        detailDTO1.setBelongMaterialCode("YL001");
        RawMaterial rawMaterial1 = new RawMaterial();
        Cargo cargo = new Cargo();
        cargo.setCargoCode("cargoCode001");
        rawMaterial1.setCargo(cargo);
        rawMaterial1.setRawMaterialCode(detailDTO1.getBelongMaterialCode());
        detailDTO1.setRawMaterial(rawMaterial1);
        dtoList.add(detailDTO1);


        AddAdjustBillDetailDTO detailDTO2 = new AddAdjustBillDetailDTO();
        detailDTO2.setShippedAmount(10);
        detailDTO2.setActualAmount(10);
        detailDTO2.setBelongMaterialCode("YL002");
        RawMaterial rawMaterial2 = new RawMaterial();
        Cargo cargo2 = new Cargo();
        cargo2.setCargoCode("cargoCode002");
        rawMaterial2.setCargo(cargo2);
        rawMaterial2.setRawMaterialCode(detailDTO2.getBelongMaterialCode());
        detailDTO2.setRawMaterial(rawMaterial2);
        dtoList.add(detailDTO2);

        billDTO.setDetails(dtoList);

        adjustBillManager.create(billDTO);

    }

    /**
     * 批量生成--提交
     */
    @Test
    public void batchSubmitBill() {
        for (int i = 10; i < 20; i++) {
            AddAdjustBillDTO billDTO = new AddAdjustBillDTO();
            // 源单据编码
            billDTO.setRootCode("planBill001" + i);
            // 录单人编码
            billDTO.setOperatorCode("operator001" + i);
            // 出库站点
            billDTO.setOutStationCode("HDQA01" + i);
            // 入库站点
            billDTO.setInStationCode("HDQA02" + i);
            // 出库库房
            Storage outStorage = new Storage();
            outStorage.setStorageCode("On001" + i);
            billDTO.setOutStorage(outStorage);

            // 入库库房
            Storage inStorage = new Storage();
            inStorage.setStorageCode("Noraml002" + i);
            billDTO.setInStorage(inStorage);

            // 调剂数量
            billDTO.setAdjustNumber(10 + i);
            // 调剂品种数
            billDTO.setVarietyNumber(10 + i);
            // 计划备注
            billDTO.setPlanMemo("计划单备注测试信息" + i);
            // 出库备注
            billDTO.setOutStorageMemo("务必1日之内送到" + i);
            // 按照货物还是原料拣货
            billDTO.setBasicEnum(BasicEnum.BY_CARGO);
            // 单据来源类型
            billDTO.setBillProperty(SourcePlanTypeEnum.ADJUST);

            List<AddAdjustBillDetailDTO> dtoList = new ArrayList<>();
            AddAdjustBillDetailDTO detailDTO1 = new AddAdjustBillDetailDTO();
            detailDTO1.setShippedAmount(10 + i);
            detailDTO1.setActualAmount(10 + i);
            detailDTO1.setBelongMaterialCode("YL001" + i);
            RawMaterial rawMaterial1 = new RawMaterial();
            Cargo cargo = new Cargo();
            cargo.setCargoCode("cargoCode001" + i);
            rawMaterial1.setCargo(cargo);
            rawMaterial1.setRawMaterialCode(detailDTO1.getBelongMaterialCode());
            detailDTO1.setRawMaterial(rawMaterial1);
            dtoList.add(detailDTO1);


            AddAdjustBillDetailDTO detailDTO2 = new AddAdjustBillDetailDTO();
            detailDTO2.setShippedAmount(10 + i);
            detailDTO2.setActualAmount(10 + i);
            detailDTO2.setBelongMaterialCode("YL002" + i);
            RawMaterial rawMaterial2 = new RawMaterial();
            Cargo cargo2 = new Cargo();
            cargo2.setCargoCode("cargoCode002" + i);
            rawMaterial2.setCargo(cargo2);
            rawMaterial2.setRawMaterialCode(detailDTO2.getBelongMaterialCode());
            detailDTO2.setRawMaterial(rawMaterial2);
            dtoList.add(detailDTO2);

            billDTO.setDetails(dtoList);

            adjustBillManager.submit(billDTO);
        }

    }

    /**
     * 提交
     */
    @Test
    public void submitBill() {

        AddAdjustBillDTO billDTO = new AddAdjustBillDTO();
        // 源单据编码
        billDTO.setRootCode("planBill002");
        // 录单人编码
        billDTO.setOperatorCode("operator002");
        // 出库站点
        billDTO.setOutStationCode("HDQA03");
        // 入库站点
        billDTO.setInStationCode("HDQA04");
        // 出库库房
        Storage outStorage = new Storage();
        outStorage.setStorageCode("On002");
        billDTO.setOutStorage(outStorage);
        // 入库库房
        Storage inStorage = new Storage();
        inStorage.setStorageCode("Noraml003");
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
        // 应拣数量
        detailDTO1.setShippedAmount(10);
        // 实拣数量
        detailDTO1.setActualAmount(10);
        // 所属原料编码
        detailDTO1.setBelongMaterialCode("YL004");
        RawMaterial rawMaterial1 = new RawMaterial();
        Cargo cargo = new Cargo();
        // 货物编码
        cargo.setCargoCode("cargoCode004");
        rawMaterial1.setCargo(cargo);
        rawMaterial1.setRawMaterialCode(detailDTO1.getBelongMaterialCode());
        detailDTO1.setRawMaterial(rawMaterial1);
        dtoList.add(detailDTO1);


        AddAdjustBillDetailDTO detailDTO2 = new AddAdjustBillDetailDTO();
        detailDTO2.setShippedAmount(10);
        detailDTO2.setActualAmount(10);
        detailDTO2.setBelongMaterialCode("YL005");
        RawMaterial rawMaterial2 = new RawMaterial();
        Cargo cargo2 = new Cargo();
        cargo2.setCargoCode("cargoCode005");
        rawMaterial2.setCargo(cargo2);
        rawMaterial2.setRawMaterialCode(detailDTO2.getBelongMaterialCode());
        detailDTO2.setRawMaterial(rawMaterial2);
        dtoList.add(detailDTO2);

        billDTO.setDetails(dtoList);

        adjustBillManager.submit(billDTO);
    }

    /**
     * 查询单个
     */
    @Test
    public void queryByBillCode() {

        String billCode = "TJCKHDQA0320180120A14000001";
        QueryOneAdjustDTO adjustDTO = adjustBillManager.findByBillCode(billCode);
        System.out.println(adjustDTO);
    }

    /**
     * 打开单据
     */
    @Test
    public void openBill() {
        String billCode = "TJCKHDQA0320180120A14000001";
        String auditPersionCode = "XJtest001";
        QueryOneAdjustDTO queryOneAdjustDTO = adjustBillManager.openBill(billCode, auditPersionCode);
        System.out.println(queryOneAdjustDTO);
    }

    /**
     * 修改--保存
     */
    @Test
    public void updateBillToSave() {

        AddAdjustBillDTO billDTO = new AddAdjustBillDTO();
        billDTO.setBillCode("TJCKHDQA0120180120CK0000001");
        // 源单据编码
        billDTO.setRootCode("planBill001test");
        // 录单人编码
        billDTO.setOperatorCode("operator001test");
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
        detailDTO1.setBelongMaterialCode("YL001test");
        RawMaterial rawMaterial1 = new RawMaterial();
        Cargo cargo = new Cargo();
        cargo.setCargoCode("cargoCode001test");
        rawMaterial1.setCargo(cargo);
        rawMaterial1.setRawMaterialCode(detailDTO1.getBelongMaterialCode());
        detailDTO1.setRawMaterial(rawMaterial1);
        dtoList.add(detailDTO1);


        AddAdjustBillDetailDTO detailDTO2 = new AddAdjustBillDetailDTO();
        detailDTO2.setShippedAmount(10);
        detailDTO2.setActualAmount(10);
        detailDTO2.setBelongMaterialCode("YL002test");
        RawMaterial rawMaterial2 = new RawMaterial();
        Cargo cargo2 = new Cargo();
        cargo2.setCargoCode("cargoCode002test");
        rawMaterial2.setCargo(cargo2);
        rawMaterial2.setRawMaterialCode(detailDTO2.getBelongMaterialCode());
        detailDTO2.setRawMaterial(rawMaterial2);
        dtoList.add(detailDTO2);

        billDTO.setDetails(dtoList);

        adjustBillManager.create(billDTO);
    }

    /**
     * 修改--保存
     */
    @Test
    public void updateBillToSubmit() {

        AddAdjustBillDTO billDTO = new AddAdjustBillDTO();
        billDTO.setBillCode("TJCKHDQA0120180120CK0000001");
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
        detailDTO1.setBelongMaterialCode("YL001");
        RawMaterial rawMaterial1 = new RawMaterial();
        Cargo cargo = new Cargo();
        cargo.setCargoCode("cargoCode001");
        rawMaterial1.setCargo(cargo);
        rawMaterial1.setRawMaterialCode(detailDTO1.getBelongMaterialCode());
        detailDTO1.setRawMaterial(rawMaterial1);
        dtoList.add(detailDTO1);


        AddAdjustBillDetailDTO detailDTO2 = new AddAdjustBillDetailDTO();
        detailDTO2.setShippedAmount(10);
        detailDTO2.setActualAmount(10);
        detailDTO2.setBelongMaterialCode("YL002");
        RawMaterial rawMaterial2 = new RawMaterial();
        Cargo cargo2 = new Cargo();
        cargo2.setCargoCode("cargoCode002");
        rawMaterial2.setCargo(cargo2);
        rawMaterial2.setRawMaterialCode(detailDTO2.getBelongMaterialCode());
        detailDTO2.setRawMaterial(rawMaterial2);
        dtoList.add(detailDTO2);

        billDTO.setDetails(dtoList);

        adjustBillManager.submit(billDTO);
    }

    /**
     * 审核不通过
     */
    @Test
    public void auditFailure() {
        String billCode = "TJCKHDQA0120180120CK0000001";
        String auditPersonCode = "auditPersonCode001";
        adjustBillManager.audit(billCode, auditPersonCode, false);
    }

    /**
     * 审核通过
     */
    @Test
    public void auditSuccess() {
        String billCode = "TJCKHDQA0320180120A14000001";
        String auditPersonCode = "auditPersonCode002";
        adjustBillManager.audit(billCode, auditPersonCode, true);
    }


    /**
     * 多条件查询出库单
     */
    @Test
    public void findByConditionsToOut() {
        ConditionQueryAdjustBill cond = new ConditionQueryAdjustBill();
        cond.setPageSize(10);
        cond.setPage(1);
        Page<AdjustBillDTO> byConditionsToOut = adjustBillManager.findByConditionsToOut(cond);
        System.out.println(byConditionsToOut.getContent().toString());
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
