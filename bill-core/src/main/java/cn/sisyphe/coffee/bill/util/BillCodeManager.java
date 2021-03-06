package cn.sisyphe.coffee.bill.util;

import cn.sisyphe.framework.common.utils.HexUtil;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by XiongJing on 2018/1/10.
 * remark：单号生成器
 * version: 1.0
 *
 * @author XiongJing
 */

public class BillCodeManager {
    private static AtomicInteger addInteger = new AtomicInteger();

    public static String getBillCodeFun(String billTypeCode, String stationCode) {
        StringBuffer sb = new StringBuffer();
        sb.append(billTypeCode);
        sb.append(stationCode);
        Calendar cc = Calendar.getInstance();
        int year = cc.get(Calendar.YEAR);
        int month = cc.get(Calendar.MONTH);
        int date = cc.get(Calendar.DATE);
        sb.append(year);
        sb.append(month);
        sb.append(date);
        sb.append(HexUtil.intTo32StrSeat(getProcessID(), 4));
        // 补零操作
        String withZore = frontCompWithZore(addInteger.addAndGet(1), 6);
        sb.append(withZore);


        return sb.toString();
    }

    /**
     * 补零操作
     *
     * @param sourceDate   元数据
     * @param formatLength 长度
     * @return
     */
    public static String frontCompWithZore(int sourceDate, int formatLength) {

        String newString = String.format("%0" + formatLength + "d", sourceDate);

        return newString;

    }

    /**
     * 获取当前线程
     *
     * @return
     */
    public static final int getProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return Integer.valueOf(runtimeMXBean.getName().split("@")[0]).intValue();
    }
}
