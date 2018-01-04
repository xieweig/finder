package cn.sisyphe.coffee.bill.infrastructure.share.station;

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
@FeignClient(value = "COFFEE-BASEINFO", fallback = LocalStationCloudRepository.class)
public interface StationCloudRepository {

    @RequestMapping(path = "/api/v1/baseInfo/station/findByStationCodeForApi", method = RequestMethod.GET)
    ResponseResult findByStationCodeForApi(@RequestParam("stationCode") String stationCode);

    /**
     * 根据站点编号查询其第一物流站点的编号
     *
     * @param stationCode
     * @return
     */
    @RequestMapping(path = "/api/v1/baseInfo/station/findLogisticCodeByStationCodeForApi", method = RequestMethod.GET)
    ResponseResult findLogisticCodeByStationCode(@RequestParam("stationCode") String stationCode);
}
