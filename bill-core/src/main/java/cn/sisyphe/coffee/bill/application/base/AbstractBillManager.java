package cn.sisyphe.coffee.bill.application.base;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.*;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Created by heyong on 2018/1/2 17:47
 * Description: 单据基础管理器
 *
 * @author heyong
 */
public class AbstractBillManager<T extends Bill> {

    private BillRepository<T> billRepository;

    private ApplicationEventPublisher applicationEventPublisher;

    public BillRepository<T> getBillRepository() {
        return billRepository;
    }

    public void setBillRepository(BillRepository<T> billRepository) {
        this.billRepository = billRepository;
    }

    public AbstractBillManager(BillRepository<T> billRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.billRepository = billRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 保存
     *
     * @param bill
     * @return
     */
    public T save(T bill) {
        return dispose(bill, new SaveBehavior());
    }

    /**
     * 提交
     *
     * @param bill
     * @return
     */
    public T submit(T bill) {

        return dispose(bill, new SubmitBehavior());
    }

    /**
     * 打开
     * @param bill
     * @return
     */
    public T open(T bill) {
        return dispose(bill, new OpenBehavior());
    }

    /**
     * 审核成功
     * @param bill
     * @return
     */
    public T auditSuccess(T bill){
        return dispose(bill, new AuditBehavior(true));
    }

    /**
     * 审核失败
     * @param bill
     * @return
     */
    public T auditFailed(T bill){

        return dispose(bill, new AuditBehavior(false));
    }

    /**
     * 具体行为处理
     * @param bill
     * @return
     */
    public T purpose(T bill){
        return dispose(bill, new PurposeBehavior());
    }

    /**
     * 处理完成
     * @param bill
     * @return
     */
    public T done(T bill){
        return dispose(bill, new DoneBehavior());
    }


    /**
     * 动作处理
     * @param bill
     * @param abstractBillBehavior
     * @return
     */
    private T dispose(T bill, AbstractBillBehavior abstractBillBehavior) {

        // 生成单据服务
        BillServiceFactory serviceFactory = new BillServiceFactory();
        AbstractBillService purchaseBillService = serviceFactory.createBillService(bill);
        // 动作调用
        purchaseBillService.dispose(abstractBillBehavior);
        // 设置数据库仓库
        purchaseBillService.setBillRepository(billRepository);
        // 保存数据到数据库中
        purchaseBillService.save();
        // 发送事件
        purchaseBillService.sendEvent(applicationEventPublisher);

        return bill;
    }

}
