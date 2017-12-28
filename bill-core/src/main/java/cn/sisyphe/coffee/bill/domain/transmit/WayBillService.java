package cn.sisyphe.coffee.bill.domain.transmit;

import cn.sisyphe.coffee.bill.domain.base.AbstractBillService;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.infrastructure.transmit.WayBillRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2017/12/27 16:57
 **/

public class WayBillService extends AbstractBillService {

    @Autowired
    private IWayBillService iWayBillService;

    //1自定义的rspository

    //2.实现方法

    //3在本类调用

    //4在manager 通过对象访问成员函数

    /**
     * 构造方法
     *
     * @param
     * @param bill
     * @param wayBillRepository
     */
    public WayBillService(Bill bill, WayBillRepository wayBillRepository) {
        super(bill);

    }

    /**
     * 调用处理前的方法
     */
    @Override
    public void beforeDispose() {
        System.out.println("处理前。。。。。。。。");
    }

    /**
     * 调用处理后的方法
     */
    @Override
    public void afterDispose() {

        System.out.println("处理后。。。。。。。。");
    }

    /**
     * 多条件查询
     *
     * @param wayBill
     * @return
     */
    public List<WayBill> findByConditions(WayBill wayBill) {

        return iWayBillService.findByConditions(wayBill);
    }


}
