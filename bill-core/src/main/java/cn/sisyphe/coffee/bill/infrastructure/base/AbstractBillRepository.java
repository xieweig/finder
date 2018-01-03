package cn.sisyphe.coffee.bill.infrastructure.base;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by heyong on 2017/12/22 17:10
 * Description: 单据基础数据库操作
 *
 * @author heyong
 */
public abstract class AbstractBillRepository<T extends Bill> implements BillRepository<T> {

    @Autowired
    private JpaRepository<T, Long> billRepository;

    //不能被重写，重写之后导致某些字段无法更新
    @Override
    public final void save(T bill) {

        // 更新单据的关系
        bill.update();

        for (Object object : bill.getBillDetails()) {
            BillDetail detail = (BillDetail) object;
            detail.update();
        }

        billRepository.save(bill);
    }


}
