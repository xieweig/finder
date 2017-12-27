package cn.sisyphe.coffee.bill.domain.plan.strategy;

import cn.sisyphe.coffee.bill.domain.base.model.BillFactory;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillPurposeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.goods.AbstractGoods;
import cn.sisyphe.coffee.bill.domain.delivery.DeliveryBillDetail;
import cn.sisyphe.coffee.bill.domain.plan.ItemPayload;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBill;
import cn.sisyphe.coffee.bill.domain.returned.ReturnedBillDetail;
import cn.sisyphe.framework.web.exception.DataException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.StationType.LOGISTICS;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.StationType.STORE;


/**
 * @author ncmao
 * 退货计划单策略
 */

@Service
public class ReturnedStrategy implements CastableStrategy {


    @Override
    public void cast(ItemPayload itemPayload) {


    }

    public ReturnedBill pack(ItemPayload itemPayload) {
        //出站点为物流则直接生成退货单
        if (LOGISTICS.equals(itemPayload.getOutStation().getStationType())) {
            ReturnedBill returnedBill = (ReturnedBill) new BillFactory().createBill(BillTypeEnum.RETURNED);
            returnedBill.setInLocation(itemPayload.getInStation());
            returnedBill.setOutLocation(itemPayload.getOutStation());
            returnedBill.setBillPurpose(BillPurposeEnum.OutStorage);
            Set<ReturnedBillDetail> billDetails = new HashSet<>();
            for (AbstractGoods goods : itemPayload.getGoods()) {
                ReturnedBillDetail returnedBillDetail = new ReturnedBillDetail();
                returnedBillDetail.setGoods(goods);
                billDetails.add(returnedBillDetail);
            }
            returnedBill.setBillDetails(billDetails);
            return returnedBill;
        }
        //出站点为门店则先生成退库单，再生成退货单
        if (STORE.equals(itemPayload.getOutStation().getStationType())) {

        }

        throw new DataException("123456", "站点选择有错误!");
    }
}
