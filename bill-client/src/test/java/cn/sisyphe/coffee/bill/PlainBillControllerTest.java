package cn.sisyphe.coffee.bill;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.apache.http.client.methods.RequestBuilder.post;

/**
 * @author ncmao
 * @Date 2018/1/4 15:01
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class PlainBillControllerTest {


    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;


    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @Test
    public void shouldCreateBill() {
    }
}
