package cn.sisyphe.coffee.bill;


import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.plan.ItemPayload;
import cn.sisyphe.coffee.bill.domain.plan.strategy.DeliveryStrategy;
import cn.sisyphe.coffee.bill.infrastructure.plan.PlanBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.plan.jpa.JPAPlanBillRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ncmao
 * @Date 2017/12/25 16:38
 * @description
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class PlanBillIntegrationTest {


    @Autowired
    private DeliveryStrategy deliveryStrategy;

    @Autowired
    private JPAPlanBillRepository jpaPlanBillRepository;

    @Autowired
    private PlanBillRepository planBillRepository;


    @Test
    public void createSuccess() {
        ItemPayload itemPayload = new ItemPayload();
        itemPayload.setBillName("总部调剂计划");
        itemPayload.setBillCode("code");
        itemPayload.setBillType(BillTypeEnum.DELIVERY);
        itemPayload.setMemo("beizhu");

        Cargo cargo = new Cargo("cargocode");
        itemPayload.addGood(cargo);
        deliveryStrategy.cast(itemPayload);

    }

}
