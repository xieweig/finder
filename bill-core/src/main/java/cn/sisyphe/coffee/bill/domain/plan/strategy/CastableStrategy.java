package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.plan.ItemPayload;

/**
 * @author ncmao
 * @Date 2017/12/27 11:30
 * @description
 */
public interface CastableStrategy {


    //将计划单进行切分
    void cast(ItemPayload itemPayload);
}