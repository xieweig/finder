package cn.sisyphe.coffee.mistake;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.mistake.MistakeBillManager;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import cn.sisyphe.coffee.bill.viewmodel.mistake.ConditionQueryMistakeBill;
import cn.sisyphe.coffee.bill.viewmodel.mistake.MistakeBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.mistake.MistakeBillDetailDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Amy on 2018/1/18.
 *         describe：
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class MistakeBillTest {
    @Autowired
    private AllotBillManager allotBillManager;
    @Autowired
    private MistakeBillManager mistakeBillManager;

    /**
     * 提交流转误差单
     */
    @Test
    public void submitMistake() {
        String allotBillCode = "AL201801181818006789";
        AllotBill allotBill = (AllotBill) allotBillManager.findOneByBillCode(allotBillCode);
        mistakeBillManager.submitByAllotBill(allotBill);
    }

    /**
     * 流转误差单的回调-修改流转误差单的状态
     */
    @Test
    public void callBack() {
        String mistakeBillCode = "TMCKCQ1120180180EES000001";
        mistakeBillManager.callbackMistakeBill(mistakeBillCode);
    }

    /**
     * 流转误差单的回调-追加调拨单号至流转误差单中
     */
    @Test
    public void callBackToAddAllotBillCode() {
        String mistakeBillCode = "TMCKCQ1120180180EES000001";
        String allotBillCode = "AL201801181818006789";
        mistakeBillManager.callBackToAddAllotBillCode(mistakeBillCode, allotBillCode);
    }

    /**
     * 条件筛选流转误差单
     */
    @Test
    public void findByConditions() {
        ConditionQueryAllotBill conditionQueryAllotBill = new ConditionQueryAllotBill();
        conditionQueryAllotBill.setPage(1);
        conditionQueryAllotBill.setPageSize(100);
        Page<AllotBillDTO> page = allotBillManager.findBillByCondition(conditionQueryAllotBill, BillPurposeEnum.MOVE_STORAGE);
        System.out.println(page);
    }

    /**
     * 查询流转误差单的详情
     */
    @Test
    public void findOne() {
        String allotBillCode = "AL201801181818006789";
        AllotBillDTO dto = allotBillManager.findBillDtoByBillCode(allotBillCode);
        MistakeBillDTO mistakeBillDTO = new MistakeBillDTO();
        if (dto != null && !StringUtils.isEmpty(dto.getBillCode())) {
            mistakeBillDTO = mistakeBillManager.findBillDtoBySourceCode(dto.getBillCode());
            if (mistakeBillDTO == null) {
                mistakeBillDTO = new MistakeBillDTO();
            }
        } else {
            dto = new AllotBillDTO();
        }
        System.out.println(dto);
        System.out.println(mistakeBillDTO.getBillDetails());
    }

    /**
     * 提交报溢单
     */
    @Test
    public void submitOverFlow() {
        MistakeBillDTO mistakeBill = new MistakeBillDTO();
        //入库位置信息
        Station inStation = new Station();
        Storage inStorage = new Storage();
        //设置库位信息
        inStorage.setStorageCode("K001");
        inStorage.setStorageName("库位001");
        //设置入站点信息
        inStation.setStationCode("CQ11");
        inStation.setStationName("重庆三峡店");
        inStation.setStationType(StationType.STORE);
        inStation.setStorage(inStorage);
        mistakeBill.setInLocation(inStation);

        mistakeBill.setMemo("备注添加的信息");

        //明细
        Cargo cargo = new Cargo();
        cargo.setCargoName("货物1");
        cargo.setCargoName("C0001");
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setRawMaterialName("Y001");
        rawMaterial.setRawMaterialCode("原料001");
        rawMaterial.setCargo(cargo);
        Set<MistakeBillDetailDTO> mistakeBillDetailSet = new HashSet<>();
        MistakeBillDetailDTO mistakeBillDetail = new MistakeBillDetailDTO();
        mistakeBillDetail.setActualAmount(36);
        mistakeBillDetail.setShippedAmount(7);
        mistakeBillDetail.setRawMaterial(rawMaterial);
        mistakeBillDetailSet.add(mistakeBillDetail);
        mistakeBill.getBillDetails().addAll(mistakeBillDetailSet);
        mistakeBillManager.submitOverFlow(mistakeBill);

    }

    /**
     * 提交报损单
     */
    @Test
    public void submitLoss() {
        MistakeBillDTO mistakeBill = new MistakeBillDTO();
        //出库位置信息
        Station outStation = new Station();
        Storage outStorage = new Storage();
        //设置库位信息
        outStorage.setStorageCode("K002");
        outStorage.setStorageName("库位002");
        //设置出站点信息
        outStation.setStationCode("CQ11");
        outStation.setStationName("重庆三峡店");
        outStation.setStationType(StationType.STORE);
        outStation.setStorage(outStorage);
        mistakeBill.setOutLocation(outStation);

        mistakeBill.setMemo("备注添加的信息");
        //明细
        Cargo cargo = new Cargo();
        cargo.setCargoName("货物2");
        cargo.setCargoName("C0002");
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setRawMaterialName("Y002");
        rawMaterial.setRawMaterialCode("原料002");
        rawMaterial.setCargo(cargo);
        Set<MistakeBillDetailDTO> mistakeBillDetailSet = new HashSet<>();
        MistakeBillDetailDTO mistakeBillDetail = new MistakeBillDetailDTO();
        mistakeBillDetail.setActualAmount(12);
        mistakeBillDetail.setShippedAmount(15);
        mistakeBillDetail.setRawMaterial(rawMaterial);
        mistakeBillDetailSet.add(mistakeBillDetail);
        mistakeBill.getBillDetails().addAll(mistakeBillDetailSet);
        mistakeBillManager.submitLoss(mistakeBill);
    }


    /**
     * 提交日常误差单
     */
    @Test
    public void submitDayMistake() {
        MistakeBillDTO mistakeBill = new MistakeBillDTO();
        //出库位置信息
        Station outStation = new Station();
        Storage outStorage = new Storage();
        //设置库位信息
        outStorage.setStorageCode("K003");
        outStorage.setStorageName("库位003");
        //设置出站点信息
        outStation.setStationCode("CQ11");
        outStation.setStationName("重庆三峡店");
        outStation.setStationType(StationType.STORE);
        outStation.setStorage(outStorage);
        mistakeBill.setOutLocation(outStation);

        mistakeBill.setMemo("备注添加的信息");

        //明细
        Cargo cargo = new Cargo();
        cargo.setCargoName("货物3");
        cargo.setCargoName("C0003");
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setRawMaterialName("Y003");
        rawMaterial.setRawMaterialCode("原料003");
        rawMaterial.setCargo(cargo);
        Set<MistakeBillDetailDTO> mistakeBillDetailSet = new HashSet<>();
        MistakeBillDetailDTO mistakeBillDetail = new MistakeBillDetailDTO();
        mistakeBillDetail.setActualAmount(12);
        mistakeBillDetail.setShippedAmount(15);
        mistakeBillDetail.setRawMaterial(rawMaterial);
        mistakeBillDetailSet.add(mistakeBillDetail);
        mistakeBill.getBillDetails().addAll(mistakeBillDetailSet);
        mistakeBillManager.submitDayMistake(mistakeBill);

    }

    /**
     * 条件筛选报溢单
     */
    @Test
    public void findOverFlowByConditions() {
        ConditionQueryMistakeBill conditionQueryMistakeBill = new ConditionQueryMistakeBill();
        conditionQueryMistakeBill.setPage(1);
        conditionQueryMistakeBill.setPageSize(100);
        Page<MistakeBillDTO> page = mistakeBillManager.findOverFlowByConditions(conditionQueryMistakeBill);
        System.out.println(page);
    }

    /**
     * 条件筛选报损单
     */
    @Test
    public void findLossByConditions() {
        ConditionQueryMistakeBill conditionQueryMistakeBill = new ConditionQueryMistakeBill();
        conditionQueryMistakeBill.setPage(1);
        conditionQueryMistakeBill.setPageSize(100);
        Page<MistakeBillDTO> page = mistakeBillManager.findLossByConditions(conditionQueryMistakeBill);
        System.out.println(page);
    }

    /**
     * 条件筛选日常误差单
     */
    @Test
    public void findDayMistakeByConditions() {
        ConditionQueryMistakeBill conditionQueryMistakeBill = new ConditionQueryMistakeBill();
        conditionQueryMistakeBill.setPage(1);
        conditionQueryMistakeBill.setPageSize(100);
        Page<MistakeBillDTO> page = mistakeBillManager.findDayMistakeByConditions(conditionQueryMistakeBill);
        System.out.println(page);
    }

    /**
     * 查询报溢单详情
     */
    @Test
    public void findOverFlowByBillCode() {
        String billCode = "TMCKCQ112018022086G000001";
        MistakeBillDTO mistakeBillDTO = mistakeBillManager.findMistakeBillDTOByBillCode(billCode, BillPurposeEnum.IN_STORAGE);
        System.out.println(mistakeBillDTO);
    }

    /**
     * 查询报损单详情
     */
    @Test
    public void findLossByByBillCode() {
        String billCode = "TMCKCQ1120180220A90000001";
        MistakeBillDTO mistakeBillDTO = mistakeBillManager.findMistakeBillDTOByBillCode(billCode, BillPurposeEnum.OUT_STORAGE);
        System.out.println(mistakeBillDTO);
    }

    /**
     * 查询日常误差单详情
     */
    @Test
    public void findDayMistakeByByBillCode() {
        String billCode = "TMCKCQ1120180220DJ8000001";
        MistakeBillDTO mistakeBillDTO = mistakeBillManager.findMistakeBillDTOByBillCode(billCode, BillPurposeEnum.MOVE_STORAGE);
        System.out.println(mistakeBillDTO);
    }
}
