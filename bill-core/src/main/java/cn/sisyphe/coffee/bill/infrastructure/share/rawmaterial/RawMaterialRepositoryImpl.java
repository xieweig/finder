package cn.sisyphe.coffee.bill.infrastructure.share.rawmaterial;
import cn.sisyphe.coffee.bill.infrastructure.share.rawmaterial.cloud.RawMaterialCloudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Amy
 */
@Repository
public class RawMaterialRepositoryImpl implements RawMaterialRepository {

    @Autowired
    private RawMaterialCloudRepository rawMaterialCloudRepository;


    @Override
    public List<String> findRawMaterialCodesByRawMaterialName(String rawMaterialName) {
        Map<String, Object> response = rawMaterialCloudRepository.findRawMaterialCodesByRawMaterialName(rawMaterialName).getResult();
        if (!response.containsKey("materialCodeList")) {
            return Arrays.asList("NOT_EXISTS");
        }
        return (List<String>) response.get("materialCodeList");
    }
}
