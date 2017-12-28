package cn.sisyphe.coffee.bill.infrastructure.transmit;

import cn.sisyphe.coffee.bill.domain.transmit.WayBill;
import cn.sisyphe.coffee.bill.infrastructure.base.AbstractBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.transmit.jpa.JPAWayBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 运单JPA
 * Created by Administrator on 2017/12/27.
 */
@Service
public class WayBillRepositoryImpl extends AbstractBillRepository<WayBill>
        implements WayBillRepository {

    @Autowired
    private JPAWayBillRepository jpaWayBillRepository;


    /**
     * 查找单个
     *
     * @param billCode
     * @return
     */
    @Override
    public WayBill findOneByCode(String billCode) {
        return jpaWayBillRepository.findOneByBillCode(billCode);
    }

    /**
     * 查找所有
     *
     * @param ta
     * @param pageable
     * @return
     */
    @Override
    public Page<WayBill> findAll(Specification<WayBill> ta, Pageable pageable) {
        return jpaWayBillRepository.findAll(ta, pageable);
    }

    @Override
    public List<WayBill> findAllByCondition(Specification<WayBill> ta) {
        return null;
    }


    /**
     * @param id
     * @return
     */
    @Override
    public WayBill findOne(Long id) {
        return jpaWayBillRepository.findOne(id);
    }
}
