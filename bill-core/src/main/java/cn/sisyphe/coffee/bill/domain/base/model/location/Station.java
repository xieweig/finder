package cn.sisyphe.coffee.bill.domain.base.model.location;

/**
 * Created by heyong on 2017/12/21 10:11
 * Description: 站点
 *
 * @author heyong
 */
public class Station extends AbstractLocation {

    /**
     * 站点代码
     */
    private String stationCode;

    /**
     * 站点名称
     */
    private String stationName;

    /**
     * 站点类型
     */
    private String stationType;

    /**
     * 库房
     */
    private Storage storage;

    public Station(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * 位置代码
     *
     * @return
     */
    @Override
    public String code() {
        return stationCode;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationCode='" + stationCode + '\'' +
                ", stationName='" + stationName + '\'' +
                ", stationType='" + stationType + '\'' +
                ", storage=" + storage +
                "} " + super.toString();
    }
}
