package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.SourcePlanTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.util.BillCodeManager;

import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/17
 * @Description:
 */
public class InstanceIter {
    private Random random = new Random();
    public static final String[] STATIONS = {"WNZA01","HDQA00","HRBA01","HGHB04"};
    public static final String[] STORAGES = {"normal001","mormal002","normal003","normal004"};
    public static final String[] ROOT_CODES = {"0302120","0302122","0302121","0302124"};
    public static final String[] SOURCE_CODES = {"s0302120","s0302122","s0302121","s0302124"};
    public static  final String[] OPERATIONCODE={"YGADMIN"};
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
        dto.setBillCode(BillCodeManager.getBillCodeFun("ABCD","10P"));
        dto.setRootCode(ROOT_CODES[random.nextInt()]);
        dto.setSourceCode(SOURCE_CODES[random.nextInt()]);

        dto.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);
        dto.setBillProperty(SourcePlanTypeEnum.RESTOCK);
        dto.setOperatorCode(OPERATIONCODE[random.nextInt(OPERATIONCODE.length)]);

        dto.setInLocation(this.nextRandomStation());
        dto.setOutLocation(this.nextRandomStation());
        Set<RestockBillDetailDTO> details = new HashSet();
        for (int i = 0; i < detailsNumber; i++) {
            details.add(this.nextRandomRestockBillDetailDTO());
        }

        dto.setBillDetails(details);


        dto.setProgress(new BigDecimal(random.nextInt(100)));

        dto.setPlanMemo("plan memo:"+random.nextInt(100));

        return dto;
    }
    private Station nextRandomStation() {
        Station station = new Station(STATIONS[random.nextInt(STATIONS.length)]);
        Storage storage = new Storage(STORAGES[random.nextInt(STORAGES.length)]);
        station.setStationType(StationType.values()[random.nextInt(StationType.values().length)]);
        station.setStorage(storage);

        return station;
    }
    private RestockBillDetailDTO nextRandomRestockBillDetailDTO(){
        RestockBillDetailDTO restockBillDetailDTO = new RestockBillDetailDTO();
        Integer amount = random.nextInt(100)+20;
        restockBillDetailDTO.setActualAmount(amount);
        restockBillDetailDTO.setShippedAmount(amount+random.nextInt(10)-5);
        restockBillDetailDTO.setRawMaterial(new RawMaterial(""+random.nextInt(100)));

        return restockBillDetailDTO;
    }
}
