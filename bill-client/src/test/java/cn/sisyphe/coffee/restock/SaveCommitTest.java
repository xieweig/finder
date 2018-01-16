

package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillExtraService;

import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


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

    public static final String[]  BILLCODES_SAVED={"90530302","43490302","63390302","13910302"};
    public static final String[]  BILLCODES_SUBMITTED={"34940302","55540302","98850302","4120302"};

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
        queryRestockBill.setInStorageStartTime(calendar.getTime());
        calendar.add(Calendar.HOUR, random.nextInt(10)+30);
        queryRestockBill.setInStorageEndTime(calendar.getTime());
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
        Page<RestockBillDTO> restockBillDTO = this.restockBillManager.findByConditions(queryRestockBill, BillTypeEnum.RESTOCK, BillPurposeEnum.OutStorage);
        logger.info(restockBillDTO.getContent().toString());
    }
    @Test
    public void selectByBasicCondition(){
        ConditionQueryRestockBill queryRestockBill = new ConditionQueryRestockBill();
        queryRestockBill.setBillCode("58300302");
        queryRestockBill.setPageSize(10);
        queryRestockBill.setPage(1);
        queryRestockBill.setBillType(BillTypeEnum.RESTOCK);
        Page<RestockBillDTO> restockBillDTO  = this.restockBillManager.findByConditions(queryRestockBill, BillTypeEnum.RESTOCK, BillPurposeEnum.OutStorage);
        logger.info(ToStringBuilder.reflectionToString(queryRestockBill,ToStringStyle.SHORT_PREFIX_STYLE));
        logger.info(restockBillDTO.getTotalPages()+restockBillDTO.getContent().toString());
    }
    @Test
    public void born(){
        for (int i = 0; i <1 ; i++) {
            this.restockBillRepository.save(this.nextRandomRestockBill(3));

        }
    }

    public  static  final String[] codes = {  "TKCK88982018012036G000004", "TKCK88382018012036G000005", "TKCK88972018012036G000006"  };
    //退库入库单
    @Test
    public void queryRestockInstorage(){
        for (int i = 0; i <codes.length ; i++) {

           RestockInStorageBillDTO restockInStorageBillDTO =  this.restockBillManager.findRestockInStorageBillByRestockBillCode(codes[i]);
           logger.info(ToStringBuilder.reflectionToString(restockInStorageBillDTO,ToStringStyle.SHORT_PREFIX_STYLE));

           logger.info(ToStringBuilder.reflectionToString(restockInStorageBillDTO.getBillDetailDTOList().get(0),ToStringStyle.SHORT_PREFIX_STYLE));
        }
    }
    //查看调拨单
    @Test
    public void queryRestock(){
        for (int i = 0; i <codes.length ; i++) {

            RestockAllotBillDTO RestockAllotBillDTO =  this.restockBillManager.findRestockAllotBillByRestockBillCode(codes[i]);
            logger.info(ToStringBuilder.reflectionToString(RestockAllotBillDTO,ToStringStyle.SHORT_PREFIX_STYLE));
            //有billCode billProperty stationCode*2

            logger.info(ToStringBuilder.reflectionToString(RestockAllotBillDTO.getRestockAllotDetailBillDTOList().get(0),ToStringStyle.SHORT_PREFIX_STYLE));



        }
    }



}


