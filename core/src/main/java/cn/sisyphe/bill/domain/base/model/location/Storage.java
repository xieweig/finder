package cn.sisyphe.bill.domain.base.model.location;

/**
 * Created by heyong on 2017/12/26 10:39
 * Description: 库房
 *
 * @author heyong
 */
public class Storage extends AbstractLocation {

    /**
     * 库房代码
     */
    private String storageCode;
    /**
     * 库房名称
     */
    private String storageName;


    public Storage(String storageCode) {
        this.storageCode = storageCode;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }


    /**
     * 位置代码
     *
     * @return
     */
    @Override
    public String code() {
        return storageCode;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "storageCode='" + storageCode + '\'' +
                ", storageName='" + storageName + '\'' +
                "} " + super.toString();
    }
}
