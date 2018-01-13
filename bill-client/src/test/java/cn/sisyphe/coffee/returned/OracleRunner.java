
package cn.sisyphe.coffee.returned;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.application.returned.ReturnedBillManager;
import cn.sisyphe.coffee.bill.infrastructure.returned.ReturnedBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.returned.AddReturnedBillDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Random;


/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/7
 * @Description:
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class OracleRunner extends InstanceIterator {
    private Random random = new Random();
    private Calendar calendar = Calendar.getInstance();
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ReturnedBillManager returnedBillManager;
    private ReturnedBillRepository returnedBillRepository;
    private String[] names = {"milk", "coffee", "cheese", "beer", "juice", "meat", "duck", "chicken", "goal", "apple"};


    //测试return bill 保存 提交
    @Test
    public void leafSave() {
        for (int i = 0; i < 6; i++) {

            AddReturnedBillDTO addReturnedBillDTO = this.nextRandomAddReturnedBillDTO();
            this.returnedBillManager.saveBill(addReturnedBillDTO);

        }
    }

    @Test
    public void leafSubmit() {
        for (int i = 0; i < 3; i++) {

            AddReturnedBillDTO addReturnedBillDTO = this.nextRandomAddReturnedBillDTO();
            this.returnedBillManager.submitBill(addReturnedBillDTO);

        }
    }

    //测试 return bill 修改后保存提交
    @Test
    public void alertAndSave() {
        AddReturnedBillDTO addReturnedBillDTO = this.nextRandomAddReturnedBillDTO();
        addReturnedBillDTO.setBillCode("27850302");
        this.returnedBillManager.updateBillToSave(addReturnedBillDTO);

    }

    @Test
    public void alertAndSubmit() {
        AddReturnedBillDTO addReturnedBillDTO = this.nextRandomAddReturnedBillDTO();
        addReturnedBillDTO.setBillCode("82090302");
        this.returnedBillManager.updateBillToSubmit(addReturnedBillDTO);

    }
}
