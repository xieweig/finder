package cn.sisyphe.coffee.bill.infrastructure.share.supplier.repo;

import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;

/**
 * @author ncmao
 * @Date 2017/12/29 10:03
 * @description
 */
public interface SupplierRepository {

    /**
     * 根据供应商编码查询供应商信息
     *
     * @param supplierCode 供应商编码
     * @return
     */
    Supplier findBySupplierCode(String supplierCode);
}
