/*
package cn.sisyphe.coffee.returned;

import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.viewmodel.returned.AddReturnedBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

*/
/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/9
 * @Description:
 *//*


public class InstanceIterator {

    private Random random = new Random();

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private BufferedInputStream bufferedInputStream;
    public static final String[] PLANCODES = {"010293", "010160", "010476"};
    public static final String[] ROOTCODES = {"010293", "010160", "010476"};
    public static final String[] CARGOCODE = {"AD","SSA","SDS","WEA"};
    public static final String[] RAWMATERIALCODE = {"2","7","8","9"};
    //默认一个bill三个detail
    public AddReturnedBillDTO nextRandomAddReturnedBillDTO() {
        return this.nextRandomRestockBillDTO(3);
    }

    public AddReturnedBillDTO nextRandomRestockBillDTO(Integer detailsSize) {

        AddReturnedBillDTO dto = new AddReturnedBillDTO();

        //dto.setBillCode("0101" + random.nextInt(1000));后台生成 update delete select 用到
        //一般性字段
        dto.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);
        dto.setBillProperty(SourcePlanTypeEnum.RESTOCK);
        //here attention!!
        dto.setSourceCode(this.PLANCODES[random.nextInt(PLANCODES.length)]);
//        dto.setRootCode(this.ROOTCODES[random.nextInt(ROOTCODES.length)]);
//
//        dto.setProgress(new BigDecimal(random.nextInt(100)+1));
        ;
        dto.setOutMemo("OutRemarks: " + random.nextInt(20) + " ok!");
        dto.setPlanMemo("PlanRemarks: " + random.nextInt(100) + " ok!");
        dto.setOperatorCode("2200" + random.nextInt(100));
        dto.setTotalPrice(new BigDecimal(random.nextInt(1000) + 500));
        dto.setInStation(this.nextRandomStation());
        dto.setOutStation(this.nextRandomStation());

        Set<ReturnedBillDetailDTO> billDetails = new HashSet<>();
        for (int i = 0; i < detailsSize; i++) {

            billDetails.add(this.nextRandomReturnedDetailDTO());
        }

        dto.setBillDetails(billDetails);
        logger.info("details 的详细数量 ：" + billDetails.size());
        logger.info(ToStringBuilder.reflectionToString(dto, ToStringStyle.SHORT_PREFIX_STYLE));
        return dto;

    }

    private ReturnedBillDetailDTO nextRandomReturnedDetailDTO() {

        ReturnedBillDetailDTO billDetailDTO = new ReturnedBillDetailDTO();
        int amount = random.nextInt(100);
        billDetailDTO.setShippedAmount(amount);
        billDetailDTO.setActualAmount(amount+random.nextInt(6)-3);

        billDetailDTO.setMemo("details remarks:" + random.nextInt(200));
        RawMaterial rawMaterial = new RawMaterial(RAWMATERIALCODE[random.nextInt(RAWMATERIALCODE.length)]);
        Cargo cargo = new Cargo(CARGOCODE[random.nextInt(CARGOCODE.length)]);
        cargo.setCargoName("cargoName:" + random.nextInt(100));
        rawMaterial.setCargo(cargo);
        billDetailDTO.setRawMaterial(rawMaterial);


        return billDetailDTO;


    }

    private Station nextRandomStation() {
        Station station = new Station("2302" + random.nextInt(10) + "02" + random.nextInt(10));
        Storage storage = new Storage("01" + random.nextInt(80));
        storage.setStorageCode("6611" + random.nextInt(100));
        station.setStationName("重庆" + random.nextInt(100) + "站");
        station.setStationCode("88" + random.nextInt(122));
        station.setStationType(StationType.STORE);
        station.setStorage(storage);
        return station;
    }
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    //billode 生成器
    private String nextBillCode(){

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("TKCK");
        stringBuffer.append("CKG001");

        stringBuffer.append(simpleDateFormat.format(new Date()));
        stringBuffer.append("P10");
        stringBuffer.append(""+(random.nextInt(800000)+100000));
        return stringBuffer.toString();
    }
}
*/
