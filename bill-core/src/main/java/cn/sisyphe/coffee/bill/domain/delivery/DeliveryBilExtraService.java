package cn.sisyphe.coffee.bill.domain.delivery;


import cn.sisyphe.coffee.bill.domain.base.BillExtraService;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import cn.sisyphe.coffee.bill.viewmodel.deliverybill.ConditionQueryDeliveryBill;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.data.domain.Page;

/**
 * 配送单查询服务类
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2018/1/4 11:06
 **/
public interface DeliveryBilExtraService extends BillExtraService<DeliveryBill> {

}
