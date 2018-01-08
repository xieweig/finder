package cn.sisyphe.coffee.bill.viewmodel.adjust;

import java.util.List;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：多条件查询返回前端DTO
 * version: 1.0
 *
 * @author XiongJing
 */
public class QueryAdjustBillDTO {
    /**
     * 总数量
     */
    private Long totalNumber;
    /**
     * 具体内容
     */
    private List<AdjustBillDTO> content;
}
