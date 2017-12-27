package cn.sisyphe.bill.core.infrastructure.plan.jpa;

import cn.sisyphe.bill.core.domain.plan.PlanBill;
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
}
