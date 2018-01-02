package cn.sisyphe.coffee.bill.domain.base.behavior;

/**
 * Created by heyong on 2017/12/19 14:36
 * Description: 某个用途（计划、出库、入库）的动作处理
 *
 * @author heyong
 */
public class PurposeBehavior extends AbstractBillBehavior {

    /**
     * 执行动作，可调用单据动作的处理
     */
    @Override
    public void doAction() {
        getBillService().getBillPurpose().handle();
    }
}
