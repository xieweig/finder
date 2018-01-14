package cn.sisyphe.coffee.bill.infrastructure.delivery.jpa;

import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 配送单
 *
 * @author ncmao
 * @Date 2017/12/27 12:25
 * @description
 */
@Repository
public interface JPADeliveryBillRepository extends JpaRepository<DeliveryBill, Long>, JpaSpecificationExecutor<DeliveryBill> {

    /**
     * 根据单据编号查询单据信息
     *
     * @param billCode
     * @return
     */
    DeliveryBill findOneByBillCode(String billCode);

    /**
     * 条件筛选查询
     *
     * @param ta
     * @param pageable
     * @return
     */
    @Override
    Page<DeliveryBill> findAll(Specification<DeliveryBill> ta, Pageable pageable);

}
