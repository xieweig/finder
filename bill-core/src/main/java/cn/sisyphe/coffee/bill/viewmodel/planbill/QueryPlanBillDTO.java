package cn.sisyphe.coffee.bill.viewmodel.planbill;

import cn.sisyphe.coffee.bill.viewmodel.purchase.PurchaseBillDTO;

import java.util.List;

public class QueryPlanBillDTO {

    /**
     * 总数量
     */
    private Long totalNumber;
    /**
     * 具体内容
     */
    private List<QueryPlanDetailBillDTO> content;

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<QueryPlanDetailBillDTO> getContent() {
        return content;
    }

    public void setContent(List<QueryPlanDetailBillDTO> content) {
        this.content = content;
    }
}
