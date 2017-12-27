package cn.sisyphe.bill.domain.base.model.db;

import cn.sisyphe.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.bill.domain.base.model.location.Station;
import cn.sisyphe.bill.domain.base.model.location.Storage;
import cn.sisyphe.bill.domain.base.model.location.Supplier;
import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;

/**
 * Created by heyong on 2017/12/26 13:54
 * Description: 数据库出入位置，用于数据库存储
 *
 * @author heyong
 * @Embeddable 将所有属性值自动加入引用实体中
 */
@Embeddable
public class DbStation {

    /**
     * 入库站点代码
     */
    private String inStationCode;
    /**
     * 入库库房代码
     */
    private String inStorageCode;
    /**
     * 出库站点代码
     */
    private String outStationCode;
    /**
     * 出库库房代码
     */
    private String outStorageCode;
    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 设置入库位置
     * @param inLocation
     */
    public void setInLocation(AbstractLocation inLocation) {
        setLocation(inLocation, 1);
    }

    /**
     * 设置出库位置
     * @param outLocation
     */
    public void setOutLocation(AbstractLocation outLocation) {
        setLocation(outLocation, -1);
    }

    /**
     * 获取入库位置
     * @return
     */
    public AbstractLocation getInLocation() {
        return getLocation(inStationCode, inStorageCode);
    }

    /**
     * 获取出库位置
     * @return
     */
    public AbstractLocation getOutLocation() {
        return getLocation(outStationCode, outStorageCode);
    }

    /**
     * 设置位置
     * @param location
     * @param inOut
     */
    private void setLocation(AbstractLocation location, int inOut) {
        if (location instanceof Station) {
            Station station = (Station) location;

            if (inOut == 1) {
                inStationCode = station.code();
                inStorageCode = station.getStorage() != null ? station.getStorage().code() : null;

            } else {
                outStationCode = station.code();
                outStorageCode = station.getStorage() != null ? station.getStorage().code() : null;
            }

        } else if (location instanceof Supplier) {
            supplierCode = location.code();
        }
    }

    /**
     * 获取位置
     * @param stationCode
     * @param storageCode
     * @return
     */
    private AbstractLocation getLocation(String stationCode, String storageCode) {
        if (!StringUtils.isEmpty(stationCode)) {
            Station station = new Station(stationCode);

            if (!StringUtils.isEmpty(storageCode)) {
                Storage storage = new Storage(storageCode);
                station.setStorage(storage);
            }
            return station;
        }

        return !StringUtils.isEmpty(supplierCode) ? new Supplier(supplierCode) : null;
    }

    @Override
    public String toString() {
        return "DbStation{" +
                "inStationCode='" + inStationCode + '\'' +
                ", inStorageCode='" + inStorageCode + '\'' +
                ", outStationCode='" + outStationCode + '\'' +
                ", outStorageCode='" + outStorageCode + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                '}';
    }
}
