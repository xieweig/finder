package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillSubmitStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.math.BigDecimal;
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

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private BufferedInputStream bufferedInputStream;
    //依赖数据库表内容 plan表的bill code
    public static final String[] PLANCODES = {"010293", "010160", "010476"};
    public static final String[] ROOTCODES = {"010293", "010160", "010476"};
    public static final String[] CARGOCODE = {"AD","SSA","SDS","WEA"};
    public static final String[] RAWMATERIALCODE = {"2","7","8","9"};

    //默认一个bill三个detail
    public AddRestockBillDTO nextRandomRestockBillDTO() {
        return this.nextRandomRestockBillDTO(3);
    }

    public AddRestockBillDTO nextRandomRestockBillDTO(Integer detailsSize) {

        AddRestockBillDTO dto = new AddRestockBillDTO();

        //dto.setBillCode("0101" + random.nextInt(1000));后台生成 update delete select 用到
        //一般性字段
        dto.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);
        dto.setBillProperty(SourcePlanTypeEnum.RESTOCK);
        //here attention!!
        dto.setSourceCode(this.PLANCODES[random.nextInt(PLANCODES.length)]);
        dto.setRootCode(this.ROOTCODES[random.nextInt(ROOTCODES.length)]);

        dto.setProgress(new BigDecimal(random.nextInt(100) + 1));
        ;
        dto.setOutMemo("OutRemarks: " + random.nextInt(20) + " ok!");
        dto.setPlanMemo("PlanRemarks: " + random.nextInt(100) + " ok!");
        dto.setOperatorCode("2200" + random.nextInt(100));
        dto.setTotalPrice(new BigDecimal(random.nextInt(1000) + 500));
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

    private RestockBillDetailDTO nextRandomRestockDetailDTO() {

        RestockBillDetailDTO billDetailDTO = new RestockBillDetailDTO();
        billDetailDTO.setActualAmount(random.nextInt(100));
        billDetailDTO.setMemo("details remarks:" + random.nextInt(200));
        RawMaterial rawMaterial = new RawMaterial(RAWMATERIALCODE[random.nextInt(RAWMATERIALCODE.length)]);
        Cargo cargo = new Cargo(CARGOCODE[random.nextInt(CARGOCODE.length)]);

        cargo.setCargoName("cargoName:" + random.nextInt(100));
        rawMaterial.setCargo(cargo);


        billDetailDTO.setRawMaterial(rawMaterial);
        return billDetailDTO;


    }

    private Station nextRandomStation() {
        Station station = new Station("1302" + random.nextInt(10) + "02" + random.nextInt(10));
        Storage storage = new Storage("01" + random.nextInt(80));
        storage.setStorageCode("6611" + random.nextInt(100));
        station.setStationName("重庆" + random.nextInt(100) + "站");
        station.setStationCode("88" + random.nextInt(122));
        station.setStationType(StationType.STORE);
        station.setStorage(storage);
        return station;
    }

    public RestockBill nextRandomRestockBill(Integer detailsSize) {
        BillFactory factory = new BillFactory();
        RestockBill restockBill = (RestockBill) factory.createBill(BillTypeEnum.RESTOCK);
        restockBill.setBillCode("" + random.nextInt(9000) + 10000);
        restockBill.setBillPurpose(BillPurposeEnum.OutStorage);
        restockBill.setProgress(new BigDecimal(random.nextInt(100) + 1));
        restockBill.setTotalPrice(new BigDecimal(random.nextInt(600) + 100));
        restockBill.setTotalAmount(random.nextInt(100));
        restockBill.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);
        restockBill.setSubmitState(BillSubmitStateEnum.values()[random.nextInt(BillSubmitStateEnum.values().length)]);

        restockBill.setRootCode("" + random.nextInt(1000) + 100);
        restockBill.setRootCode("" + random.nextInt(1000) + 100);
        restockBill.setInLocation(new Station());
        restockBill.setOutLocation(new Station());
        Set<RestockBillDetail> billDetails = new HashSet<>();
        for (int i = 0; i < 2; i++) {
            billDetails.add(this.nextRandomBillDetail());
        }

        restockBill.setBillDetails(billDetails);
        return restockBill;
    }

    private RestockBillDetail nextRandomBillDetail() {
        RawMaterial rawMaterial = new RawMaterial("030201" + random.nextInt(2000));
        Cargo cargo = new Cargo("00205" + random.nextInt(1000));
        cargo.setCargoName("cargoName:" + random.nextInt(100));
        rawMaterial.setCargo(cargo);

        RestockBillDetail billDetail = new RestockBillDetail();
        billDetail.setGoods(rawMaterial);
        billDetail.setProgress(new BigDecimal(random.nextInt(100)));
        return billDetail;
    }

}
