package cn.sisyphe.coffee.bill.viewmodel.waybill;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * waybill分页返回的DTO对象
 * Created by yichuan on 2018/1/2.
 */
public class QueryWayBillDTO implements Serializable {
    /**
     * 总数量
     */
    private Long totalNumber;

    /**
     * 当前页
     */
    @JsonIgnore
    private Long currentPage;

    /**
     * 具体内容
     */
    private List<ReturnWayBillDTO> content;

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<ReturnWayBillDTO> getContent() {
        return content;
    }

    public void setContent(List<ReturnWayBillDTO> content) {
        this.content = content;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "QueryWayBillDTO{" +
                "totalNumber=" + totalNumber +
                ", currentPage=" + currentPage +
                ", content=" + content +
                '}';
    }
}
