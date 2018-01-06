package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.CoreApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/5
 * @Description:可以在
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CoreApplication.class, ClientApplication.class})
@WebAppConfiguration
public class ControllerTest {
    @Resource
    private WebApplicationContext webApplicationContext;
    Logger logger = LoggerFactory.getLogger(ControllerTest.class);
    private Random random;
    private MockMvc mvc;

//

    @Before
    public void setUp(){
        mvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        random = new Random();
    }

    @Test
  public void funny() {
        try {
            mvc.perform(MockMvcRequestBuilders
                    .get("/api/bill/returned/saveRestockBill")
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .param("addRestockBillDTO",""));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("--------返回的json = " );

    }

}
