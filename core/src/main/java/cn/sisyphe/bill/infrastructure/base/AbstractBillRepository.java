package cn.sisyphe.bill.infrastructure.base;

import cn.sisyphe.bill.domain.base.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by heyong on 2017/12/22 17:10
 * Description: 单据基础数据库操作
 *
 * @author heyong
 */
public class AbstractBillRepository<T extends Bill> implements BillRepository<T> {

    @Autowired
    private JpaRepository<T, Long> billRepository;

    @Override
    public void save(T bill) {
        billRepository.save(bill);
    }

    @Override
    public T findOne(Long id) {
        return billRepository.findOne(id);
    }
}
