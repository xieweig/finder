package cn.sisyphe.coffee.bill.domain.transmit;

import cn.sisyphe.coffee.bill.infrastructure.transmit.WayBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * 运货单
 * Created by Administrator on 2017/12/28.
 */
@Service
public class IWayBillServiceImpl implements IWayBillService {

    @Autowired
    private WayBillRepository wayBillRepository;


    /**
     * 条件查询
     *
     * @param wayBill
     * @return
     */
    @Override
    public List<WayBill> findByConditions(WayBill wayBill) {


        return wayBillRepository.findAllByCondition((root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            // bill编码
            if (!StringUtils.isEmpty(wayBill.getBillCode())) {
                expressions.add(criteriaBuilder.like(root.get("billCode").as(String.class),
                        "%" + wayBill.getBillCode() + "%"));
            }
            //物流公司名称
            if (!StringUtils.isEmpty(wayBill.getLogisticsCompanyName())) {
                expressions.add(criteriaBuilder.like(root.get("logisticsCompanyName").as(String.class),
                        "%" + wayBill.getLogisticsCompanyName() + "%"));
            }
            //id
            if (wayBill.getBillId() != null) {
                //id
                expressions.add(criteriaBuilder.equal(root.get("id").as(Long.class), wayBill.getBillId()));
            }
            //单据状态
            if (wayBill.getBillState() != null) {

                expressions.add(criteriaBuilder.equal(root.get("billState").as(Long.class), wayBill.getBillState()));
            }
            return predicate;

        });

    }
}
