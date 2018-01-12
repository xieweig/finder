package cn.sisyphe.coffee.bill.domain.allot;

import cn.sisyphe.coffee.bill.viewmodel.planbill.ConditionQueryPlanBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AllotBillExtraServiceImpl implements AllotBillExtraService {
    @Override
    public Page<AllotBill> findPageByCondition(ConditionQueryPlanBill conditionQueryAllotBill) {
        // 组装页面
        Pageable pageable = new PageRequest(conditionQueryAllotBill.getPage() - 1, conditionQueryAllotBill.getPageSize());

        Page<AllotBill> allotBillPage;
        allotBillPage = queryByParams(conditionQueryAllotBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (allotBillPage.getContent().size() < 1 && allotBillPage.getTotalElements() > 0) {
            pageable = new PageRequest(allotBillPage.getTotalPages() - 1, conditionQueryAllotBill.getPageSize());
            allotBillPage = queryByParams(conditionQueryAllotBill, pageable);
        }
        return allotBillPage;
    }

    private Page<AllotBill> queryByParams(ConditionQueryPlanBill conditionQueryAllotBill, Pageable pageable) {
        return null;
    }
}
