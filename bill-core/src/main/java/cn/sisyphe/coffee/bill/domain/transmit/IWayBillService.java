package cn.sisyphe.coffee.bill.domain.transmit;

import cn.sisyphe.coffee.bill.viewmodel.waybill.ConditionQueryWayBill;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28.
 */

public interface IWayBillService {


    /**
     * 多条件查询运单
     *
     * @param wayBill
     * @return
     */
    List<WayBill> findByConditions(WayBill wayBill);

    /**
     * 分页查询运单
     *
     * @param conditionQueryWayBill
     * @return
     * @throws DataException
     */
    Page<WayBill> findPageByCondition(ConditionQueryWayBill conditionQueryWayBill) throws DataException;

}
