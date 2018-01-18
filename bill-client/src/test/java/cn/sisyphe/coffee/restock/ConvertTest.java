package cn.sisyphe.coffee.restock;

import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @Author: xie_wei_guang
 * @Date: 2018/1/17
 * @Description: 测试spring bean util copyProperties
 */
public class ConvertTest {

    public class EntityTest{
        private String name;
        private List<Date> dates;
        private Station station;
        private Set<Cargo> content;
        private String ultra="ultra";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Date> getDates() {
            return dates;
        }

        public void setDates(List<Date> dates) {
            this.dates = dates;
        }

        public Station getStation() {
            return station;
        }

        public void setStation(Station station) {
            this.station = station;
        }

        public Set<Cargo> getContent() {
            return content;
        }

        public void setContent(Set<Cargo> content) {
            this.content = content;
        }
    }
    public class DTOTest{
        private String name="unknown";
        private List<Date> dates;
        private Station station;
        private Set<Cargo> content;
        private String extra ="default";

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Date> getDates() {
            return dates;
        }

        public void setDates(List<Date> dates) {
            this.dates = dates;
        }

        public Station getStation() {
            return station;
        }

        public void setStation(Station station) {
            this.station = station;
        }

        public Set<Cargo> getContent() {
            return content;
        }

        public void setContent(Set<Cargo> content) {
            this.content = content;
        }
    }
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Random random= new Random();
    @Test
    public void stick(){
        EntityTest entityTest = new EntityTest();
        DTOTest dtoTest = new DTOTest();
        Set<Cargo> cargos = new HashSet<>();
        for (int i = 0; i < 2; i++) {
            cargos.add(new Cargo("cargo:"+random.nextInt(100)));
        }
        entityTest.setContent(cargos);
        entityTest.setName("name="+random.nextInt(100));
        List<Date> dates = new ArrayList<>();
        for (int i = 0; i <2 ; i++) {
            dates.add(new Date());
        }
        entityTest.setDates(dates);
        entityTest.setStation(new Station("station"+random.nextInt()));
        logger.info(ToStringBuilder.reflectionToString(entityTest));

        BeanUtils.copyProperties(entityTest,dtoTest);
        logger.info(ToStringBuilder.reflectionToString(dtoTest));
    }


}
