package cn.sisyphe.coffee.bill.viewmodel.purchase;

import java.util.List;

/**
 * Created by XiongJing on 2017/12/28.
 * remark：进货单据查询返回DTO
 * version: 1.0
 *
 * @author XiongJing
 */
public class QueryPurchaseBillDTO {
    /**
     * 总数量
     */
    private Long totalNumber;
    /**
     * 具体内容
     */
    private List<PurchaseBillDTO> content;

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<PurchaseBillDTO> getContent() {
        return content;
    }

    public void setContent(List<PurchaseBillDTO> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "QueryPurchaseBillDTO{" +
                "totalNumber=" + totalNumber +
                ", content=" + content +
                '}';
    }
}
