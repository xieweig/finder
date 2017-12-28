package cn.sisyphe.coffee.transmit;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.application.transmit.WayBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.transmit.WayBill;
import cn.sisyphe.coffee.bill.viewmodel.waybill.EditWayBillDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class WayBillTest {

    @Autowired
    private WayBillManager wayBillManager;


    @Test
    public void testCreateDto() {
        UUID uuid = UUID.randomUUID();
        EditWayBillDTO editWayBillDTO = new EditWayBillDTO();
        editWayBillDTO.setMemo("test");
        editWayBillDTO.setAmountOfPackages(122);
        editWayBillDTO.setDeliveryTime(new Date());
        editWayBillDTO.setDestination("重庆");
        //id
        editWayBillDTO.setWayBillCode(uuid.toString());

        editWayBillDTO.setEditWayBillDetailDTOList(null);
        //
        wayBillManager.createWayBillWithDTO(editWayBillDTO);


    }


    @Test
    public void testCreate() {
        WayBill wayBill = new WayBill();

        //   UUID uuid = UUID.randomUUID();

        UUID uuid = UUID.randomUUID();
        wayBill.setBillCode(uuid.toString().toUpperCase());
        wayBill.setBillId(11L);
        wayBill.setAmountOfPackages(12);
        wayBill.setDeliveryTime(new Date());
        wayBill.setPlanArrivalTime(new Date());
        wayBill.setDestination("重庆");
        wayBill.setSourceCode("ssss");
        // 运单类型
        wayBill.setBillType(BillTypeEnum.TRANSMIT);///
        //
        wayBill.setBillDetails(null);

        wayBillManager.createWayBill(wayBill);
    }

}
