package cn.sisyphe.coffee.bill.util;

import cn.sisyphe.coffee.bill.domain.base.model.BillDetail;
import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDetailDTO;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.serializer.PropertyFilter;

import java.lang.reflect.Type;

/**
 * Created by heyong on 2018/1/18 10:49
 * Description: abstractGoods 处理
 *
 * @author heyong
 */
public class DtoToBillExtraProcessor implements ExtraProcessor {

    private final String RAW_MATERIAL = "rawMaterial";
    private final String RAW_MATERIAL_CODE = "rawMaterialCode";

    @Override
    public void processExtra(Object target, String name, Object source) {

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
