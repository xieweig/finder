
package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;

import cn.sisyphe.coffee.bill.domain.base.model.enums.*;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;

import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillQueryService;
import cn.sisyphe.coffee.bill.domain.restock.enums.PropertyEnum;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;


import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;


import javafx.scene.paint.Material;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;

import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.*;
import java.util.Calendar;
import java.util.Random;

import static cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum.BY_CARGO;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/5
 * @Description: manager 级别上的测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class SaveCommitTest {
    private Random random = new Random();
    private Calendar calendar = Calendar.getInstance();
    Logger logger = LoggerFactory.getLogger(SaveCommitTest.class);
    @Resource
    private RestockBillManager restockBillManager;
    @Resource
    RestockBillQueryService restockBillQueryService;

    private RandomAccessFile randomAccessFile;


    /**
     * notes :
     * 随机生成 AddRestockBillDTO 的对象
     */

//    @Before
//    public void init() {
//        try {
//            this.randomAccessFile = new RandomAccessFile("E:/work_files/test/test_restock.txt", "rw");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
    //生成一个bill dto
    private AddRestockBillDTO createBill() {
        AddRestockBillDTO dto = new AddRestockBillDTO();

        //dto.setBillCode("0101" + random.nextInt(1000));后台生成 update delete select 用到
        dto.setOutMemo("Remarks: " + random.nextInt(20) + " ok!");
        dto.setPlanMemo("Remarks: " + random.nextInt(100) + " ok!");
        dto.setOperatorCode("2200" + random.nextInt(100));
        dto.setBasicEnum(cn.sisyphe.coffee.bill.domain.restock.enums.BasicEnum.BY_CARGO);
        dto.setTotalPrice(new BigDecimal(random.nextInt(1000)+500));
        dto.setBillProperty(PropertyEnum.RESTOCK);


        Station station = new Station("1302" + random.nextInt(10) + "02" + random.nextInt(10));
        Storage inStorage = new Storage("01" + random.nextInt(80));
        inStorage.setStorageCode("6611"+random.nextInt(100));
        station.setStationName("重庆" + random.nextInt(100) + "站");
        station.setStationCode("88"+random.nextInt(122));
        station.setStationType(StationType.STORE);
        station.setStorage(inStorage);

        dto.setInStation(station);

        Station outStation = new Station("1515"+random.nextInt(100));
        Storage outStorage = new Storage("00" + random.nextInt(80));
        outStorage.setStorageName("xx库");
        outStorage.setStorageCode("66"+random.nextInt(100));
        station.setStationType(StationType.STORE);
        station.setStationCode("8822"+random.nextInt());
        outStation.setStorage(outStorage);

        dto.setOutStation(outStation);

        Set<RestockBillDetailDTO> list = new HashSet<>();
        for (int i = 0; i < 3; i++) {

            list.add(this.createDetail());
        }
        logger.info("details 的详细数量 ：" + list.size());


        dto.setBillDetails(list);

        return dto;
    }

    //生成一个detail dto
    private RestockBillDetailDTO createDetail() {

        RestockBillDetailDTO billDetailDTO = new RestockBillDetailDTO();
        billDetailDTO.setActualAmount(random.nextInt(100));
        billDetailDTO.setMemo("details remarks:" + random.nextInt(200));
        RawMaterial rawMaterial = new RawMaterial("030201" + random.nextInt(2000));
        Cargo cargo = new Cargo("00205" + random.nextInt(1000));
        cargo.setCargoName("非持久化？" + random.nextInt(100));
        rawMaterial.setCargo(cargo);

        billDetailDTO.setRawMaterial(rawMaterial);
        return billDetailDTO;

    }

    //测试拣货界面保存
    @Test
    public void saveTest() {
        for (int i = 0; i < 5; i++) {
            AddRestockBillDTO dto = this.createBill();

            this.restockBillManager.saveBill(dto);

        }

    }

    //测试拣货界面提交
    @Test
    public void submitTest() {
        for (int i = 0; i < 4; i++) {
            AddRestockBillDTO dto = this.createBill();
            this.restockBillManager.submitBill(dto);
        }
    }

    @Test
    public void findOneTest() {
        String billCode = "7750302";

        RestockBill restockBill = restockBillQueryService.findByBillCode(billCode);

        logger.info(restockBill.toString());

        logger.info("cargo_code before: " + restockBill.getBillDetails().iterator().next().getGoods().code());

        AddRestockBillDTO dto = this.createBill();

        dto.setBillCode(billCode);

        this.restockBillManager.updateBillToSave(dto);

        restockBill = restockBillQueryService.findByBillCode(billCode);
        logger.info(restockBill.toString());
        logger.info("cargo_code before: " + restockBill.getBillDetails().iterator().next().getGoods().code());

        // logger.info(one.toString());13023024  00205176 {cargoCode='00205176', rawMaterialCode='03020199'}

    }

    //测试修改界面修改
    @Test
    public void updateTest() {

        AddRestockBillDTO dto = this.createBill();
        dto.setBillCode("03020201");
        // dto.setBillCode();
    }

    @Resource
    private PlanBillRepository planBillRepository;

    private PlanBillDetail createPlanBillDetail() {
        PlanBillDetail planBillDetail = new PlanBillDetail();
        planBillDetail.setAmount(random.nextInt(100));
        planBillDetail.setPackageCode("test:03" + random.nextInt(100));
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setCargo(new Cargo("3002" + random.nextInt()));
        rawMaterial.setRawMaterialName("测试原料名称" + random.nextInt(1000));
        planBillDetail.setGoods(rawMaterial);
        planBillDetail.setOutLocation(new Storage("03021" + random.nextInt(100)));
        return planBillDetail;
    }

    private PlanBill createPlanBill() {
        PlanBill planBill = new PlanBill();
        planBill.setHqBill(false);
        planBill.setSpecificBillType(BillTypeEnum.PLAN);
        planBill.setAuditMemo("remarks: " + random.nextInt(1000));
        planBill.setBasicEnum(BasicEnum.BY_MATERIAL);
        planBill.setBillName("all" + random.nextInt(100));
        planBill.setProgress(new BigDecimal(random.nextInt(1200)));
        planBill.setBillState(BillStateEnum.SAVED);
        planBill.setAuditState(BillAuditStateEnum.AUDIT_SUCCESS);
        planBill.setSubmitState(BillSubmitStateEnum.SUBMITTED);
        planBill.setAuditPersonCode(random.nextInt(100) + "0302");
        planBill.setBillCode("010" + random.nextInt(1000));
        planBill.setBillType(BillTypeEnum.PLAN);
        Set<PlanBillDetail> details = new HashSet<>();
        for (int i = 0; i < 3; i++) {

            details.add(this.createPlanBillDetail());
        }
        planBill.setBillDetails(details);
        return  planBill;
    }
    @Test
    public void insertTest(){
        for (int i = 0; i <6 ; i++) {
           this.planBillRepository.save(this.createPlanBill());
        }
    }
}

