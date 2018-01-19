package cn.sisyphe.coffee.bill.infrastructure.share.storage;

import java.util.Date;

/**
 * Created by XiongJing on 2018/1/6.
 * remark：
 * version:
 */
public class TempStorage {

    /**
     * 临时库房ID
     */
    private Long tempStorageId;

    /**
     * 临时库房编码
     */
    private String tempStorageCode;

    /**
     * 临时库房名称
     */
    private String tempStorageName;

    /**
     * 库房类型
     */
    private String storageType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 版本号
     */
    private Long version;

    public TempStorage() {
    }

    public TempStorage(String tempStorageName, String storageType) {
        this.tempStorageName = tempStorageName;
        this.storageType = storageType;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public Long getTempStorageId() {
        return tempStorageId;
    }

    public void setTempStorageId(Long tempStorageId) {
        this.tempStorageId = tempStorageId;
    }

    public String getTempStorageCode() {
        return tempStorageCode;
    }

    public void setTempStorageCode(String tempStorageCode) {
        this.tempStorageCode = tempStorageCode;
    }

    public String getTempStorageName() {
        return tempStorageName;
    }

    public void setTempStorageName(String tempStorageName) {
        this.tempStorageName = tempStorageName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TempStorage{" +
                "tempStorageId=" + tempStorageId +
                ", tempStorageCode='" + tempStorageCode + '\'' +
                ", tempStorageName='" + tempStorageName + '\'' +
                ", storageType='" + storageType + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", version=" + version +
                '}';
    }
}
