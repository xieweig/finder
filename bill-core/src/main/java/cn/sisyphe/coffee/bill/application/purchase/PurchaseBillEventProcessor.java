package cn.sisyphe.coffee.bill.application.purchase;

import cn.sisyphe.coffee.bill.application.base.processor.BaseBillEventProcessor;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import org.springframework.stereotype.Component;

/**
 * Created by heyong on 2017/12/25 15:55
 * Description: 进货单事件监听
 *
 * @author heyong
 */
@Component
public class PurchaseBillEventProcessor extends BaseBillEventProcessor<PurchaseBill> {


}
