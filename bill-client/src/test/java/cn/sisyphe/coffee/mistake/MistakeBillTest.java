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
 * describe：
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class MistakeBillTest {
    @Autowired
    private AllotBillManager allotBillManager;
    @Autowired
    private MistakeBillManager mistakeBillManager;
    @Test
    public void submitMistake(){
        String allotBillCode = "AL201801181818006789";
        AllotBill allotBill = (AllotBill) allotBillManager.findOneByBillCode(allotBillCode);
        mistakeBillManager.submitByAllotBill(allotBill);
    }
    @Test
    public void callBack(){
        String mistakeBillCode = "TMCKCQ1120180180EES000001";
        mistakeBillManager.callbackMistakeBill(mistakeBillCode);
    }
    @Test
    public void callBackToAddAllotBillCode(){
        String mistakeBillCode = "TMCKCQ1120180180EES000001";
        String allotBillCode = "AL201801181818006789";
        mistakeBillManager.callBackToAddAllotBillCode(mistakeBillCode,allotBillCode);
    }
    @Test
    public void findByConditions(){
        ConditionQueryAllotBill conditionQueryAllotBill = new ConditionQueryAllotBill();
        conditionQueryAllotBill.setPage(1);
        conditionQueryAllotBill.setPageSize(100);
        Page<AllotBillDTO> page  = allotBillManager.findBillByCondition(conditionQueryAllotBill, BillPurposeEnum.MOVE_STORAGE);
        System.out.println(page);
    }
    @Test
    public void findOne(){
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
    //报溢
    @Test
    public void submitOverFlow(){
        MistakeBillDTO mistakeBill = new MistakeBillDTO();
        mistakeBill.setBelongStationCode("CQ11");
        mistakeBill.setOperatorCode("YGADMIN");
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
    //报损
    @Test
    public void submitLoss(){
        MistakeBillDTO mistakeBill = new MistakeBillDTO();
        mistakeBill.setBelongStationCode("CQ11");
        mistakeBill.setOperatorCode("YGADMIN");
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


    //日常误差
    @Test
    public void submitDayMistake(){
        MistakeBillDTO mistakeBill = new MistakeBillDTO();
        mistakeBill.setBelongStationCode("CQ11");
        mistakeBill.setOperatorCode("YGADMIN");
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
}
