
package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.*;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.base.purpose.BillPurpose;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBill;
import cn.sisyphe.coffee.bill.domain.plan.model.PlanBillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.plan.enums.OperationStateEnum;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/8
 * @Description:仅仅服务于restock测试用，数据做的字段不全
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class JustForPlan {

     private Random random = new Random();
    private Calendar calendar = Calendar.getInstance();
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String[] STATIONS = {"WNZA01","HDQA00","HRBA01","HGHB04"};
    public static final String[] STORAGES = {"NORMAL","STORAGE","IN_STORAGE","OUT_STORAGE",
            "ON_STORAGE","RESERVE_STORAGE","NORMAL","NORMAL","NORMAL","NORMAL","NORMAL","NORMAL"};
    public static final String[] OPERATIONCODE={"YGADMIN"};
    //用来添加货物和原料对应名称 请如下添加 “原料=货物”
    public static final List<String> detailList = new ArrayList();
    static {
        detailList.add("AS=AD");
        detailList.add("HSH=SSA");
        detailList.add("WEA=WEA");
        detailList.add("AS=SDS");
    }
    @Resource
    private PlanBillRepository planBillRepository;

    protected PlanBillDetail createPlanBillDetail() {
        PlanBillDetail planBillDetail = new PlanBillDetail();
        planBillDetail.setShippedAmount(random.nextInt(100));
        planBillDetail.setPackageCode("test:03" + random.nextInt(100));

        String codePair= detailList.get(random.nextInt(detailList.size()));
        String[] strings = codePair.split("=");
        RawMaterial rawMaterial = new RawMaterial(strings[0]);
        Cargo cargo = new Cargo(strings[1]);
        rawMaterial.setCargo(cargo);
        planBillDetail.setGoods(rawMaterial);
        planBillDetail.setTransferLocation(this.nextRandomStation());
        planBillDetail.setInLocation(this.nextRandomStation());
        planBillDetail.setOutLocation(this.nextRandomStation());
        return planBillDetail;
    }
//为restock生成plan测试用
    private BillTypeEnum billType = BillTypeEnum.RESTOCK;
    private BillFactory billFactory = new BillFactory();
    protected PlanBill createPlanBill() {


        PlanBill planBill = (PlanBill)billFactory.createBill(BillTypeEnum.PLAN);
        planBill.setHqBill(false);
       //plan_bill 的code生成 可能是远程接口调用
        planBill.setBillCode(String.valueOf(System.currentTimeMillis()));

        planBill.setSpecificBillType(this.billType);
        planBill.setBillState(BillStateEnum.SAVED);
        planBill.setBillType(BillTypeEnum.PLAN);
        planBill.setBillPurpose(BillPurposeEnum.values()[random.nextInt(BasicEnum.values().length)]);
        planBill.setInLocation(this.nextRandomStation());
        planBill.setOutLocation(this.nextRandomStation());
        planBill.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);
        planBill.setInOrOutState(BillInOrOutStateEnum.OUT_SUCCESS);

        planBill.setAuditMemo("audit remarks: " + random.nextInt(1000));
        planBill.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);
        planBill.setOperationState(OperationStateEnum.NOOPERATION);
        planBill.setBillName("bill name" + random.nextInt(100));

        planBill.setAuditState(BillAuditStateEnum.AUDIT_SUCCESS);
        planBill.setSubmitState(BillSubmitStateEnum.SUBMITTED);

        planBill.setOperatorCode(OPERATIONCODE[random.nextInt(OPERATIONCODE.length)]);
        planBill.setPlanMemo("plan memo:"+random.nextInt(100));


        planBill.setOutWareHouseTime(new Date());

        Set<PlanBillDetail> details = new HashSet<>();
        for (int i = 0; i < random.nextInt(3); i++) {

            details.add(this.createPlanBillDetail());
        }
        planBill.setBillDetails(details);
        return  planBill;
    }
    private Station nextRandomStation() {
        Station station = new Station(STATIONS[random.nextInt(STATIONS.length)]);
        Storage storage = new Storage(STORAGES[random.nextInt(STORAGES.length)]);
        station.setStationType(StationType.values()[random.nextInt(StationType.values().length)]);
        station.setStorage(storage);

        return station;
    }
    @Test
    public void insertTest(){
        for (int i = 0; i <6 ; i++) {
            this.planBillRepository.save(this.createPlanBill());
//            if (i < InstanceIter.ROOT_CODES.length){
//                InstanceIter.ROOT_CODES[i]=
//            }
        }
    }
}

