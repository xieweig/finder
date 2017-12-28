package cn.sisyphe.coffee.bill.domain.transmit;

import cn.sisyphe.coffee.bill.infrastructure.transmit.WayBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 运货单
 * Created by Administrator on 2017/12/28.
 */
@Service
public class IWayBillServiceImpl implements IWayBillService {

    @Autowired
    private WayBillRepository wayBillRepository;

}
