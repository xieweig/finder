package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillService;
import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailsDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.SaveByRestockBillDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

/**
 *@date: 2018/1/4
 *@description:
 *@author：xieweiguang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class MondayTest {
    Logger logger = LoggerFactory.getLogger(RestockTest.class);
    @Resource
    private RestockBillManager restockBillManager;
    @Resource
    private RestockBillRepository restockBillRepository;
    private Random random = new Random();
    private Calendar calendar =Calendar.getInstance();
    private BillFactory billFactory = new BillFactory();
    /**
     *
     *notes :
     *  最简单的后端保存，熟悉基本流程
     */
    @Test
    public void overFlow(){
        RestockBill restockBill = (RestockBill) billFactory.createBill(BillTypeEnum.RESTOCK);
        restockBill.setBillPurpose(BillPurposeEnum.OutStorage);
        restockBill.setBillType(BillTypeEnum.RESTOCK);

        restockBill.setBillCode("030212025"+random.nextInt(100));

        RestockBillDetail restockBillDetail = new RestockBillDetail();
        restockBillDetail.setDetailsRemarks("all right");
        Cargo cargo = new Cargo("cargocode 12120");
        restockBillDetail.setGoods(cargo);
        Set<RestockBillDetail> details = new HashSet();
        details.add(restockBillDetail);

        restockBill.setBillDetails(details);

        BillServiceFactory billServiceFactory = new BillServiceFactory();
        RestockBillService service =(RestockBillService) billServiceFactory.createBillService(restockBill);
        service.dispose(new SaveBehavior());
        service.setBillRepository(restockBillRepository);
        service.save();


    }
    /**
     *
     *notes :
     *  提交测试 本质与保存相同 并不是update 而是dto一步到位状态为submit而已
     */
    @Test
    public void apple(){
        SaveByRestockBillDTO saveByRestockBillDTO = new SaveByRestockBillDTO();
        saveByRestockBillDTO.setBillCode("030202250"+random.nextInt(100));
        saveByRestockBillDTO.setInStationCode("0905"+random.nextInt(100));
        saveByRestockBillDTO.setOutStationCode("0901"+random.nextInt(100));
        saveByRestockBillDTO.setReadyByCargo(true);
        saveByRestockBillDTO.setRemarks("tall_pine_trees");

        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 5);
        saveByRestockBillDTO.setCreateTime(calendar.getTime());
            List<RestockBillDetailsDTO> list = new ArrayList();

            RestockBillDetailsDTO billDetail_1 = new RestockBillDetailsDTO();
            billDetail_1.setCargoCode("030212"+random.nextInt(100));
            billDetail_1.setCargoName("milk");
            list.add(billDetail_1);

            RestockBillDetailsDTO billDetail_2 = new RestockBillDetailsDTO();
            billDetail_2.setCargoCode("040210"+random.nextInt(120));
            billDetail_2.setCargoName("chocolate");
            list.add(billDetail_2);

        saveByRestockBillDTO.setBillDetails(list);
        restockBillManager.submitByRestockBill(saveByRestockBillDTO);


    }
}
