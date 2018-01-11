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


    public static final String OUT_STORAGE_DONE = "OUT_STORAGE_DONE";

    public static final String IN_STORAGE_DONE = "IN_STORAGE_DONE";

}
