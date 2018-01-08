package cn.sisyphe.coffee.bill.application.shared;

import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.infrastructure.share.station.repo.StationRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.supplier.repo.SupplierRepository;
import cn.sisyphe.coffee.bill.infrastructure.share.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Amy on 2018/1/4.
 *         describe：
 */
@Service
public class SharedManager {
    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserRepository userRepository;


    public String findLogisticCodeByStationCode(String stationCode) {
        return stationRepository.findLogisticCodeByStationCode(stationCode);
    }

    public Station findStationByStationCode(String stationCode) {
        return stationRepository.findByStationCode(stationCode);
    }

    public Supplier findSupplierBySupplierCode(String supplierCode) {
        return supplierRepository.findBySupplierCode(supplierCode);
    }

    /**
     * 根据用户名称模糊查询用户编码集合
     *
     * @param userName
     * @return
     */
    public List<String> findByLikeUserName(String userName) {
        return userRepository.findByLikeUserName(userName);
    }

    /**
     * 根据用户编码称查询用户姓名
     *
     * @param userCode 用户名称
     * @return
     */
    public String findOneByUserCode(String userCode){
        return userRepository.findOneByUserCode(userCode);
    }
}
