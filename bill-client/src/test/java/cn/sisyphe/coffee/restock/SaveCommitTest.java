
package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;
import cn.sisyphe.coffee.bill.domain.restock.RestockBill;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillDetail;
import cn.sisyphe.coffee.bill.domain.restock.RestockBillQueryService;
import cn.sisyphe.coffee.bill.domain.restock.enums.BasicEnum;
import cn.sisyphe.coffee.bill.domain.restock.enums.PropertyEnum;
import cn.sisyphe.coffee.bill.infrastructure.restock.RestockBillRepository;
import cn.sisyphe.coffee.bill.viewmodel.restock.AddRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
import cn.sisyphe.coffee.bill.viewmodel.restock.QueryRestockBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDetailDTO;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.style.ToStringCreator;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

//import cn.sisyphe.coffee.bill.domain.plan.enums.BasicEnum;



/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/5
 * @Description: manager 级别上的测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CoreApplication.class , ClientApplication.class})
public class SaveCommitTest extends InstanceFactory{
    private Random random = new Random();
    private Calendar calendar = Calendar.getInstance();
    Logger logger = LoggerFactory.getLogger(SaveCommitTest.class);
    @Resource
    private RestockBillManager restockBillManager;
    @Resource
    private RestockBillQueryService restockBillQueryService;
    @Resource
    private RestockBillRepository restockBillRepository;
    //暂时先凑合用吧 应该从数据库中读取或者从文件读取
    public static final String[]  BILLCODES={"90530302","43490302","63390302","13910302"};

//    @Before
//    public static void setUp(){
//
//    }

    //测试拣货界面保存
    @Test
    public void saveByAddRestockBillDTO() {
        for (int i = 0; i < 3; i++) {
            AddRestockBillDTO dto = this.nextRandomRestockBillDTO();

            this.restockBillManager.saveBill(dto);
        }
    }
    //测试拣货界面提交
    @Test
    public void submitByAddRestockBillDTO() {
        for (int i = 0; i < 4; i++) {
            AddRestockBillDTO dto = this.nextRandomRestockBillDTO(random.nextInt(3)+1);
            this.restockBillManager.submitBill(dto);
        }
    }
    //查询界面 修改后保存

    private void saveModify(String billCode) throws InterruptedException {

        //由于manager 封装了find 所以在此另外重复一遍
        RestockBill restockBill = restockBillQueryService.findByBillCode(billCode);

        logger.info("===="+ToStringBuilder.reflectionToString(restockBill,ToStringStyle.SHORT_PREFIX_STYLE));

        AddRestockBillDTO dto = this.nextRandomRestockBillDTO(4);

        dto.setBillCode(billCode);

        this.restockBillManager.updateBillToSave(dto);

        RestockBill restockBilled = restockBillQueryService.findByBillCode(billCode);

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
        for (int i = 0; i <BILLCODES.length ; i++) {

            try {
                this.saveModify(BILLCODES[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //测试修改界面修改

    private void submitModify(String billCode) throws InterruptedException  {

        RestockBill restockBill = restockBillQueryService.findByBillCode(billCode);
        //模拟更新操作
        AddRestockBillDTO dto = this.nextRandomRestockBillDTO(3);
        dto.setBillCode(billCode);
        this.restockBillManager.updateBillToSubmit(dto);

        RestockBill restockBilled = restockBillQueryService.findByBillCode(billCode);

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
        for (int i = 0; i <BILLCODES.length ; i++) {

            try {
                this.submitModify(BILLCODES[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


   //测试多条件查询
    @Test
    public void selectByConditions(){
        ConditionQueryRestockBill queryRestockBill = new ConditionQueryRestockBill();
        queryRestockBill.setBillCode("98650302");
        Date basicDate = new Date();
        calendar.setTime(basicDate);

        calendar.add(Calendar.DATE, 2);
        queryRestockBill.setInStartTime(calendar.getTime());
        calendar.add(Calendar.DATE, random.nextInt(20)+5);
        queryRestockBill.setInEndTime(calendar.getTime());
        calendar.add(Calendar.DATE,random.nextInt(20)+10 );
        queryRestockBill.setCreateStartTime(calendar.getTime());
        calendar.add(Calendar.DATE,random.nextInt(20)+5);
        queryRestockBill.setCreateEndTime(calendar.getTime());
     //   queryRestockBill.setCreateStartTime();
     //   queryRestockBill.setCreateEndTime();
        queryRestockBill.setPage(1);
        queryRestockBill.setPageSize(2);
        QueryRestockBillDTO queryRestockBillDTO = this.restockBillManager.findByConditions(queryRestockBill);
        logger.info(queryRestockBillDTO.toString());
    }

}

