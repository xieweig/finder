package cn.sisyphe.coffee.allot;

import cn.sisyphe.coffee.bill.domain.allot.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAuditStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.purpose.BillPurpose;
import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;
import cn.sisyphe.coffee.bill.util.BillCodeManager;
import cn.sisyphe.coffee.bill.viewmodel.shared.SourcePlanTypeEnum;
import com.sun.javafx.binding.StringFormatter;

import java.util.Random;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/12
 * @Description: 信息实例dto 或entity 的生成
 */
public class InstanceFactory {

    private Random random = new Random();
    private BillFactory billFactory = new BillFactory();
    public AllotBill nextRandomAllot(){
        AllotBill allotBill = (AllotBill) billFactory.createBill(BillTypeEnum.ALLOT);
        allotBill.setBillCode(BillCodeManager.getBillCodeFun("ABCD","0302"));
        allotBill.setAuditState(BillAuditStateEnum.UN_REVIEWED);
        allotBill.setBasicEnum(BasicEnum.values()[random.nextInt(BasicEnum.values().length)]);
     //   allotBill.setBillDetails();
        allotBill.setBillProperty(SourcePlanTypeEnum.values()[random.nextInt(SourcePlanTypeEnum.values().length)]);
        allotBill.setBillPurpose(BillPurposeEnum.values()[random.nextInt(BillPurposeEnum.values().length)]);
        allotBill.setBillState(BillStateEnum.values()[random.nextInt(BillStateEnum.values().length)]);
        allotBill.setBillType(BillTypeEnum.values()[random.nextInt(BillTypeEnum.values().length)]);
       // allotBill.se

        allotBill.setBelongStationCode(String.format("%08d",random.nextInt(10000)));
        allotBill.setBillCodePrefix("prefix");
        allotBill.setAuditPersonCode(String.format("%04d",random.nextInt(1000)));

        allotBill.setAuditMemo("audit remarks: "+random.nextInt());
        return  allotBill;
    }
}
