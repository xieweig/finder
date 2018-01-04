package cn.sisyphe.coffee.bill.viewmodel.restock;

import java.util.List;

public class QueryRestockBillDTO {
    /**
     * 总数量
     */
    private Long totalNumber;
    /**
     * 具体内容
     */
    private List<RestockBillDTO> content;

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<RestockBillDTO> getContent() {
        return content;
    }

    public void setContent(List<RestockBillDTO> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "QueryRestockBillDTO{" +
                "totalNumber=" + totalNumber +
                ", content=" + content +
                '}';
    }
}
