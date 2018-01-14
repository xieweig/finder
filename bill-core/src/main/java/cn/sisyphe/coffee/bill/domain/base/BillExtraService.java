package cn.sisyphe.coffee.bill.domain.base;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import org.springframework.data.domain.Page;

public interface BillExtraService<T extends Bill> {

    /**
     * 根据单据编码查询单据信息
     *
     * @param billCode 单据编码
     * @return
     */
    T findByBillCode(String billCode);

    /**
     * 根据sourceCode查询调剂单据
     *
     * @param sourceCode
     * @return
     */
    T findBySourceCode(String sourceCode);

    /**
     * 根据发起单号发查
     *
     * @param rootCode
     * @return
     */
    T findByRootCode(String rootCode);

    /**
     * 多条件查询
     *
     * @param conditionQueryBill
     * @param <Q>
     * @return
     */
    <Q extends ConditionQueryBill> Page<T> findPageByCondition(Q conditionQueryBill);
}
