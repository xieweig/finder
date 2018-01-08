package cn.sisyphe.coffee.bill.infrastructure.plan.jpa;

import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.plan.PlanBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    /**
     * 根据单据编号查询单据信息
     * @param billCode
     * @return
     */
    PlanBill findByBillCode(String billCode);

    /**
     * 条件筛选查询
     * @param ta
     * @param pageable
     * @return
     */
    @Override
    Page<PlanBill> findAll(Specification<PlanBill> ta, Pageable pageable );

    /**
     * 根据单据编号查询单据信息
     * @param billCode
     * @return
     */
    PlanBill findByBillCodeAndSpecificBillType(String billCode, BillTypeEnum billType);
}
