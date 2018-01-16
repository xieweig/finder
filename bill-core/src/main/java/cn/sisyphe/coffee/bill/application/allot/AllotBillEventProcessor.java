package cn.sisyphe.coffee.bill.application.allot;

import cn.sisyphe.coffee.bill.application.base.processor.BaseBillEventProcessor;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import org.springframework.stereotype.Component;

/**
 * Created by heyong on 2018/1/16 12:10
 * Description: 调拨事件监听器
 *
 * @author heyong
 */
@Component
public class AllotBillEventProcessor extends BaseBillEventProcessor<AllotBill> {
}
