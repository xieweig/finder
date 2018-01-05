package cn.sisyphe.coffee.bill.application.shared;

import cn.sisyphe.coffee.bill.infrastructure.share.station.repo.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Amy on 2018/1/4.
 * describe：
 */
@Service
public class SharedManager {
    @Autowired
    private StationRepository stationRepository;
    public String findLogisticCodeByStationCode(String stationCode) {
        return stationRepository.findLogisticCodeByStationCode(stationCode);
    }
}
