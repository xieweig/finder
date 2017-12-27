package cn.sisyphe.coffee.bill.domain.base.purpose;


import cn.sisyphe.coffee.bill.domain.base.model.Bill;

/**
 * Created by heyong on 2017/12/19 14:07
 * Description: 入库单用途处理器
 * @author heyong
 */
public class InStoragePurpose extends AbstractBillPurpose {

    /**
     * 用途处理器
     */
    @Override
    public void handle() {

    }

    /**
     * 监控出库行为
     * @param bill
     */
    public void listener(Bill bill){

    }
}
