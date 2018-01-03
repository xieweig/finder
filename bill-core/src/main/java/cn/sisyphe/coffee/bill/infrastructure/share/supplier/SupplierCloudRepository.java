package cn.sisyphe.coffee.bill.infrastructure.share.supplier;

import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ncmao
 * @Date 2017/12/29 9:55
 * @description
 */
@FeignClient(value = "COFFEE-BASEINFO-API", fallback = LocalSupplierCloudRepository.class)
public interface SupplierCloudRepository {

    /**
     * 根据供应商编码查询供应商信息
     *
     * @param supplierCode 供应商编码
     * @return
     */
    @RequestMapping(path = "/api/v1/baseInfo/supplier/findBySupplierCode", method = RequestMethod.GET)
    ResponseResult findSupplierByCode(@RequestParam("supplierCode") String supplierCode);
}
