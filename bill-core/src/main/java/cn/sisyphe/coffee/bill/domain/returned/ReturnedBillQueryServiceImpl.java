package cn.sisyphe.coffee.bill.domain.returned;


import cn.sisyphe.coffee.bill.infrastructure.returned.ReturnedBillRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.user.repo.UserRepository;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Mayupeng on 2018/01/05.
 * remark：退货单查询服务接口实现
 * version: 1.0
 *
 * @author Mayupeng
 */
@Service
public class ReturnedBillQueryServiceImpl implements  ReturnedBillQueryService{
    @Autowired
    private ReturnedBillRepository returnedBillRepository;

    /*@Autowired
    private UserRepository userRepository;*/

    /**
     * 根据单据编码查询单据信息
     *
     * @param billCode 单据编码
     * @return
     */
    @Override
    public ReturnedBill findByBillCode(String billCode) {
        if (StringUtils.isEmpty(billCode)) {
            throw new DataException("20011", "进货单编码为空");
        }
        ReturnedBill returnedBill = returnedBillRepository.findOneByBillCode(billCode);
        if (returnedBill != null) {
            return returnedBill;
        } else {
            throw new DataException("20012", "根据该进货单编码没有查询到具体的进货单信息");
        }
    }
}
