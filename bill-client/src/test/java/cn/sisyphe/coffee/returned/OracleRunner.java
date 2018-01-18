package cn.sisyphe.coffee.returned;

import cn.sisyphe.coffee.bill.CoreApplication;

import cn.sisyphe.coffee.bill.application.returned.ReturnedBillManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/17
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CoreApplication.class})
public class OracleRunner extends InstanceIterator{
    @Autowired
    private ReturnedBillManager returnedBillManager;

    @Test
    public void saveDTO(){
        for (int i = 0; i <6; i++) {
            returnedBillManager.saveBill(this.nextRandomAddReturnedBillDTO(random.nextInt(3)+1));

        }

    }
}