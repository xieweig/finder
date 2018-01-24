package cn.sisyphe.coffee.bill.util;

import ch.lambdaj.function.closure.Switcher;
import cn.sisyphe.coffee.bill.domain.adjust.model.AdjustBill;
import cn.sisyphe.coffee.bill.domain.allot.model.AllotBill;
import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import cn.sisyphe.coffee.bill.domain.base.model.enums.BillTypeEnum;
import cn.sisyphe.coffee.bill.domain.base.model.location.AbstractLocation;
import cn.sisyphe.coffee.bill.domain.base.model.location.Station;
import cn.sisyphe.coffee.bill.domain.base.model.location.Supplier;
import cn.sisyphe.coffee.bill.domain.delivery.model.DeliveryBill;
import cn.sisyphe.coffee.bill.domain.mistake.model.MistakeBill;
import cn.sisyphe.coffee.bill.domain.purchase.model.PurchaseBill;
import cn.sisyphe.coffee.bill.domain.restock.model.RestockBill;
import cn.sisyphe.framework.web.ResponseResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * 将resposneResult转换成Bill
 *
 * @date 2018/1/23 19:03
 */
public class ResponseResultMapUtil {

    public static final String STATION_CODE = "stationCode";
    public static final String IN_LOCATION = "inLocation";
    public static final String OUT_LOCATION = "outLocation";

    public Bill convertBillFromResponse(ResponseResult responseResult) {
        Map properties = (Map) responseResult.getResult().get("bill");
        Bill bill = JSON.parseObject(JSON.toJSONString(responseResult.getResult().get("bill")),
                (Type) getClazz((String) properties.get("billType")), new ResponseResultToBillExtraProcessor());
        wapperLocation(bill, properties);
        return bill;
    }

    private Class<Bill> getClazz(String billType) {

        BillTypeEnum billTypeEnum = BillTypeEnum.valueOf(billType);
        Switcher<Class> switcher = new Switcher<Class>()
                .addCase(BillTypeEnum.PURCHASE, PurchaseBill.class)
                .addCase(BillTypeEnum.DELIVERY, DeliveryBill.class)
                .addCase(BillTypeEnum.RETURNED, RestockBill.class)
                .addCase(BillTypeEnum.RESTOCK, RestockBill.class)
                .addCase(BillTypeEnum.ADJUST, AdjustBill.class)
                .addCase(BillTypeEnum.MISTAKE, MistakeBill.class)
                .addCase(BillTypeEnum.ALLOT, AllotBill.class);

        return switcher.exec(billTypeEnum);
    }

    private void wapperLocation(Bill bill, Map properties) {
        bill.setInLocation(transferLocation((JSONObject) properties.get(IN_LOCATION)));
        bill.setOutLocation(transferLocation((JSONObject) properties.get(OUT_LOCATION)));

    }

    private AbstractLocation transferLocation(JSONObject jsonObject) {
        if (jsonObject.containsKey(STATION_CODE)) {
            return JSONObject.toJavaObject(jsonObject, Station.class);
        }
        return JSONObject.toJavaObject(jsonObject, Supplier.class);

    }
}
