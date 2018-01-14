package cn.sisyphe.coffee.bill.domain.base;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;

public interface BillExtraService<T extends Bill> {

    /**
     * 根据单据编码查询单据信息
     *
     * @param billCode 单据编码
     * @return
     */
    T findOneByBillCode(String billCode);

    /**
     * 根据sourceCode查询调剂单据
     *
     * @param sourceCode
     * @return
     */
    T findBySourceCode(String sourceCode);
}
