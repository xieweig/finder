package cn.sisyphe.coffee.allot;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.delivery.DeliveryBillManager;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.delivery.DeliveryBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
import cn.sisyphe.coffee.delivery.InstanceIterat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/18
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CoreApplication.class})
public class NewAllotTest extends InstanceFactory {
    @Resource
    private RestockBillManager restockBillManager;
    @Resource
    private AllotBillManager allotBillManager;

    @Test
    public void saveRestockSource(){
        for (int i = 0; i < 5; i++) {

            RestockBillDTO restockBillDTO = this.nextRandomAddRestockBillDTO(random.nextInt(2)+1);
            restockBillDTO.setBillPurpose(BillPurposeEnum.IN_STORAGE);
            restockBillDTO =restockBillManager.saveBill(restockBillDTO);
            sourceRestockList.add(restockBillDTO.getBillCode());
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.saveDTO();


    }

    protected void saveDTO(){
        for (int i = 0; i <8; i++) {
            AllotBillDTO dto = this.nextRandomAddAllotBillDTO(random.nextInt(3)+1);
            dto.setSourceCode(sourceRestockList.get(random.nextInt(sourceRestockList.size())).trim());

            allotBillManager.saveBill(dto);

        }
        sourceRestockList.clear();

    }

}