package cn.sisyphe.coffee.bill.infrastructure.base;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


/**
 * @author heyong
 * @date 2017/1/9
 * <p>
 * 单据基础数据库操作
 */

public interface BillRepository<T extends Bill> {

    /**
     * 保存
     *
     * @param bill
     */
    void save(T bill);

    /**
     * 保存
     *
     * @param bills
     */
    void save(List<T> bills);

    /**
     * 按单号查询
     *
     * @param billCode
     * @return
     */
    T findOneByBillCode(String billCode);

    /**
     * 根据来源单号查询
     * @param sourceCode
     * @return
     */
    T findOneBySourceCode(String sourceCode);

    /**
     * 根据发起单号发查
     * @param rootCode
     * @return
     */
    T findOneByRootCode(String rootCode);

    /**
     * 复杂查询
     * @param ta
     * @param pageable
     * @return
     */
    Page<T> findAll(Specification<T> ta, Pageable pageable );
}

