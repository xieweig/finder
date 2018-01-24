package cn.sisyphe.coffee.bill.util;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;

/**
 * @Date 2018/1/23 18:25
 * @description
 */
public class ResponseResultToBillExtraProcessor implements ExtraProcessor {

    private final String RAW_MATERIAL = "rawMaterial";
    private final String RAW_MATERIAL_CODE = "rawMaterialCode";

    @Override
    public void processExtra(Object target, String name, Object source) {
        System.out.println(name);

        // 原料判断
        if (target instanceof BillDetail && RAW_MATERIAL.equals(name)) {
            BillDetail billDetail = (BillDetail) target;
            billDetail.setGoods(getRawMaterial((JSONObject) source));
        }

    }


    @SuppressWarnings("Duplicates")
    private RawMaterial getRawMaterial(JSONObject source) {

        if (source.containsKey(RAW_MATERIAL_CODE)) {
            return JSONObject.parseObject(source.toJSONString(), RawMaterial.class);
        } else {
            RawMaterial rawMaterial = new RawMaterial();
            rawMaterial.setCargo(JSONObject.parseObject(source.toJSONString(), Cargo.class));
            return rawMaterial;
        }
    }
}
