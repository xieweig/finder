package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.enums.*;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.purpose.BillPurpose;
import cn.sisyphe.coffee.bill.util.BillCodeManager;

import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/17
 * @Description:
 */
public class InstanceIter {
    protected Random random = new Random();
    public static final String[] STATIONS = {"WNZA01","HDQA00","HRBA01","HGHB04"};

    public static final String[] STORAGES = {"NORMAL","STORAGE","IN_STORAGE","OUT_STORAGE",
            "ON_STORAGE","RESERVE_STORAGE","NORMAL","NORMAL","NORMAL","NORMAL","NORMAL","NORMAL"};
    public static final String[] ROOT_CODES = {"1516188564164","1516188564180","1516188564195"};
    public static final String[] SOURCE_CODES = {"1516188564164","1516188564180","1516188564195"};
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


        dto.setBillType(BillTypeEnum.RESTOCK);
        dto.setBillPurpose(BillPurposeEnum.OUT_STORAGE);
        Integer sign = random.nextInt(10);
        dto.setRootCode(ROOT_CODES[random.nextInt(ROOT_CODES.length)]);
        if (sign>4){
            dto.setBillProperty(SourcePlanTypeEnum.RESTOCK);
            dto.setSourceCode(SOURCE_CODES[random.nextInt(ROOT_CODES.length)]);
        }

        else{
            dto.setBillProperty(SourcePlanTypeEnum.NOPLAN);
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


        return dto;
    }
    private Station nextRandomStation() {
        Station station = new Station(STATIONS[random.nextInt(STATIONS.length)]);
        Storage storage = new Storage(STORAGES[random.nextInt(STORAGES.length)]);
        station.setStationType(StationType.values()[random.nextInt(StationType.values().length)]);
        station.setStorage(storage);
        System.err.println(ToStringBuilder.reflectionToString(station));
        return station;
    }
    private RestockBillDetailDTO nextRandomRestockBillDetailDTO(){
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
