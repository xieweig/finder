package cn.sisyphe.coffee.bill.application.base.purpose.interfaces;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;

/**
 * @Date 2018/1/11 17:23
 * @description
 */
public interface Executor<T extends Bill> {

    void apply(T bill);
}
