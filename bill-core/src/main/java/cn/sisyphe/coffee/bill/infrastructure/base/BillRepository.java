package cn.sisyphe.coffee.bill.infrastructure.base;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;


/**
 * @author heyong
 * @date 2017/1/9
 *
 *  单据基础数据库操作
 */

public interface BillRepository<T extends Bill> {

    /**
     * 保存
     * @param bill
     */
    void save(T bill);

    /**
     * 按单号查询
     * @param billCode
     * @return
     */
    T findOneByBillCode(String billCode);
}

