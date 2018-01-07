package cn.sisyphe.coffee.bill.viewmodel.returned;

import cn.sisyphe.coffee.bill.viewmodel.returned.ReturnedBillDTO;

import java.util.List;

public class QueryReturnedBillDTO {
    /**
     * 总数量
     */
    private Long totalNumber;
    /**
     * 具体内容
     */
    private List<ReturnedBillDTO> content;

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<ReturnedBillDTO> getContent() {
        return content;
    }

    public void setContent(List<ReturnedBillDTO> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "QueryReturnedBillDTO{" +
                "totalNumber=" + totalNumber +
                ", content=" + content +
                '}';
    }
}
