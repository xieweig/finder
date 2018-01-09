package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.restock.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.restock.enums.PropertyEnum;
import cn.sisyphe.coffee.bill.viewmodel.purchase.BillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/9
 * @Description:该组件用来生成测试使用的各种实体类 和 DTO
 */

public class InstanceFactory {
    private Random random = new Random();
//    private Calendar calendar = Calendar.getInstance();
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private BufferedInputStream bufferedInputStream;
    public static final String[] PLANCODES = {"010638","010280","010487"};

    //默认一个bill三个detail
    public AddRestockBillDTO nextRandomRestockBillDTO(){
       return this.nextRandomRestockBillDTO(3);
    }
    public AddRestockBillDTO nextRandomRestockBillDTO(Integer detailsSize){

        AddRestockBillDTO dto = new AddRestockBillDTO();

        //dto.setBillCode("0101" + random.nextInt(1000));后台生成 update delete select 用到
        //一般性字段
        dto.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);
        dto.setBillProperty(PropertyEnum.RESTOCK);
        //here attention!!
        dto.setSourceCode(this.PLANCODES[1]);
       // dto.setRootCode();

        dto.setProgress(new BigDecimal(random.nextInt(100)+1));
        ;
        dto.setOutMemo("OutRemarks: " + random.nextInt(20) + " ok!");
        dto.setPlanMemo("PlanRemarks: " + random.nextInt(100) + " ok!");
        dto.setOperatorCode("2200" + random.nextInt(100));
        dto.setTotalPrice(new BigDecimal(random.nextInt(1000)+500));
        dto.setInStation(this.nextRandomStation());
        dto.setOutStation(this.nextRandomStation());

        Set<RestockBillDetailDTO> billDetails = new HashSet<>();
        for (int i = 0; i < detailsSize; i++) {

            billDetails.add(this.nextRandomRestockDetailDTO());
        }

        dto.setBillDetails(billDetails);
        logger.info("details 的详细数量 ：" + billDetails.size());
        logger.info(ToStringBuilder.reflectionToString(dto, ToStringStyle.SHORT_PREFIX_STYLE));
        return dto;

    }

    private RestockBillDetailDTO nextRandomRestockDetailDTO(){

        RestockBillDetailDTO billDetailDTO = new RestockBillDetailDTO();
        billDetailDTO.setActualAmount(random.nextInt(100));
        billDetailDTO.setMemo("details remarks:" + random.nextInt(200));
        RawMaterial rawMaterial = new RawMaterial("030201" + random.nextInt(2000));
        Cargo cargo = new Cargo("00205" + random.nextInt(1000));
        cargo.setCargoName("cargoName:" + random.nextInt(100));
        rawMaterial.setCargo(cargo);


        billDetailDTO.setRawMaterial(rawMaterial);
        return billDetailDTO;


    }
    private Station  nextRandomStation(){
        Station station = new Station("1302" + random.nextInt(10) + "02" + random.nextInt(10));
        Storage storage = new Storage("01" + random.nextInt(80));
        storage.setStorageCode("6611"+random.nextInt(100));
        station.setStationName("重庆" + random.nextInt(100) + "站");
        station.setStationCode("88"+random.nextInt(122));
        station.setStationType(StationType.STORE);
        station.setStorage(storage);
        return station;
    }

}
