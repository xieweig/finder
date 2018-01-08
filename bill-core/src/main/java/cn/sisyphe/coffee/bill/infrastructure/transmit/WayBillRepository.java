package cn.sisyphe.coffee.bill.infrastructure.transmit;


import cn.sisyphe.coffee.bill.domain.transmit.WayBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * 运单JPA
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2017/12/27 17:08
 **/
public interface WayBillRepository {

    WayBill save(WayBill wayBill);

    /**
     * @param billCode
     * @return
     */
    WayBill findOneByCode(String billCode);


    WayBill createBill(WayBill wayBill);

    /**
     * 分页查询
     *
     * @param ta
     * @param pageable
     * @return
     */
    Page<WayBill> findAll(Specification<WayBill> ta, Pageable pageable);


    /**
     * 多条件查询集合
     *
     * @param ta
     * @return
     */
    List<WayBill> findAllByCondition(Specification<WayBill> ta);

}

