package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.CoreApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/5
 * @Description:可以在
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CoreApplication.class, ClientApplication.class})
//@WebAppConfiguration
public class ControllerTest {

    public static final String PREFIX = "http://localhost:15009/api/bill/restock/";
    @Resource
    private WebApplicationContext webApplicationContext;
    Logger logger = LoggerFactory.getLogger(ControllerTest.class);
    private Random random;
    private MockMvc mvc;



}
