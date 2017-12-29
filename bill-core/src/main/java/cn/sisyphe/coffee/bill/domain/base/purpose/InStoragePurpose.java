package cn.sisyphe.coffee.bill.domain.base.purpose;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.framework.message.core.MessagingHelper;

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
        // 入库操作
        try {
        // 发消息到库存冲减中
        MessagingHelper.messaging().convertAndSend("cn_sisyphe_coffee_bill", "askcard.trade.2.2.Unprocessed", null);

        }catch (Exception e){
            // TODO: 2017/12/29

        }

    }

    /**
     * 监控出库行为
     * @param bill
     */
    public void listener(Bill bill){

    }
}
