package cn.sisyphe.coffee.shared;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.application.shared.SharedManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Amy on 2018/1/4.
 * describe：
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class SharedTest {
    @Autowired
    private SharedManager sharedManager;
    @Test
    public void test1() {
        String s  = sharedManager.findLogisticCodeByStationCode("TEST002");
        System.out.println(s);
    }
}
