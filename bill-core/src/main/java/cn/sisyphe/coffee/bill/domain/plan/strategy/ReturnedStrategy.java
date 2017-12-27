package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.plan.ItemPayload;
import org.springframework.stereotype.Service;

/**
 * @author ncmao
 * 退货计划单策略
 */

@Service
public class ReturnedStrategy implements CastableStrategy {


    @Override
    public void cast(ItemPayload itemPayload) {

    }
}
