package cn.sisyphe.coffee.bill.application.base;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;

/**
 * Created by heyong on 2018/1/16 16:01
 * Description:
 *
 * @author heyong
 */
public abstract class AbstractBillExtraManager<T extends Bill, K extends ConditionQueryBill> extends AbstractBillManager<T> {

    public AbstractBillExtraManager(BillRepository<T> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(billRepository, applicationEventPublisher);
    }

    /**
     * 入库单查询
     *
     * @param conditionQuery 条件查询参数
     * @return 查询结果带分页信息
     */

    public Page<T> findInStorageBillByCondition(K conditionQuery) {
        throw new UnsupportedOperationException();
    }

    /**
     * 出库单查询
     *
     * @param conditionQuery 条件查询参数
     * @return 查询结果带分页信息
     */

    public Page<T> findOutStorageBillByCondition(K conditionQuery) {
        throw new UnsupportedOperationException();
    }
}
