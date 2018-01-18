package cn.sisyphe.coffee.bill.domain.base.model.location;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by heyong on 2017/12/26 17:35
 * Description: 基础单据位置
 * @author heyong
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractLocation {

    /**
     * 位置代码
     * @return
     */
    public abstract String code();
}
