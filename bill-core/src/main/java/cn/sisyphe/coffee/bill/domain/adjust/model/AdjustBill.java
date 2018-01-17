package cn.sisyphe.coffee.bill.domain.adjust.model;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：调剂单
 * version: 1.0
 *
 * @author XiongJing
 */
@Entity
@Table(name = "adjust_bill")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class AdjustBill extends Bill<BillDetail> {
    public static final String ADJUST_OUT_STORAGE_PREFIX = "TJCK";

    public AdjustBill() {
        setBillCodePrefix(ADJUST_OUT_STORAGE_PREFIX);
        setBillType(BillTypeEnum.ADJUST);
    }

    /**
     * 是否是自主调剂
     */

    public boolean isSelfAdjust() {
        return StringUtils.isEmpty(getRootCode());
    }

//    /**
//     * 反序列化单据详情
//     *
//     * @param abstractBillDetailDtos
//     */
//    @Override
//    public void unbuildDetails(Set<AbstractBillDetailDTO> abstractBillDetailDtos) {
//        Set<AdjustBillDetail> adjustBillDetails = new HashSet<AdjustBillDetail>(16);
//        for (AbstractBillDetailDTO abstractBillDetailDTO : abstractBillDetailDtos) {
//            if (abstractBillDetailDTO != null) {
//                AdjustBillDetail adjustBillDetail = new AdjustBillDetail();
//                adjustBillDetail.unbuild(abstractBillDetailDTO);
//                adjustBillDetails.add(adjustBillDetail);
//            }
//        }
//    }
//
//    /**
//     * 反序列化扩展
//     *
//     * @param abstractBillDTO
//     */
//    @Override
//    protected void unbuildExcend(AbstractBillDTO abstractBillDTO) {
//
//    }
}
