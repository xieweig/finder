package cn.sisyphe.coffee.bill.infrastructure.adjust.jpa;

import cn.sisyphe.coffee.bill.domain.adjust.AdjustBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：
 * version:
 */
@Repository
public interface JPAAdjustBillRepository extends JpaRepository<AdjustBill, Long>, JpaSpecificationExecutor<AdjustBill> {

    /**
     * 根据单据编号查询单据信息
     *
     * @param billCode
     * @return
     */
    AdjustBill findByBillCode(String billCode);

    /**
     * 条件筛选查询
     *
     * @param ta
     * @param pageable
     * @return
     */
    @Override
    Page<AdjustBill> findAll(Specification<AdjustBill> ta, Pageable pageable);

}
