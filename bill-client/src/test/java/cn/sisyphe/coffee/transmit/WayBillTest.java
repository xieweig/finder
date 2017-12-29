package cn.sisyphe.coffee.transmit;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.application.transmit.WayBillManager;
import cn.sisyphe.coffee.bill.domain.transmit.IWayBillService;
import cn.sisyphe.coffee.bill.domain.transmit.WayBill;
import cn.sisyphe.coffee.bill.domain.transmit.enums.PackAgeTypeEnum;
import cn.sisyphe.coffee.bill.viewmodel.waybill.ConditionQueryWayBill;
import cn.sisyphe.coffee.bill.viewmodel.waybill.EditWayBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.waybill.EditWayBillDetailDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class WayBillTest {

    @Autowired
    private WayBillManager wayBillManager;

    @Autowired
    private IWayBillService iWayBillService;

    @Test
    public void testFindByPage() {

        ConditionQueryWayBill conditionQueryWayBill = new ConditionQueryWayBill();
        conditionQueryWayBill.setOperatorName("小明");
        //conditionQueryWayBill.setInStationCode("jd");
        conditionQueryWayBill.setPageSize(100);
        conditionQueryWayBill.setPage(1);
        Page<WayBill> billPage = iWayBillService.findPageByCondition(conditionQueryWayBill);
        if (billPage != null) {
            System.out.println(billPage.getContent());
        }

    }


    @Test
    public void testCreateDto() {

        //
        UUID uuid = UUID.randomUUID();
        EditWayBillDTO editWayBillDTO = new EditWayBillDTO();
        editWayBillDTO.setMemo("test");
        editWayBillDTO.setAmountOfPackages(122);
        editWayBillDTO.setDeliveryTime(new Date());
        editWayBillDTO.setDestination("重庆");
        editWayBillDTO.setOperatorName("小明");
        editWayBillDTO.setLogisticsCompanyName("韵达快递");
        editWayBillDTO.setPlanArrivalTime(new Date());//预计到达时间
        editWayBillDTO.setAmountOfPackages(155);
        //id
        editWayBillDTO.setWayBillCode(uuid.toString());

        List<EditWayBillDetailDTO> editWayBillDetailDTOList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            EditWayBillDetailDTO editWayBillDetailDTO = new EditWayBillDetailDTO(editWayBillDTO);

            editWayBillDetailDTO.setOutStorageTime(new Date());
            editWayBillDetailDTO.setInStationCode("cq11");
            editWayBillDetailDTO.setOutStationCode("cq12");
            editWayBillDetailDTO.setPackageNumbers("8955");
            editWayBillDetailDTO.setOperatorName("小明");
            editWayBillDetailDTO.setTotalAmount(10);
            editWayBillDetailDTO.setTotalCount(6);
            //
            editWayBillDetailDTO.setPackageType(PackAgeTypeEnum.ONE_BILL_TO_ONE_PACKAGE.name().toString());
            editWayBillDetailDTOList.add(editWayBillDetailDTO);
        }
        editWayBillDTO.setEditWayBillDetailDTOList(editWayBillDetailDTOList);
        //
        wayBillManager.createWayBillWithDTO(editWayBillDTO);


    }


    @Test
    public void testCreate() {
        WayBill wayBill = new WayBill();

        UUID uuid = UUID.randomUUID();
//        wayBill.setBillCode(uuid.toString().toUpperCase());
//        wayBill.setBillId(11L);
        wayBill.setAmountOfPackages(12);
        wayBill.setDeliveryTime(new Date());
        wayBill.setPlanArrivalTime(new Date());
        wayBill.setDestination("重庆");
        //  wayBill.setSourceCode("ssss");
        wayBill.setLogisticsCompanyName("test");
        // 运单类型
        //  wayBill.setBillType(BillTypeEnum.TRANSMIT);///
        //
        // wayBill.setBillState(BillStateEnum.SAVED);
        // wayBill.setBillDetails(null);

        wayBillManager.createWayBill(wayBill);
    }

}
