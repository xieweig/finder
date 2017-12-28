package cn.sisyphe.coffee.bill.domain.transmit;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28.
 */

public interface IWayBillService {

    List<WayBill> findByConditions(WayBill wayBill);
}
