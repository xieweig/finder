package cn.sisyphe.coffee.bill.viewmodel.deliverybill;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/5.
 */
public class QueryDeliveryBillDTO implements Serializable {

    /**
     * 总数量
     */
    private Long totalNumber;

    /**
     * 查询返回的数据集合
     */
    private List<DeliveryBillDTO> content;

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<DeliveryBillDTO> getContent() {
        return content;
    }

    public void setContent(List<DeliveryBillDTO> content) {
        this.content = content;
    }
}
