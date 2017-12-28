package cn.sisyphe.coffee.bill.domain.base.behavior;


/**
 * 运单的保存动作
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2017/12/28 13:47
 **/
public class WayBillSaveBehavior extends AbstractBillBehavior {


    /**
     * 运单的动作和其他单据流程不一样，实现自己的方法
     *
     * @company 西西弗文化传播
     * @author yichuan
     * @Date 2017/12/28 13:47
     **/
    @Override
    public void doAction() {

        System.out.println("do wayBill  Action...");
    }
}
