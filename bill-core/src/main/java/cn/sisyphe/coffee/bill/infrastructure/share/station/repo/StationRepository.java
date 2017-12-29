package cn.sisyphe.coffee.bill.infrastructure.share.station.repo;

import cn.sisyphe.coffee.bill.domain.base.model.location.Station;

/**
 * @author ncmao
 * @Date 2017/12/29 10:21
 * @description
 */
public interface StationRepository {


    Station findByStationCode(String stationCode);
}
