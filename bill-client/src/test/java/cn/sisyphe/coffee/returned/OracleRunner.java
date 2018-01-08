package cn.sisyphe.coffee.returned;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.application.returned.ReturnedBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBill;
import cn.sisyphe.coffee.bill.domain.returned.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.returned.enums.PropertyEnum;
import cn.sisyphe.coffee.bill.infrastructure.returned.ReturnedBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.AddReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDetailDTO;
import cn.sisyphe.coffee.restock.SaveCommitTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/7
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class OracleRunner {
    private Random random = new Random();
    private Calendar calendar = Calendar.getInstance();
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ReturnedBillManager returnedBillManager;
    private ReturnedBillRepository returnedBillRepository;
    private String[] names = {"milk", "coffee", "cheese", "beer", "juice", "meat", "duck", "chicken", "goal", "apple"};

    private AddReturnedBillDTO createReturnedBill() {
        AddReturnedBillDTO dto = new AddReturnedBillDTO();

        dto.setBillCode("02" + random.nextInt(10000));
        dto.setOperatorCode("99" + random.nextInt(100));
        dto.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);
        dto.setPlanMemo("plan memo:" + names[random.nextInt(names.length)]);
        dto.setOutMemo("out memo" + names[random.nextInt(names.length)]);
        dto.setTotalPrice(new BigDecimal(random.nextInt(1500) + 600));
        dto.setBillProperty(PropertyEnum.values()[random.nextInt(PropertyEnum.values().length)]);


        Station station = new Station("1302" + random.nextInt(10) + "02" + random.nextInt(10));
        Storage inStorage = new Storage("01" + random.nextInt(80));
        inStorage.setStorageCode("6611" + random.nextInt(100));
        inStorage.setStorageName("正常库");
        station.setStationName("重庆" + random.nextInt(100) + "站");
        station.setStationCode("88" + random.nextInt(122));
        station.setStationType(StationType.STORE);
        station.setStorage(inStorage);

        dto.setInStation(station);

        Station outStation = new Station("1515" + random.nextInt(100));
        Storage outStorage = new Storage("00" + random.nextInt(80));
        outStorage.setStorageName("xx库");
        outStorage.setStorageCode("66" + random.nextInt(100));
        station.setStationType(StationType.STORE);
        station.setStationCode("8822" + random.nextInt());
        outStation.setStorage(outStorage);

        dto.setOutStation(outStation);

        Set<ReturnedBillDetailDTO> list = new HashSet<>();
        for (int i = 0; i < 3; i++) {

            list.add(this.createReturnedDetail());
        }
        logger.info("details 的详细数量 ：" + list.size());


        dto.setBillDetails(list);

        return dto;


    }

    private ReturnedBillDetailDTO createReturnedDetail() {
        ReturnedBillDetailDTO billDetailDTO = new ReturnedBillDetailDTO();


        billDetailDTO.setMemo("details remarks:" + random.nextInt(200));
        RawMaterial rawMaterial = new RawMaterial("030201" + random.nextInt(2000));
        Cargo cargo = new Cargo("00205" + random.nextInt(1000));
        cargo.setCargoName("非持久化？" + random.nextInt(100));
        rawMaterial.setCargo(cargo);

        billDetailDTO.setRawMaterial(rawMaterial);
        return billDetailDTO;
    }
    //测试return bill 保存 提交
    @Test
    public void leafSave() {
        for (int i = 0; i < 3; i++) {

            AddReturnedBillDTO addReturnedBillDTO = this.createReturnedBill();
            this.returnedBillManager.saveBill(addReturnedBillDTO);

        }
    }
    @Test
    public void leafSubmit() {
        for (int i = 0; i < 3; i++) {

            AddReturnedBillDTO addReturnedBillDTO = this.createReturnedBill();
            this.returnedBillManager.submitBill(addReturnedBillDTO);

        }
    }
    //测试 return bill 修改后保存提交
    @Test
    public void alertAndSave() {
        AddReturnedBillDTO addReturnedBillDTO = this.createReturnedBill();
        addReturnedBillDTO.setBillCode("27850302");
        this.returnedBillManager.updateBillToSave(addReturnedBillDTO);

    }
    @Test
    public void alertAndSubmit() {
        AddReturnedBillDTO addReturnedBillDTO = this.createReturnedBill();
        addReturnedBillDTO.setBillCode("82090302");
        this.returnedBillManager.updateBillToSubmit(addReturnedBillDTO);

    }
}