package cn.sisyphe.coffee.station;

import cn.sisyphe.coffee.bill.ClientApplication;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.infrastructure.share.station.repo.StationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ncmao
 * @Date 2018/1/3 16:41
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class StationCloudIntegrationTest {

    @Autowired
    private StationRepository stationRepository;


    @Test
    public void shouldFindOneStation() {
        Station station = stationRepository.findByStationCode("AVAB01");
        System.out.println(station.getStationType().name());
    }
}
