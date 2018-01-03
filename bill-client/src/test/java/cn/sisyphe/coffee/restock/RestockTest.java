package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
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
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

/**
 *@date: 2018/1/2
 *@description: for Restock Test
 *@author：xieweiguang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class RestockTest {
    Logger logger = LoggerFactory.getLogger(RestockTest.class);
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;
    @Resource
    private RestockBillRepository restockBillRepository;
    private Random random = new Random();
    private Calendar calendar =Calendar.getInstance();
    private void setDate(RestockBill restockBill){


        Date date = new Date();
        calendar.setTime(date);

        logger.info(calendar.getTime().toString());

        if (restockBill.getInStorageEndTime()==null) {

            calendar.add(Calendar.HOUR, 11);
            restockBill.setInStorageStartTime(calendar.getTime());
        }

        if (restockBill.getInStorageStartTime()==null){

            calendar.add(Calendar.HOUR,18);
            restockBill.setInStorageEndTime(calendar.getTime());
        }

        if (restockBill.getOutStorageStartTime()==null){

            calendar.add(Calendar.DATE, 2);
            restockBill.setOutStorageStartTime(calendar.getTime());
        }
        if (restockBill.getOutStorageEndTime()==null){

            calendar.add(Calendar.HOUR,12);
            restockBill.setOutStorageEndTime(calendar.getTime());
        }
    }

    @Test
    public void Juice(){

        RestockBill restockBill = new RestockBill();
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);

        calendar.add(Calendar.HOUR, 11);
        logger.info(calendar.getTime().toString());
        restockBill.setInStorageStartTime(calendar.getTime());

        calendar.add(Calendar.HOUR,18);
        logger.info(calendar.getTime().toString());
        restockBill.setInStorageEndTime(calendar.getTime());

        calendar.add(Calendar.DATE, 2);
        logger.info(calendar.getTime().toString());
        restockBill.setOutStorageStartTime(calendar.getTime());

        calendar.add(Calendar.HOUR,12);
        logger.info(calendar.getTime().toString());
        restockBill.setOutStorageEndTime(calendar.getTime());
        //前段页面隐形要求
        restockBill.setBillState(BillStateEnum.SAVED);
        restockBill.setBillPurpose(BillPurposeEnum.OutStorage);
        restockBill.setBillType(BillTypeEnum.RESTOCK);
        //前段页面显性要求

        restockBill.setBillCode("030212004");
        //restockBill.setStorageType();
        //正常库在途库等
        restockBill.setRemarks("ba_la_ba_la");

        RawMaterial rawMaterial_first = new RawMaterial("Raw111"+random.nextInt(100));
        rawMaterial_first.setCargo(new Cargo("cargo00"+random.nextInt(10)));


        RestockBillDetail restockBillDetail_first = new RestockBillDetail();
        restockBillDetail_first.setTotalPrice(random.nextInt(100));
        restockBillDetail_first.setAmount(random.nextInt(20));
        restockBillDetail_first.setGoods(rawMaterial_first);

        RawMaterial rawMaterial_second = new RawMaterial("Raw111"+random.nextInt(100));
        rawMaterial_second.setCargo(new Cargo("cargo00"+random.nextInt(10)));

        RestockBillDetail restockBillDetail_second = new RestockBillDetail();
        restockBillDetail_second.setTotalPrice(random.nextInt(100));
        restockBillDetail_second.setAmount(random.nextInt(20));
        restockBillDetail_second.setGoods(rawMaterial_second);

        Set<RestockBillDetail> details = new HashSet<>();
        details.add(restockBillDetail_first);
        details.add(restockBillDetail_second);

        restockBill.setBillDetails(details);



        BillServiceFactory billServiceFactory = new BillServiceFactory();
        RestockBillService billService =(RestockBillService) billServiceFactory.createBillService(restockBill);
        billService.dispose(new SaveBehavior());

        billService.setBillRepository(restockBillRepository);
        billService.save();
        billService.sendEvent(applicationEventPublisher);

    }
    @Resource
    private RestockBillManager restockBillManager;
    @Test
    public void Orange(){
        SaveByRestockBillDTO saveByRawMaterialDTO = new SaveByRestockBillDTO();
        saveByRawMaterialDTO.setBillCode("0802104"+random.nextInt(10));
        saveByRawMaterialDTO.setCreateTime(new Date());
        saveByRawMaterialDTO.setInStationCode("10001"+random.nextInt(10));
        saveByRawMaterialDTO.setOutStationCode("20001"+random.nextInt(10));
        saveByRawMaterialDTO.setRemarks("bill remarks");


            List<RestockBillDetailsDTO> list = new ArrayList<>();
            RestockBillDetailsDTO first = new RestockBillDetailsDTO();
            first.setRawMaterialName("milk");
            first.setRawMaterialCode("8002"+random.nextInt(10));
            first.setCargoName("menu");
            first.setCargoCode("80003"+random.nextInt(10));
            first.setAmount(500);
            first.setPackageCode("bottle");
            first.setActualNumber(10);
            first.setCargoRemarks("only"+random.nextInt(100)+" left ");

            list.add(first);
            RestockBillDetailsDTO second = new RestockBillDetailsDTO();
            second.setRawMaterialName("milk");
            second.setRawMaterialCode("7002"+random.nextInt(10));
            second.setCargoName("menu");
            second.setCargoCode("70003"+random.nextInt(10));
            second.setAmount(500);
            second.setPackageCode("bottle");
            second.setActualNumber(10);
            second.setCargoRemarks("only"+random.nextInt(100)+" left ");
            list.add(second);

        saveByRawMaterialDTO.setBillDetails(list);

        restockBillManager.saveByRawMaterial(saveByRawMaterialDTO);


    }
}
