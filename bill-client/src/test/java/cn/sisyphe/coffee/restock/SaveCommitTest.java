
package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;

import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillExtraService;

import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
import cn.sisyphe.coffee.bill.viewmodel.restock.QueryRestockBillDTO;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;




/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/5
 * @Description: manager 级别上的测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CoreApplication.class})
public class SaveCommitTest extends InstanceFactory{
    private Random random = new Random();
    private Calendar calendar = Calendar.getInstance();
    Logger logger = LoggerFactory.getLogger(SaveCommitTest.class);
    @Resource
    private RestockBillManager restockBillManager;

    //辅助提供findByCode
    @Resource
    private RestockBillExtraService restockBillExtraService;

    @Resource
    private RestockBillRepository restockBillRepository;

    public static final  String PATH ="F:/own/test.txt";
    //暂时先凑合用吧 应该从数据库中读取或者从文件读取
    public static final String[]  BILLCODES_SAVED={"90530302","43490302","63390302","13910302"};
    public static final String[]  BILLCODES_SUBMITTED={"34940302","55540302","98850302","4120302"};
    private BufferedWriter writer ;
    @Before
    public  void setUp(){
        File file = new File(PATH);
        if (!file.exists()){
            file.mkdirs();
        }

        try {
            writer= new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //测试拣货界面保存
    @Test
    public void saveByAddRestockBillDTO() throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            AddRestockBillDTO dto = this.nextRandomRestockBillDTO();

            this.restockBillManager.saveBill(dto);

            TimeUnit.SECONDS.sleep(1);

        }
    }
    //测试拣货界面提交
//    @Transactional
//    @Rollback
    @Test
    public void submitByAddRestockBillDTO() {
        for (int i = 0; i < 6; i++) {
            AddRestockBillDTO dto = this.nextRandomRestockBillDTO(random.nextInt(3)+1);
            this.restockBillManager.submitBill(dto);
        }
    }
    //查询界面 修改后保存

    private void saveModify(String billCode) throws InterruptedException {

        //由于manager 封装了find 所以在此另外重复一遍
        RestockBill restockBill = restockBillExtraService.findByBillCode(billCode);

        logger.info("===="+ToStringBuilder.reflectionToString(restockBill,ToStringStyle.SHORT_PREFIX_STYLE));

        AddRestockBillDTO dto = this.nextRandomRestockBillDTO(4);

        dto.setBillCode(billCode);

        this.restockBillManager.updateBillToSave(dto);

        RestockBill restockBilled = restockBillExtraService.findByBillCode(billCode);

        logger.info("===="+ ToStringBuilder.reflectionToString(restockBill), ToStringStyle.SHORT_PREFIX_STYLE);
        //等一秒钟数据库查询
        TimeUnit.SECONDS.sleep(1);
        Iterator<RestockBillDetail> iter = restockBill.getBillDetails().iterator();
        while (iter.hasNext()){
            logger.info(billCode+" Before goods codes:"+ iter.next().getGoods().code());
        }
        Iterator<RestockBillDetail> iterd = restockBilled.getBillDetails().iterator();
        while (iterd.hasNext()){
            logger.info(billCode+" After goods codes:"+ iterd.next().getGoods().code());
        }



    }

    @Test(timeout = 20000)
    //批量修改后保存测试
    public void saveAfterModify(){
        for (int i = 0; i <BILLCODES_SAVED.length ; i++) {

            try {
                this.saveModify(BILLCODES_SAVED[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //测试修改界面修改

    private void submitModify(String billCode) throws InterruptedException  {

        RestockBill restockBill = restockBillExtraService.findByBillCode(billCode);
        //模拟更新操作
        AddRestockBillDTO dto = this.nextRandomRestockBillDTO(3);
        dto.setBillCode(billCode);
        this.restockBillManager.updateBillToSubmit(dto);

        RestockBill restockBilled = restockBillExtraService.findByBillCode(billCode);

        //等一秒钟数据库查询
        TimeUnit.SECONDS.sleep(1);
        Iterator<RestockBillDetail> iter = restockBill.getBillDetails().iterator();
        while (iter.hasNext()){
            logger.info(billCode+" Before goods codes:"+ iter.next().getGoods().code());
        }
        Iterator<RestockBillDetail> iterd = restockBilled.getBillDetails().iterator();
        while (iterd.hasNext()){
            logger.info(billCode+" After goods codes:"+ iterd.next().getGoods().code());
        }


    }
    @Transactional
    @Rollback
    @Test(timeout = 20000)
    //批量修改后保存测试
    public void submitAfterModify(){
        for (int i = 0; i <BILLCODES_SUBMITTED.length ; i++) {

            try {
                this.submitModify(BILLCODES_SUBMITTED[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //测试审核界面审核通过与不通过
    @Test
    public void auditTest(){
        for (int i = 0; i <BILLCODES_SUBMITTED.length ; i++) {

            Boolean isSuccess = random.nextBoolean();
            String auditCode= "8866"+random.nextInt(1000)+1000;

            this.restockBillManager.auditBill(BILLCODES_SUBMITTED[i], auditCode, isSuccess);
        }

    }


   //测试多条件查询
    @Test
    public void selectByConditions(){
        ConditionQueryRestockBill queryRestockBill = new ConditionQueryRestockBill();
        queryRestockBill.setBillCode("98650302");
        Date basicDate = new Date();
        calendar.setTime(basicDate);

        calendar.add(Calendar.HOUR, -20);
        queryRestockBill.setInStartTime(calendar.getTime());
        calendar.add(Calendar.HOUR, random.nextInt(10)+30);
        queryRestockBill.setInEndTime(calendar.getTime());
        calendar.add(Calendar.HOUR,-35 );
        queryRestockBill.setCreateStartTime(calendar.getTime());
        calendar.add(Calendar.HOUR,random.nextInt(20)+15);
        queryRestockBill.setCreateEndTime(calendar.getTime());

     //   queryRestockBill.setBillProperty();
     //   queryRestockBill.setCreateStartTime();
     //   queryRestockBill.setCreateEndTime();
        queryRestockBill.setPage(1);
        queryRestockBill.setPageSize(5);
        logger.info(ToStringBuilder.reflectionToString(queryRestockBill,ToStringStyle.SHORT_PREFIX_STYLE));
        QueryRestockBillDTO queryRestockBillDTO = this.restockBillManager.findByConditions(queryRestockBill);
        logger.info(queryRestockBillDTO.toString());
    }
    @Test
    public void selectByBasicCondition(){
        ConditionQueryRestockBill queryRestockBill = new ConditionQueryRestockBill();
        queryRestockBill.setBillCode("74780302");
        QueryRestockBillDTO queryRestockBillDTO = this.restockBillManager.findByConditions(queryRestockBill);
        logger.info(ToStringBuilder.reflectionToString(queryRestockBill,ToStringStyle.SHORT_PREFIX_STYLE));
        logger.info(queryRestockBillDTO.getTotalNumber()+queryRestockBillDTO.getContent().toString());
    }
    @Test
    public void born(){
        for (int i = 0; i <1 ; i++) {
            this.restockBillRepository.save(this.nextRandomRestockBill(3));

        }
    }



}

