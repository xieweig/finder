package cn.sisyphe.coffee.bill;

import cn.sisyphe.coffee.bill.application.planbill.PlanBillManager;
import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import cn.sisyphe.coffee.bill.viewmodel.planbill.QueryPlanBillDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class PlanBillTest {

    @Autowired
    private PlanBillManager planBillManager;
    /**
     * 测试多条件查询
     */
    @Test
    public void findPlanBillByConditions() {
        ConditionQueryPlanBill conditionQueryPlanBill = new ConditionQueryPlanBill();
//        conditionQueryPlanBill.setCreatorName("伊利丹");
        conditionQueryPlanBill.setPage(1);
        conditionQueryPlanBill.setPageSize(10);
//        planBillManager.findPageByCondition(conditionQueryPlanBill);
        QueryPlanBillDTO queryPlanBillDTO = planBillManager.findPageByCondition(conditionQueryPlanBill);

        System.err.print(queryPlanBillDTO.toString());
    }

    /**
     * 测试根据billCode查询
     */
    @Test
    public void findPlanBillByBillCode() {
        ConditionQueryPlanBill conditionQueryPlanBill = new ConditionQueryPlanBill();
        String billCode = "1";
        planBillManager.findHqPlanBillByBillCode(billCode);

    }
}
