package cn.sisyphe.coffee.bill.infrastructure.share.cargo.repo;

import cn.sisyphe.coffee.bill.infrastructure.share.cargo.CargoCloudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ncmao
 * @Date 2017/12/29 10:21
 * @description
 */
@Repository
public class CargoRepositoryImpl implements CargoRepository {

    @Autowired
    private CargoCloudRepository cargoCloudRepository;


    @Override
    public List<String> findCargoCodesByCargoName(String cargoName) {
        Map<String, Object> response = cargoCloudRepository.findCargoCodesByCargoName(cargoName).getResult();
        if (!response.containsKey("cargoList")) {
            return Arrays.asList("NOT_EXISTS");
        }
        return (List<String>) response.get("cargoList");
    }
}
