
package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;

import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.Calendar;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class SaveCommitTest {
    private Random random = new Random();
    private Calendar calendar = Calendar.getInstance();
    Logger logger = LoggerFactory.getLogger(SaveCommitTest.class);
    @Resource
    private RestockBillManager restockBillManager;
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

    protected AddRestockBillDTO createBill() {
        AddRestockBillDTO dto = new AddRestockBillDTO();

        dto.setBillCode("0101" + random.nextInt(1000));
        dto.setMemo("Remarks: " + random.nextInt(20) + " ok!");
        dto.setOperatorCode("2200" + random.nextInt(100));


        Station station = new Station("1302" + random.nextInt(10) + "02" + random.nextInt(10));
        station.setStationName("重庆" + random.nextInt(100) + "站");
        station.setStationType(StationType.STORE);
        dto.setStation(station);

        Storage storage = new Storage("00" + random.nextInt(8));
        storage.setStorageName("xx库");
        dto.setStorage(storage);

        List<RestockBillDetailDTO> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            list.add(this.createDetail());
        }
        logger.info("details 的详细数量 ："+list.size());


            dto.setBillDetails(list);

        return dto;
    }
    protected RestockBillDetailDTO  createDetail(){

        RestockBillDetailDTO billDetailDTO = new RestockBillDetailDTO();
        billDetailDTO.setAmount(random.nextInt(100));
        billDetailDTO.setMemo("details remarks:"+random.nextInt(200));
        RawMaterial rawMaterial = new RawMaterial("030201"+random.nextInt(2000));
        Cargo cargo = new Cargo("00205"+random.nextInt(1000));
        cargo.setCargoName("非持久化？"+random.nextInt(100));
        rawMaterial.setCargo(cargo);

        billDetailDTO.setRawMaterial(rawMaterial);
        return  billDetailDTO;

    }

    @Test
    public void saveTest()  {
        for (int i = 0; i < 5; i++) {
            AddRestockBillDTO dto = this.createBill();

            this.restockBillManager.saveBill(dto);

        }


    }
}