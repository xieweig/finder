package cn.sisyphe.coffee.bill.domain.returned;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author ncmao
 * @Date 2017/12/27 16:10
 * @description 退货计划单
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class ReturnedBill extends Bill<ReturnedBillDetail> {
}
