package cn.sisyphe.coffee.dto;

import cn.sisyphe.coffee.bill.CoreApplication;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDetailDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by heyong on 2018/1/17 16:53
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class DtoTest {

    @Test
    public void copy(){

        // dto
        AllotBillDTO allotBillDTO = new AllotBillDTO();
        allotBillDTO.setBillCode("123");

        AllotBillDetailDTO allotBillDetailDTO = new AllotBillDetailDTO();
        allotBillDetailDTO.setActualAmount(123);

        Set<AllotBillDetailDTO> list = new HashSet<>();
        list.add(allotBillDetailDTO);

        allotBillDTO.setBillDetails(list);

        // bill
        AllotBill allotBill = new AllotBill();

        BeanUtils.copyProperties(allotBillDTO, allotBill);

        System.err.println(allotBill);

    }
}
