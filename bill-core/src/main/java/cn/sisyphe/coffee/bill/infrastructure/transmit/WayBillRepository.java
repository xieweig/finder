package cn.sisyphe.coffee.bill.infrastructure.transmit;


import cn.sisyphe.coffee.bill.domain.transmit.WayBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * 运单JPA
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2017/12/27 17:08
 **/
public interface WayBillRepository {


    WayBill findOneByCode(String billCode);


    Page<WayBill> findAll(Specification<WayBill> ta, Pageable pageable);

}

