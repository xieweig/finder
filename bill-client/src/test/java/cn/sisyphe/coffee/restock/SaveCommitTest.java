
package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillQueryService;
import cn.sisyphe.coffee.bill.domain.restock.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.restock.enums.PropertyEnum;
import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
import cn.sisyphe.coffee.bill.viewmodel.restock.QueryRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;
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

//import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;



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
    private RestockBillQueryService restockBillQueryService;
    @Resource
    private RestockBillRepository restockBillRepository;
    private RandomAccessFile randomAccessFile;
    public static final String[] PLANCODES = {"010523","010507","010702"};

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
        dto.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);

        dto.setTotalPrice(new BigDecimal(random.nextInt(1000)+500));
        dto.setBillProperty(PropertyEnum.RESTOCK);

        dto.setSourceCode(this.PLANCODES[0]);

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
    public static void setUp(){

    }
    //测试拣货界面保存
    @Test
    public void saveRestockTest() {
        for (int i = 0; i < 6; i++) {
            AddRestockBillDTO dto = this.createBill();

            this.restockBillManager.saveBill(dto);

        }

    }

    //测试拣货界面提交
    @Test
    public void submitRestockTest() {
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

   //测试多条件查询
    @Test
    public void selectByConditions(){
        ConditionQueryRestockBill queryRestockBill = new ConditionQueryRestockBill();
        queryRestockBill.setBillCode("98650302");
        Date basicDate = new Date();
        calendar.setTime(basicDate);

        calendar.add(Calendar.DATE, 2);
        queryRestockBill.setInStartTime(calendar.getTime());
        calendar.add(Calendar.DATE, random.nextInt(20)+5);
        queryRestockBill.setInEndTime(calendar.getTime());
        calendar.add(Calendar.DATE,random.nextInt(20)+10 );
        queryRestockBill.setCreateStartTime(calendar.getTime());
        calendar.add(Calendar.DATE,random.nextInt(20)+5);
        queryRestockBill.setCreateEndTime(calendar.getTime());
     //   queryRestockBill.setCreateStartTime();
     //   queryRestockBill.setCreateEndTime();
        queryRestockBill.setPage(1);
        queryRestockBill.setPageSize(2);
        QueryRestockBillDTO queryRestockBillDTO = this.restockBillManager.findByConditions(queryRestockBill);
        logger.info(queryRestockBillDTO.toString());
    }

}

