package cn.sisyphe.coffee.bill.infrastructure.plan;

import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author ncmao
 * @Date 2017/12/25 17:08
 * @description
 */
public interface PlanBillRepository extends BillRepository<PlanBill> {

    /**
     * 根据单据编号查询
     * @param billCode
     * @return
     */
    PlanBill findByBillCode(String billCode);

    void save(List<PlanBill> planBills);

    Page<PlanBill> findAll(Specification<PlanBill> ta, Pageable pageable);
}
