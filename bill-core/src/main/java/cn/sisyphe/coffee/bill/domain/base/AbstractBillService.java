package cn.sisyphe.coffee.bill.domain.base;

import cn.sisyphe.coffee.bill.domain.base.behavior.AbstractBillBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.BillBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.purpose.BillPurpose;
import cn.sisyphe.coffee.bill.domain.base.purpose.BillPurposeFactory;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import cn.sisyphe.coffee.bill.util.BillCodeManager;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Created by heyong on 2017/12/19 12:07
 * Description: 基础单据服务，用于调用单据动作
 *
 * @author heyong
 */
public abstract class AbstractBillService {

    /**
     * 单据用途
     */
    private BillPurpose billPurpose;

    /**
     * 单据动作
     */
    private BillBehavior billBehavior;

    /**
     * 单据
     */
    private Bill bill;

    /**
     * 单据数据仓库
     */
    private BillRepository billRepository;


    /**
     * 构造方法
     *
     * @param bill
     */
    public AbstractBillService(Bill bill) {
        if (bill == null || bill.getBillPurpose() == null) {
            throw new DataException("001", "单据作用类型错误");
        }

        this.bill = bill;
        this.bill.setBillCode(BillCodeManager.getBillCodeFun(bill.getBillCodePrefix(), bill.getBelongStationCode()));
        this.billPurpose = BillPurposeFactory.createPurpose(bill.getBillPurpose());
        this.billPurpose.setBillService(this);
    }

    /**
     * 某个单据特殊前置动作处理
     */
    public abstract void beforeDispose();

    /**
     * 某个单据特殊后置动作处理
     */
    public abstract void afterDispose();

    /**
     * 默认单据动作调用
     *
     * @param behavior
     */
    public void dispose(AbstractBillBehavior behavior) {
        this.billBehavior = behavior;
        // 设置单据服务
        behavior.setBillService(this);
        // 执行前置特殊动作
        beforeDispose();
        // 执行默认动作
        behavior.doAction();
        // 执行后置特殊动作
        afterDispose();
    }

    /**
     * 发送事件
     *
     * @param applicationEventPublisher
     */
    public void sendEvent(ApplicationEventPublisher applicationEventPublisher) {
        billBehavior.sendEvent(applicationEventPublisher);
    }

    /**
     * 保存单据
     */
    public void save() {
        if (billRepository == null) {
            throw new DataException("002", "没有设置单据的数据库仓库");
        }

        billRepository.save(bill);
    }

    public BillPurpose getBillPurpose() {
        return billPurpose;
    }

    public void setBillPurpose(BillPurpose billPurpose) {
        this.billPurpose = billPurpose;
    }

    public BillBehavior getBillBehavior() {
        return billBehavior;
    }

    public void setBillBehavior(BillBehavior billBehavior) {
        this.billBehavior = billBehavior;
    }

    public BillRepository getBillRepository() {
        return billRepository;
    }

    public void setBillRepository(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

}
