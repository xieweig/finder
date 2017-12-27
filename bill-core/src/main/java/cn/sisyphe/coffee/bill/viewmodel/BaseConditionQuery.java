package cn.sisyphe.coffee.bill.viewmodel;

/**
 * Created by XiongJing on 2017/12/27.
 * remark：基础查询条件
 * version: 1.0
 *
 * @author XiongJing
 */
public class BaseConditionQuery {
    /**
     * 页码
     */
    public int page;
    /**
     * 每页大小
     */
    public int pageSize;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "BaseConditionQuery{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
