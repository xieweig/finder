package cn.sisyphe.coffee.bill.infrastructure.share.supplier.repo;

import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.framework.web.ResponseResult;

/**
 * @author ncmao
 * @Date 2017/12/29 10:03
 * @description
 */
public interface SupplierRepository {


    Supplier findBySupplierCode(String supplierCode);
}
