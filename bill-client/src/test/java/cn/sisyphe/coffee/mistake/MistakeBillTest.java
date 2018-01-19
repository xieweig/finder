package cn.sisyphe.coffee.mistake;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.application.allot.AllotBillManager;
import cn.sisyphe.coffee.bill.application.mistake.MistakeBillManager;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.viewmodel.allot.AllotBillDTO;
import cn.sisyphe.coffee.bill.viewmodel.allot.ConditionQueryAllotBill;
import cn.sisyphe.coffee.bill.viewmodel.mistake.MistakeBillDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

/**
 * @author Amy on 2018/1/18.
 * describe：
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class MistakeBillTest {
    @Autowired
    private AllotBillManager allotBillManager;
    @Autowired
    private MistakeBillManager mistakeBillManager;
    @Test
    public void submitMistake(){
        String allotBillCode = "AL201801181818006789";
        AllotBill allotBill = (AllotBill) allotBillManager.findOneByBillCode(allotBillCode);
        mistakeBillManager.submitByAllotBill(allotBill);
    }
    @Test
    public void callBack(){
        String mistakeBillCode = "TMCKCQ1120180180EES000001";
        mistakeBillManager.callbackMistakeBill(mistakeBillCode);
    }
    @Test
    public void callBackToAddAllotBillCode(){
        String mistakeBillCode = "TMCKCQ1120180180EES000001";
        String allotBillCode = "AL201801181818006789";
        mistakeBillManager.callBackToAddAllotBillCode(mistakeBillCode,allotBillCode);
    }
    @Test
    public void findByConditions(){
        ConditionQueryAllotBill conditionQueryAllotBill = new ConditionQueryAllotBill();
        conditionQueryAllotBill.setPage(1);
        conditionQueryAllotBill.setPageSize(100);
        Page<AllotBillDTO> page  = allotBillManager.findBillByCondition(conditionQueryAllotBill, BillPurposeEnum.MOVE_STORAGE);
        System.out.println(page);
    }
    @Test
    public void findOne(){
        String allotBillCode = "AL201801181818006789";
        AllotBillDTO dto = allotBillManager.findBillDtoByBillCode(allotBillCode);
        MistakeBillDTO mistakeBillDTO = new MistakeBillDTO();
        if (dto != null && !StringUtils.isEmpty(dto.getBillCode())) {
            mistakeBillDTO = mistakeBillManager.findBillDtoBySourceCode(dto.getBillCode());
            if (mistakeBillDTO == null) {
                mistakeBillDTO = new MistakeBillDTO();
            }
        } else {
            dto = new AllotBillDTO();
        }
        System.out.println(dto);
        System.out.println(mistakeBillDTO.getBillDetails());
    }

}
