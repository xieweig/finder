package cn.sisyphe.coffee.bill.infrastructure.share.station.repo;

import cn.sisyphe.coffee.bill.domain.base.model.enums.StationType;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.infrastructure.share.station.StationCloudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

import static cn.sisyphe.coffee.bill.domain.base.model.enums.StationType.STORE;
import static cn.sisyphe.coffee.bill.domain.base.model.enums.StationType.SUPPLIER;

/**
 * @author ncmao
 * @Date 2017/12/29 10:21
 * @description
 */
@Repository
public class StationRepositoryImpl implements StationRepository {

    @Autowired
    private StationCloudRepository stationCloudRepository;


    @Override
    public Station findByStationCode(String stationCode) {
        Map<String, Object> response = stationCloudRepository.findByStationCodeForApi(stationCode).getResult();
        if (!response.containsKey("stationApiReturnDTO")) {
            return null;
        }
        LinkedHashMap<String, String> properties = (LinkedHashMap) response.get("stationApiReturnDTO");
        Station station = new Station(properties.get("stationCode"));
        station.setStationName(properties.get("stationName"));
        station.setStationType(getStationType(properties.get("siteType")));
        return station;
    }

    private StationType getStationType(String siteType) {

        if (SUPPLIER.name().equals(siteType)) {
            return SUPPLIER;
        }
        return STORE;
    }
}
