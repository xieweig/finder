package cn.sisyphe.coffee.bill.infrastructure.base;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;

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
}

