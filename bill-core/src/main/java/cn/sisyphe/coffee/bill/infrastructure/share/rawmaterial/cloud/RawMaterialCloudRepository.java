package cn.sisyphe.coffee.bill.infrastructure.share.rawmaterial.cloud;

import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ncmao
 * @Date 2017/12/29 10:16
 * @description
 */
@FeignClient(value = "COFFEE-BASEINFO", fallback = LocalRawMaterialCloudRepository.class)
public interface RawMaterialCloudRepository {

    /**
     * 根据原料名称模糊查询原料编号集合
     *
     * @param rawMaterialName
     * @return
     */
    @RequestMapping(path = "/api/v1/baseInfo/cargo/findCargoCodeListByCargoName", method = RequestMethod.GET)
    ResponseResult findRawMaterialCodesByRawMaterialName(@RequestParam("rawMaterialName") String rawMaterialName);
}
