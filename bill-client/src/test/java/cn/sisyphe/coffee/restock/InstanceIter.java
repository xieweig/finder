package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.domain.base.model.enums.*;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;

import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/17
 * @Description:
 */
public class InstanceIter {
    protected Random random = new Random();
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String[] STATIONS = {"WNZA01","HDQA00","HRBA01","HGHB04"};

    public static final String[] STORAGES = {"NORMAL","STORAGE","IN_STORAGE","OUT_STORAGE",
            "ON_STORAGE","RESERVE_STORAGE","NORMAL","NORMAL","NORMAL","NORMAL","NORMAL","NORMAL"};
    public static  String[] ROOT_CODES = {"1516332546264","1516332546238","1516332546228"};

    public static final String[] SOURCE_CODES_FOR_RESTOCK = {"1516332546264","1516332546238","1516332546228"};
    public static final String[] OPERATIONCODE={"YGADMIN"};
    //用来添加货物和原料对应名称 请如下添加 “原料=货物”
    public static final List<String> detailList = new ArrayList();
    static {
        detailList.add("AS=AD");
        detailList.add("HSH=SSA");
        detailList.add("WEA=WEA");
        detailList.add("AS=SDS");
    }

    public RestockBillDTO nextRandomAddRestockBillDTO(Integer detailsNumber){
        RestockBillDTO dto = new RestockBillDTO();
        //可能有误


        dto.setBillPurpose(BillPurposeEnum.OUT_STORAGE);
        Integer sign = random.nextInt(10);
        dto.setRootCode(ROOT_CODES[random.nextInt(ROOT_CODES.length)]);

        if (sign>4){
            dto.setSpecificBillType(BillTypeEnum.RESTOCK);
            dto.setSourceCode(SOURCE_CODES_FOR_RESTOCK[random.nextInt(SOURCE_CODES_FOR_RESTOCK.length)].trim());
        }

        else{
            dto.setSpecificBillType(BillTypeEnum.NO_PLAN);
        }


        dto.setInLocation(this.nextRandomStation());
        dto.setOutLocation(this.nextRandomStation());
        dto.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);


        Set<RestockBillDetailDTO> details = new HashSet();
        for (int i = 0; i < detailsNumber; i++) {
            details.add(this.nextRandomRestockBillDetailDTO());
        }

        dto.setBillDetails(details);

        dto.setOutStorageMemo("出库库位备注："+random.nextInt(100));



        dto.setOperatorCode(OPERATIONCODE[random.nextInt(OPERATIONCODE.length)]);
        dto.setTotalAmount(random.nextInt(10));
        dto.setTotalVarietyAmount(random.nextInt(500)+100);

        return dto;
    }
    protected Station nextRandomStation() {
        Station station = new Station(STATIONS[random.nextInt(STATIONS.length)]);
        Storage storage = new Storage(STORAGES[random.nextInt(STORAGES.length)]);
        station.setStationType(StationType.values()[random.nextInt(StationType.values().length)]);
        station.setStorage(storage);
        System.err.println(ToStringBuilder.reflectionToString(station));
        return station;
    }
    protected RestockBillDetailDTO nextRandomRestockBillDetailDTO(){
        RestockBillDetailDTO restockBillDetailDTO = new RestockBillDetailDTO();

        Integer amount = random.nextInt(100)+20;
        restockBillDetailDTO.setActualAmount(amount);
        restockBillDetailDTO.setShippedAmount(amount+random.nextInt(10)-5);

        String codePair= detailList.get(random.nextInt(detailList.size()));
        String[] strings = codePair.split("=");
        RawMaterial rawMaterial = new RawMaterial(strings[0]);
        Cargo cargo = new Cargo(strings[1]);
        rawMaterial.setCargo(cargo);
        restockBillDetailDTO.setRawMaterial(rawMaterial);
        System.err.println(ToStringBuilder.reflectionToString(restockBillDetailDTO));
        return restockBillDetailDTO;
    }

}
