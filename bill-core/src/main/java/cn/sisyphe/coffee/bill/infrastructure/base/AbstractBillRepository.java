package cn.sisyphe.coffee.bill.infrastructure.base;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.infrastructure.base.jpa.JpaBillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


/**
 * Created by heyong on 2017/12/22 17:10
 * Description: 单据基础数据库操作
 *
 * @author heyong
 */
public class AbstractBillRepository<T extends Bill> implements BillRepository<T> {

    /**
     * JPA持久化
     */
    private JpaBillRepository<T> jpaBillRepository;

    public AbstractBillRepository(JpaBillRepository<T> jpaBillRepository) {
        this.jpaBillRepository = jpaBillRepository;
    }

    public JpaBillRepository<T> getJpaBillRepository() {
        return jpaBillRepository;
    }

    public void setJpaBillRepository(JpaBillRepository<T> jpaBillRepository) {
        this.jpaBillRepository = jpaBillRepository;
    }

    /**
     * 保存
     *
     * @param bill
     */
    @Override
    public final void save(T bill) {

        // 更新单据的关系
        bill.update();

        for (Object object : bill.getBillDetails()) {
            BillDetail detail = (BillDetail) object;
            detail.update();
        }

        jpaBillRepository.save(bill);
    }

    /**
     * 批量保存
     *
     * @param bills
     */
    @Override
    public final void save(List<T> bills) {
        for (Bill bill : bills) {
            // 更新单据的关系
            bill.update();

            for (Object object : bill.getBillDetails()) {
                BillDetail detail = (BillDetail) object;
                detail.update();
            }
        }
        jpaBillRepository.save(bills);
    }

    /**
     * 按单号查询
     *
     * @param billCode
     * @return
     */
    @Override
    public T findOneByBillCode(String billCode) {
        return jpaBillRepository.findOneByBillCode(billCode);
    }

    /**
     * 根据来源单号查询
     *
     * @param sourceCode
     * @return
     */
    @Override
    public T findOneBySourceCode(String sourceCode) {
        return jpaBillRepository.findOneBySourceCode(sourceCode);
    }

    /**
     * 根据发起单号发查
     *
     * @param rootCode
     * @return
     */
    @Override
    public T findOneByRootCode(String rootCode) {
        return jpaBillRepository.findOneByRootCode(rootCode);
    }

    /**
     * 复杂查询
     *
     * @param ta
     * @param pageable
     * @return
     */
    @Override
    public Page<T> findAll(Specification<T> ta, Pageable pageable) {
        return jpaBillRepository.findAll(ta, pageable);
    }


}
