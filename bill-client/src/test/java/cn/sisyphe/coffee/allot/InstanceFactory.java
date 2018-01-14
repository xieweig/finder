package cn.sisyphe.coffee.allot;

import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.*;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.util.BillCodeManager;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/12
 * @Description: 调拨单的信息实例dto 或entity 的生成 辅助测试
 */
public class InstanceFactory {

    private Random random = new Random();
    private BillFactory billFactory = new BillFactory();

    public static final String[] CARGOCODE = {"AD","SSA","SDS","WEA"};
    public static final String[] RAWMATERIALCODE = {"2","7","8","9"};

    public AllotBill nextRandomAllot(){
        AllotBill allotBill = (AllotBill) billFactory.createBill(BillTypeEnum.ALLOT);
        //allotBill “TKCK”"36G" wondering whether right
        allotBill.setBillCode(BillCodeManager.getBillCodeFun("TKCK","36G"));
        allotBill.setAuditState(BillAuditStateEnum.UN_REVIEWED);
        allotBill.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);
        allotBill.setRootCode("00"+random.nextInt(1000));
        allotBill.setSourceCode("2222"+random.nextInt(1000));
        allotBill.setTotalPrice(new BigDecimal(random.nextInt(1000)));
        allotBill.setInOrOutState(BillInOrOutStateEnum.values()[BillInOrOutStateEnum.values().length]);
        allotBill.setOutStateEnum(BillOutStateEnum.values()[BillOutStateEnum.values().length]);

        allotBill.setBillProperty(SourcePlanTypeEnum.values()[random.nextInt(SourcePlanTypeEnum.values().length)]);
        allotBill.setBillPurpose(BillPurposeEnum.values()[random.nextInt(BillPurposeEnum.values().length)]);
        allotBill.setBillState(BillStateEnum.values()[random.nextInt(BillStateEnum.values().length)]);
        allotBill.setBillType(BillTypeEnum.values()[random.nextInt(BillTypeEnum.values().length)]);
        allotBill.setInWareHouseTime(new Date());
        allotBill.setProgress(new BigDecimal("100"));


        allotBill.setInLocation(this.nextRandomStation());
        allotBill.setOutLocation(this.nextRandomStation());
        Set<AllotBillDetail> set = new HashSet<>();
        for (int i = 0; i < random.nextInt(4)+1; i++) {
            set.add(this.nextRandomAllotDetail());
        }

        allotBill.setBillDetails(set);

        allotBill.setOperatorCode("0303"+random.nextInt(10000));
        allotBill.setBelongStationCode(String.format("%08d",random.nextInt(10000)));
        allotBill.setBillCodePrefix("prefix");
        allotBill.setAuditPersonCode(String.format("%04d",random.nextInt(1000)));

        allotBill.setAuditMemo("audit remarks: "+random.nextInt());
        return  allotBill;
    }

    private AllotBillDetail nextRandomAllotDetail(){

        AllotBillDetail allotBillDetail = new AllotBillDetail();
        int amount = random.nextInt(1000);
        allotBillDetail.setShippedAmount(amount);
        allotBillDetail.setActualAmount(amount+random.nextInt(6)-3);
        /*billDetailDTO.setMemo("details remarks:" + random.nextInt(200));*/
        RawMaterial rawMaterial = new RawMaterial(RAWMATERIALCODE[random.nextInt(RAWMATERIALCODE.length)]);
        Cargo cargo = new Cargo(CARGOCODE[random.nextInt(CARGOCODE.length)]);

        cargo.setCargoName("cargoName:" + random.nextInt(100));
        rawMaterial.setCargo(cargo);
        allotBillDetail.setGoods(rawMaterial);
        return allotBillDetail;
    }


    private Station nextRandomStation() {
        Station station = new Station("1212" + random.nextInt(10) + "02" + random.nextInt(10));
        Storage storage = new Storage("01" + random.nextInt(80));
        storage.setStorageCode("1111" + random.nextInt(100));
        station.setStationName("重庆" + random.nextInt(100) + "站");
        station.setStationCode("88" + random.nextInt(122));
        station.setStationType(StationType.STORE);
        station.setStorage(storage);
        return station;
    }
}
