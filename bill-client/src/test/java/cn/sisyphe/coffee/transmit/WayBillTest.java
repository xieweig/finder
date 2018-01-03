package cn.sisyphe.coffee.transmit;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.application.transmit.WayBillManager;
import cn.sisyphe.coffee.bill.domain.transmit.WayBill;
import cn.sisyphe.coffee.bill.domain.transmit.WayBillService;
import cn.sisyphe.coffee.bill.domain.transmit.enums.PackAgeTypeEnum;
import cn.sisyphe.coffee.bill.domain.transmit.enums.ReceivedStatusEnum;
import cn.sisyphe.coffee.bill.infrastructure.transmit.WayBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.waybill.ConditionQueryWayBill;
import cn.sisyphe.coffee.bill.viewmodel.waybill.EditWayBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.waybill.EditWayBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.waybill.QueryWayBillDTO;
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
    private WayBillService iWayBillService;


    @Autowired
    WayBillRepository wayBillRepository;

    @Test
    public void testFindByCode() {

        wayBillRepository.findOneByCode("a");
    }

    /**
     * 查找单个
     */
    @Test
    public void testFindOneByConditions() {
        WayBill wayBill = new WayBill();
        wayBill.setBillId(2L);

        List<WayBill> wayBills = iWayBillService.findByConditions(wayBill);
        for (WayBill item : wayBills) {
            System.out.println("" + item.toString());
        }

    }

    /**
     * 多条件分页查询
     */
    @Test
    public void testFindByConditions() {

        ConditionQueryWayBill conditionQueryWayBill = new ConditionQueryWayBill();
        conditionQueryWayBill.setOperatorName("小明");
        // conditionQueryWayBill.setInStationCode("jd");
        conditionQueryWayBill.setPageSize(100);
        //conditionQueryWayBill.setLogisticsCompanyName("韵达");
        //conditionQueryWayBill.setInStationCode("cq12");
        conditionQueryWayBill.setPage(1);

        QueryWayBillDTO queryWayBillDTO = wayBillManager.findPageByCondition(conditionQueryWayBill);


        System.out.println("" + queryWayBillDTO.getContent());
    }


    /**
     *
     */
    @Test
    public void testFindByPage() {
        ConditionQueryWayBill conditionQueryWayBill = new ConditionQueryWayBill();
        conditionQueryWayBill.setOperatorName("小明");
        conditionQueryWayBill.setInStationCode("jd");
        conditionQueryWayBill.setPageSize(100);
        conditionQueryWayBill.setLogisticsCompanyName("韵达");
        conditionQueryWayBill.setInStationCode("cq12");
        conditionQueryWayBill.setPage(1);
        Page<WayBill> billPage = iWayBillService.findPageByCondition(conditionQueryWayBill);
        if (billPage != null) {
            System.out.println(billPage.getContent());
        }

    }


    /**
     * 修改
     */
    @Test
    public void testUpdateWillBill() {

        // UUID uuid = UUID.randomUUID();
        EditWayBillDTO editWayBillDTO = new EditWayBillDTO();
        //
        editWayBillDTO.setMemo("test");
        editWayBillDTO.setBillId(2L);
        editWayBillDTO.setAmountOfPackages(122);
        editWayBillDTO.setDeliveryTime(new Date());
        editWayBillDTO.setDestination("北京45");

        editWayBillDTO.setOperatorName("小明2");
        editWayBillDTO.setLogisticsCompanyName("韵达");
        editWayBillDTO.setPlanArrivalTime(new Date());//预计到达时间
        editWayBillDTO.setAmountOfPackages(155);
        editWayBillDTO.setReceivedStatus(ReceivedStatusEnum.IS_NOT_RECEIVED);//收货状态

        editWayBillDTO.setMemo("修改了");
        //id
//        editWayBillDTO.setWayBillCode(uuid.toString());

        List<EditWayBillDetailDTO> editWayBillDetailDTOList = new ArrayList<>();
        //List<EditWayBillDetailDTO> editWayBillRemoveDetailDTOList = new ArrayList<>();

        //
        EditWayBillDetailDTO wayBillDetailDTO1 = new EditWayBillDetailDTO(editWayBillDTO);

        wayBillDetailDTO1.setBillDetailId(9L);
        wayBillDetailDTO1.setPackageNumbers("sss");//包号
        wayBillDetailDTO1.setOutStorageBillCode("kkkkk");
        wayBillDetailDTO1.setOperatorName("sss");
        wayBillDetailDTO1.setOutStationCode("cq12");
        wayBillDetailDTO1.setSinglePacking(false);
        wayBillDetailDTO1.setTotalAmount(10);

        wayBillDetailDTO1.setTotalCount(55);
        wayBillDetailDTO1.setOutStorageTime(new Date());
        wayBillDetailDTO1.setEditWayBillDTO(editWayBillDTO);

        // delete
        EditWayBillDetailDTO wayBillDetailDTO2 = new EditWayBillDetailDTO(editWayBillDTO);
        wayBillDetailDTO2.setBillDetailId(12L);

        wayBillDetailDTO2.setEditWayBillDTO(editWayBillDTO);
        //
        EditWayBillDetailDTO wayBillDetailDTO3 = new EditWayBillDetailDTO(editWayBillDTO);

        //wayBillDetailDTO1.setBillDetailId(1L);
        wayBillDetailDTO3.setOutStorageBillCode("sxfsdfsdf");
        wayBillDetailDTO3.setOperatorName("aaaaaaaaaaaaaa");
        wayBillDetailDTO3.setOutStationCode("fsdfsdfsdfsdf");
//        wayBillDetailDTO3

        wayBillDetailDTO3.setSinglePacking(false);
        wayBillDetailDTO3.setTotalAmount(10);
        wayBillDetailDTO3.setPackageNumbers("aaaaaaaaaaaaaaa");//包号
        wayBillDetailDTO3.setTotalCount(55);
        wayBillDetailDTO3.setOutStorageTime(new Date());
        wayBillDetailDTO3.setEditWayBillDTO(editWayBillDTO);

        editWayBillDetailDTOList.add(wayBillDetailDTO1);//add
        editWayBillDTO.setEditWayBillDetailDTOList(editWayBillDetailDTOList);// add
        wayBillManager.updateWayBillWithDTO(editWayBillDTO);

    }


    /**
     * 添加
     */
    @Test
    public void testCreateDto() {

        //
        UUID uuid = UUID.randomUUID();
        EditWayBillDTO editWayBillDTO = new EditWayBillDTO();
        editWayBillDTO.setMemo("ssssssssss");
        editWayBillDTO.setAmountOfPackages(122);
        editWayBillDTO.setDeliveryTime(new Date());
        editWayBillDTO.setDestination("北京");
        editWayBillDTO.setOperatorName("小明");
        editWayBillDTO.setLogisticsCompanyName("韵达快递");
        editWayBillDTO.setPlanArrivalTime(new Date());//预计到达时间
        editWayBillDTO.setAmountOfPackages(155);
        editWayBillDTO.setOutStationCode("cq12");
        // 状态
        //id
        editWayBillDTO.setWayBillCode(uuid.toString());

        List<EditWayBillDetailDTO> editWayBillDetailDTOList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            EditWayBillDetailDTO editWayBillDetailDTO = new EditWayBillDetailDTO(editWayBillDTO);
            editWayBillDetailDTO.setOutStorageTime(new Date());
            editWayBillDetailDTO.setInStationCode("cq11");
            editWayBillDetailDTO.setOutStationCode("cq12");
            editWayBillDetailDTO.setPackageNumbers("cq14");
            editWayBillDetailDTO.setOperatorName("小明");
            editWayBillDetailDTO.setTotalAmount(10);
            //
            editWayBillDetailDTO.setOutStorageBillCode("TH0002");// 出库单号
            editWayBillDetailDTO.setTotalCount(6);
            //
            editWayBillDetailDTO.setPackageType(PackAgeTypeEnum.ONE_BILL_TO_ONE_PACKAGE.name().toString());
            editWayBillDetailDTOList.add(editWayBillDetailDTO);
        }
        editWayBillDTO.setEditWayBillDetailDTOList(editWayBillDetailDTOList);
        //
        wayBillManager.createWayBillWithDTO(editWayBillDTO);
    }


}
