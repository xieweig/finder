package cn.sisyphe.coffee.bill.domain.base;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.viewmodel.base.ConditionQueryBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public abstract class AbstractBillExtraService<T extends Bill> implements BillExtraService<T> {

    /**
     * 持久化
     */
    private BillRepository<T> billRepository;

    public AbstractBillExtraService(BillRepository<T> billRepository) {
        this.billRepository = billRepository;
    }

    public BillRepository<T> getBillRepository() {
        return billRepository;
    }

    public void setBillRepository(BillRepository<T> billRepository) {
        this.billRepository = billRepository;
    }

    /**
     * 根据单据编码查询单据信息
     *
     * @param billCode 单据编码
     * @return
     */
    @Override
    public T findByBillCode(String billCode) {
        return billRepository.findOneByBillCode(billCode);
    }

    /**
     * 根据sourceCode查询调剂单据
     *
     * @param sourceCode
     * @return
     */
    @Override
    public T findBySourceCode(String sourceCode) {
        return billRepository.findOneBySourceCode(sourceCode);
    }

    /**
     * 根据发起单号发查
     *
     * @param rootCode
     * @return
     */
    @Override
    public T findByRootCode(String rootCode) {
        return billRepository.findOneByRootCode(rootCode);
    }

    /**
     * 多条件查询
     *
     * @param conditionQueryBill
     * @return
     */
    @Override
    public <Q extends ConditionQueryBill> Page<T> findPageByCondition(Q conditionQueryBill) {
        Pageable pageable = new PageRequest(conditionQueryBill.getPage() - 1, conditionQueryBill.getPageSize());
        Page<T> configurePage = pageCondition(conditionQueryBill, pageable);

        // 改变页码导致的页面为空时，获取最后一页
        if (configurePage.getContent().size() < 1 && configurePage.getTotalElements() > 0) {
            pageable = new PageRequest(configurePage.getTotalPages() - 1, conditionQueryBill.getPageSize());
            configurePage = pageCondition(conditionQueryBill, pageable);
        }
        return configurePage;
    }

    /**
     * 条件组装
     * @param conditionQuery
     * @param pageable
     * @param <Q>
     * @return
     */
    protected <Q extends ConditionQueryBill> Page<T> pageCondition(Q conditionQuery, Pageable pageable){
        return null;
    }
}
