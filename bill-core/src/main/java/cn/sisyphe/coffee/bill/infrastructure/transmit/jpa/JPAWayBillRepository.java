package cn.sisyphe.coffee.bill.infrastructure.transmit.jpa;

import cn.sisyphe.coffee.bill.domain.transmit.WayBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2017/12/27 17:08
 **/
@Repository
public interface JPAWayBillRepository extends JpaRepository<WayBill, Long>,
        JpaSpecificationExecutor<WayBill> {


    /**
     * 通过code查询
     * @param billCode
     * @return
     */
    WayBill findOneByBillCode(String billCode);


    /**
     * 分页查询
     *
     * @param ta
     * @param pageable
     * @return
     */
    @Override
    Page<WayBill> findAll(Specification<WayBill> ta, Pageable pageable);

}
