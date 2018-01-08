package cn.sisyphe.coffee.bill.domain.adjust;

import cn.sisyphe.coffee.bill.infrastructure.adjust.AdjustBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：
 * version:
 */

@Service
public class AdjustBillExtraServiceImpl implements AdjustBillExtraService {

    @Autowired
    private AdjustBillRepository adjustBillRepository;

    @Override
    public AdjustBill findByBillCode(String billCode) {
        return adjustBillRepository.findOneByBillCode(billCode);
    }
}
