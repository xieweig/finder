package cn.sisyphe.coffee.bill.application.shared;

import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.infrastructure.share.station.repo.StationRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.supplier.repo.SupplierRepository;
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

    @Autowired
    private SupplierRepository supplierRepository;


    public String findLogisticCodeByStationCode(String stationCode) {
        return stationRepository.findLogisticCodeByStationCode(stationCode);
    }

    public Station findStationByStationCode(String stationCode) {
        return stationRepository.findByStationCode(stationCode);
    }

    public Supplier findSupplierBySupplierCode(String supplierCode) {
        return supplierRepository.findBySupplierCode(supplierCode);
    }
}
