package cn.sisyphe.coffee.bill.util;

/**
 * Created by XiongJing on 2017/12/28.
 * remark：项目魔法值
 * version:1.0
 *
 * @author XiongJing
 */
public final class Constant {
    /**
     * 员工管理范围
     */
    public static final String OAUTH_USER_MANAGEMENT_SCOPE = "OAUTH_USER_MANAGEMENT_SCOPE";

    /**
     * RabbitMQ-EXCHANGE
     */
    public static final String BILL_EXCHANGE = "cn_sisyphe_coffee_bill";

    /**
     * COMMON-NAME
     */
    public static final String COMMON_NAME = "bill-stock-done";

    // 进货库：STOCKLIBRARY
    // 正常库：NORMALLIBRARY
    // 仓储库：STORAGELIBRARY
    // 退货库：RETURNLIBRARY
    // 在途库：PASSAGELIBRARY
    // 预留库：RESERVEDLIBRARY


    /**
     * 出库冲减完成
     */
    public static final String OUT_STORAGE_OFFSET_DONE = "OUT_STORAGE_OFFSET_DONE";

    /**
     * 入库冲减完成
     */
    public static final String IN_STORAGE_OFFSET_DONE = "IN_STORAGE_OFFSET_DONE";

    /**
     * 库位类型-误差库类型
     */
    public static final String STORAGE_TYPE_WCK = "WCK";
    /**
     * 库位名称-误差库名称
     */
    public static final String STORAGE_NAME_WCK = "误差库";

    /**
     * 在途库
     */
    public static final String SHIPPING_STORAGE = "ON_STORAGE";

}
