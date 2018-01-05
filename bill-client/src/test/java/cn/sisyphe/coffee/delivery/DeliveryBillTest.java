package cn.sisyphe.coffee.delivery;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.application.deliverybill.DeliveryBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.delivery.enums.PickingTypeEnum;
import cn.sisyphe.coffee.bill.infrastructure.delivery.DeliveryBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yichuan on 2018/1/4.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class DeliveryBillTest {

    @Autowired
    private DeliveryBillRepository deliveryBillRepository;


    @Autowired
    DeliveryBillManager deliveryBillManager;


    @Test
    public void testFindByCode() {


        DeliveryBillDTO deliveryBillDTO = new DeliveryBillDTO();

        deliveryBillDTO.setBillCode("DE06a79c72-7823-469e-bf70-fc3e26376ae7");

        deliveryBillDTO = deliveryBillManager.findOneDeliveryBillByBillCode(deliveryBillDTO.getBillCode());///

        System.out.println("deliveryBillDTO  " + deliveryBillDTO);
    }

    /**
     * 多条件分页查询配送单
     */
    @Test
    public void testFindPages() {

        ConditionQueryDeliveryBill conditionQueryDeliveryBill = new ConditionQueryDeliveryBill();

        conditionQueryDeliveryBill.setPage(1);
        conditionQueryDeliveryBill.setPageSize(100);
        conditionQueryDeliveryBill.setBillCode("DE06a79c72-7823-469e-bf70-fc3e26376ae7");

        QueryDeliveryBillDTO queryDeliveryBillDTO = deliveryBillManager
                .findDeliveryBillsByCondition(conditionQueryDeliveryBill);

        System.out.println(" totalElements :" + queryDeliveryBillDTO.getTotalNumber());
        System.out.println(" info :" + queryDeliveryBillDTO.getContent());
    }

    /**
     * 添加拣货
     */
    @Test
    public void testCreateBill() {
        DeliveryPickingEditDTO editDTO = new DeliveryPickingEditDTO();

        //editDTO.setBillCode("DE" + UUID.randomUUID().toString());
        //
        editDTO.setOperatorCode("user001");
        Station inLocation = new Station("CQ00");
        inLocation.setStationType(StationType.STORE);

        Station outLocation = new Station("CQ02");
        editDTO.setOutLocation(outLocation);

        editDTO.setInLocation(inLocation);
        editDTO.setOutLocation(outLocation);
        ///////////////明细
        editDTO.setBillType(BillTypeEnum.DELIVERY);// 配送单

        List<DeliveryPickingEditItemDTO> billDetails = new ArrayList<>();
        DeliveryPickingEditItemDTO itemDTO = new DeliveryPickingEditItemDTO();
        // 包号
        itemDTO.setPackageCode("44444444445554");

        itemDTO.setMemo("test");
        itemDTO.setPackageCode("sp0001");
        itemDTO.setExpectedAmount(102);
        itemDTO.setActualAmount(41);// 实际数量

        // 设置货物和原料信息
        RawMaterial rawMaterial = new RawMaterial();
        Cargo cargo = new Cargo();

        cargo.setCargoCode("GD001");
        cargo.setCargoName("商品0001");
        rawMaterial.setCargo(cargo);

        rawMaterial.setRawMaterialCode("KF0001");
        rawMaterial.setRawMaterialName("咖啡01");
        itemDTO.setRawMaterial(rawMaterial);


        Storage storage = new Storage();
        storage.setStorageCode("ZT01");
        storage.setStorageName("在途库");


        // 站点
        Station station = new Station("CD39");
        station.setStationName("总部4");
        station.setStationType(StationType.STORE);
        // 将库房设置到站点中去
        station.setStorage(storage);
        editDTO.setStation(station);

        billDetails.add(itemDTO);
        editDTO.setBillPurpose(BillPurposeEnum.OutStorage);//出库
        editDTO.setBillDetails(billDetails);
        // 拣货方式
        editDTO.setPickingTypeEnum(PickingTypeEnum.PICKING_BY_CARGO);
        //
        deliveryBillManager.pickingByCargo(editDTO);

    }


}
