//package cn.sisyphe.coffee.restock;
//
//import cn.sisyphe.coffee.bill.CoreApplication;
//import cn.sisyphe.coffee.bill.application.restock.RestockBillManager;
//import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
//import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
//import cn.sisyphe.coffee.bill.viewmodel.restock.ConditionQueryRestockBill;
//import cn.sisyphe.coffee.bill.viewmodel.restock.RestockBillDTO;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.*;
//
///**
// * @Author: xie_wei_guang
// * @Date: 2018/1/12
// * @Description:
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {CoreApplication.class})
//public class RestockQueryTest extends InstanceFactory {
//    private Random random = new Random();
//    private Calendar calendar = Calendar.getInstance();
//    Logger logger = LoggerFactory.getLogger(SaveCommitTest.class);
//    @Resource
//    private RestockBillManager restockBillManager;
//    public  static  final String[] codes = {  "TKCK88982018012036G000004", "TKCK88382018012036G000005", "TKCK88972018012036G000006"  };
//    public  static  final String[] operateName={ "220072","220028","220049"};
//    @Test
//    public void test(){
//        ConditionQueryRestockBill conditionQueryRestockBill = new ConditionQueryRestockBill();
//        conditionQueryRestockBill.setPage(1);
//        conditionQueryRestockBill.setPageSize(3);
//
////      测试单据单号
////        for (int i = 0; i < codes.length ; i++) {
////
////            conditionQueryRestockBill.setBillCode(codes[i]);
////
////            Page<RestockBillDTO> page = restockBillManager.findByConditions(conditionQueryRestockBill, BillTypeEnum.RESTOCK, BillPurposeEnum.OUT_STORAGE);
////
////            logger.info(ToStringBuilder.reflectionToString(page.getContent().get(0)), ToStringStyle.SHORT_PREFIX_STYLE);
////
////        }
//
////      测试录单人
////        for (int i = 0; i < codes.length ; i++) {
////
////            conditionQueryRestockBill.setOperatorName(operateName[i]);
////
////            Page<RestockBillDTO> page = restockBillManager.findByConditions(conditionQueryRestockBill, BillTypeEnum.RESTOCK, BillPurposeEnum.OUT_STORAGE);
////
////            logger.info(ToStringBuilder.reflectionToString(page.getContent().get(0)), ToStringStyle.SHORT_PREFIX_STYLE);
////        }
//
////        测试时间
//            Date date = new Date() ;
//            conditionQueryRestockBill.setCreateEndTime(date);
//            calendar.setTime(date);
//            calendar.add(Calendar.DATE, -1);
//            conditionQueryRestockBill.setCreateStartTime(calendar.getTime());
//            Page<RestockBillDTO> page = restockBillManager.findByConditions(conditionQueryRestockBill, BillTypeEnum.RESTOCK, BillPurposeEnum.OUT_STORAGE);
//
//            logger.info(ToStringBuilder.reflectionToString(page.getContent().get(0)), ToStringStyle.SHORT_PREFIX_STYLE);
//               // 测试审核状态
////            conditionQueryRestockBill.setAuditStateCode(new ArrayList<>("UNCOMMITTED"));
//              //测试提交状态
////            conditionQueryRestockBill.setSubmitStateCode();
//              //测试出站点
////            conditionQueryRestockBill.setOutStationCodeArray();
////
////            //测试入站点
////            conditionQueryRestockBill.setInStationCodeArray();
//             //测试出入站点
////            conditionQueryRestockBill.setInOrOutStateCode();
////
//
//
//
//    }
//
//}
