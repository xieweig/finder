package cn.sisyphe.coffee.bill.infrastructure.share.supplier.repo;

import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.infrastructure.share.supplier.SupplierCloudRepository;
import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ncmao
 * @Date 2017/12/29 10:03
 * @description
 */

@Repository
public class SupplierRepositoryImpl implements SupplierRepository {

    @Autowired
    private SupplierCloudRepository supplierCloudRepository;


    @Override
    public Supplier findBySupplierCode(String supplierCode) {
        ResponseResult response = supplierCloudRepository.findSupplierByCode(supplierCode);
        Map<String, Object> resultMap = response.getResult();
        if (!resultMap.containsKey("result")) {
            return null;
        }
        LinkedHashMap<String, String> properties = (LinkedHashMap) ((LinkedHashMap) resultMap.get("result")).get("supplier");
        Supplier supplier = new Supplier(properties.get("supplierCode"));
        supplier.setSupplierName(properties.get("supplierName"));
        return supplier;
    }

    /**
     * 根据供应商名称模糊查询供应商编码
     * @param supplierName
     * @return
     */
    @Override
    public List<String> findByLikeSupplierName(String supplierName) {
        ResponseResult responseResult = supplierCloudRepository.findByLikeSupplierName(supplierName);
        Map<String, Object> resultMap = responseResult.getResult();
        if (!resultMap.containsKey("result")) {
            return null;
        }
        List<String> supplierCodeList = (ArrayList)resultMap.get("result");
        return supplierCodeList;
    }
}
