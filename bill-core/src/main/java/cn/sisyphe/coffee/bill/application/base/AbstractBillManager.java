package cn.sisyphe.coffee.bill.application.base;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.BillServiceFactory;
import cn.sisyphe.coffee.bill.domain.base.behavior.AbstractBillBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.AuditBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.DoneBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.OpenBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.PurposeBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.SaveBehavior;
import cn.sisyphe.coffee.bill.domain.base.behavior.SubmitBehavior;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillAllotStatusEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillInOrOutStateEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.infrastructure.base.BillRepository;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Date;

/**
 * Created by heyong on 2018/1/2 17:47
 * Description: 单据基础管理器
 *
 * @author heyong
 */
public abstract class AbstractBillManager<T extends Bill> {

    /**
     * 持久化
     */
    private BillRepository<T> billRepository;

    /**
     * 事件发布
     */
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
     * 单据类型
     *
     * @return
     */
    public abstract BillTypeEnum billType();

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
     *
     * @param bill
     * @return
     */
    public T open(T bill) {
        return dispose(bill, new OpenBehavior());
    }

    /**
     * 审核
     *
     * @param bill
     * @return
     */
    public T audit(T bill, boolean isSuccess) {
        return dispose(bill, new AuditBehavior(isSuccess));
    }


    /**
     * 具体行为处理
     *
     * @param bill
     * @return
     */
    public T purpose(T bill) {
        return dispose(bill, new PurposeBehavior());
    }

    /**
     * 出库成功
     */
    public T outStorageSuccess(T bill) {
        if (!BillPurposeEnum.OUT_STORAGE.equals(bill.getBillPurpose())) {
            throw new UnsupportedOperationException("不支持此操作");
        }
        T foundBill = billRepository.findOneByBillCode(bill.getBillCode());
        //设置出库时间
        foundBill.setInOrOutState(BillInOrOutStateEnum.OUT_SUCCESS);
        foundBill.setOutWareHouseTime(new Date());
        billRepository.save(foundBill);
        return foundBill;
    }

    /**
     * 出库失败
     */
    public T outStorageFail(T bill) {
        if (!BillPurposeEnum.OUT_STORAGE.equals(bill.getBillPurpose())) {
            throw new UnsupportedOperationException("不支持此操作");
        }
        T foundBill = billRepository.findOneByBillCode(bill.getBillCode());
        foundBill.setInOrOutState(BillInOrOutStateEnum.OUT_FAILURE);
//        bill.setOutWareHouseTime(new Date());
        billRepository.save(foundBill);
        return foundBill;
    }

    /**
     * 已调拨
     *
     * @param billCode
     */
    public void committed(String billCode) {

        T bill = billRepository.findOneByBillCode(billCode);
        if (!BillPurposeEnum.IN_STORAGE.equals(bill.getBillPurpose())) {
            throw new UnsupportedOperationException("不支持此操作");
        }
        bill.setAllotStatus(BillAllotStatusEnum.ALLOT);
        bill.setInWareHouseTime(new Date());
        billRepository.save(bill);
    }

    /**
     * 调拨中
     *
     * @param billCode
     */
    public void committing(String billCode) {
        T bill = billRepository.findOneByBillCode(billCode);
        if (!BillPurposeEnum.IN_STORAGE.equals(bill.getBillPurpose())) {
            throw new UnsupportedOperationException("不支持此操作");
        }
        bill.setAllotStatus(BillAllotStatusEnum.ALLOTTING);

        billRepository.save(bill);


    }


    /**
     * 处理完成
     *
     * @param bill
     * @return
     */
    public T done(T bill) {
        return dispose(bill, new DoneBehavior());
    }


    /**
     * 动作处理
     *
     * @param bill
     * @param abstractBillBehavior
     * @return
     */
    private T dispose(T bill, AbstractBillBehavior abstractBillBehavior) {

        // 生成单据服务
        BillServiceFactory serviceFactory = new BillServiceFactory();
        AbstractBillService billService = serviceFactory.createBillService(bill);
        // 设置数据库仓库
        billService.setBillRepository(billRepository);

        // 动作调用
        billService.dispose(abstractBillBehavior);
        // 保存数据到数据库中
        billService.save();
        // 发送事件
        billService.sendEvent(applicationEventPublisher);

        return bill;
    }

    /**
     * 查询
     *
     * @param billCode
     * @return
     */
    public Bill findOneByBillCode(String billCode) {
        return billRepository.findOneByBillCode(billCode);
    }


}
