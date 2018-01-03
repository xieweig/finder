package cn.sisyphe.coffee.bill.domain.base.purpose;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;

/**
 * Created by heyong on 2017/12/19 11:08
 * Description: 单据用途处理
 *
 * @author heyong
 */
public interface BillPurpose {

    /**
     * 单据服务引用
     *
     * @param billService
     */
    void setBillService(AbstractBillService billService);

    /**
     * 用途处理器
     */
    void handle();
}
