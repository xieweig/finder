package cn.sisyphe.coffee.bill.infrastructure.share.station;

import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.stereotype.Component;

/**
 * @author ncmao
 * @Date 2017/12/29 10:18
 * @description
 */

@Component
public class LocalStationCloudRepository implements StationCloudRepository {

    @Override
    public ResponseResult findByStationCodeForApi(String stationCode) {
        return new ResponseResult();
    }

    @Override
    public ResponseResult findLogisticCodeByStationCode(String stationCode) {
        return new ResponseResult();
    }
}
