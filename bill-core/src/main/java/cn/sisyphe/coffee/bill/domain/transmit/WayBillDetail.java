package cn.sisyphe.coffee.bill.domain.transmit;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 运货单明细
 */
@Entity
@Table
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class WayBillDetail {
}
