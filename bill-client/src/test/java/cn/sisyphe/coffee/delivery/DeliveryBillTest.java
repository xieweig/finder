/*
package cn.sisyphe.coffee.delivery;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.application.deliverybill.DeliveryBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBill;
import cn.sisyphe.coffee.bill.infrastructure.delivery.DeliveryBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.DeliveryBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.DeliveryPickingEditDTO;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.DeliveryPickingEditItemDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

*/
/**
 * Created by yichuan on 2018/1/4.
 *//*


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class DeliveryBillTest {

    @Autowired
    private DeliveryBillRepository deliveryBillRepository;


    @Autowired
    DeliveryBillManager deliveryBillManager;


    @Test
    public void testFindByCode() {
        //
        DeliveryBill deliveryBill = deliveryBillRepository.findOneByBillCode("A1");

        System.out.println("DeliveryBill  " + deliveryBill);

        DeliveryBillDTO deliveryBillDTO = new DeliveryBillDTO();

        System.out.println("deliveryBillDTO  " + deliveryBillDTO.convertBillToDTO(deliveryBill));
    }


    */
/**
     * 添加拣货
     *//*

    @Test
    public void testCreateBill() {
        DeliveryPickingEditDTO editDTO = new DeliveryPickingEditDTO();
        editDTO.setBillCode("DE" + UUID.randomUUID().toString());
        //
        editDTO.setOperatorCode("user001");
        Station inLocation = new Station("CQ00");
        inLocation.setStationType(StationType.STORE);

        Station outLocation = new Station("CQ01");
        editDTO.setOutLocation(outLocation);

        editDTO.setInLocation(inLocation);
        editDTO.setOutLocation(outLocation);
        ///////////////明细
        editDTO.setBillType(BillTypeEnum.DELIVERY);// 配送单

        List<DeliveryPickingEditItemDTO> billDetails = new ArrayList<>();
        DeliveryPickingEditItemDTO itemDTO = new DeliveryPickingEditItemDTO();

        itemDTO.setPackageCode("154s54dfsad");

        itemDTO.setMemo("test");
        itemDTO.setCargoCode("aaaaaaaaaa");
        itemDTO.setPackageCode("sp0001");
        itemDTO.setExpectedAmount(102);
        itemDTO.setExpectedAmount(41);

        billDetails.add(itemDTO);
        editDTO.setBillPurpose(BillPurposeEnum.OutStorage);//出库
        editDTO.setBillDetails(billDetails);
        //
        deliveryBillManager.pickingByCargo(editDTO);

    }


}
*/
