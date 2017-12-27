package cn.sisyphe.coffee.bill.infrastructure.plan.jpa;

import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ncmao
 * @Date 2017/12/25 17:09
 * @description
 */

@Repository
public interface JPAPlanBillRepository extends JpaRepository<PlanBill, Long>, JpaSpecificationExecutor<PlanBill> {

    PlanBill findByBillCode(String billCode);
}
