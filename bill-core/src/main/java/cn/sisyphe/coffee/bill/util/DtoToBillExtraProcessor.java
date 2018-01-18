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

import java.lang.reflect.Type;

/**
 * Created by heyong on 2018/1/18 10:49
 * Description:
 *
 * @author heyong
 */
public class DtoToBillExtraProcessor implements ExtraProcessor {

    @Override
    public void processExtra(Object target, String name, Object source) {
        if (target instanceof BillDetail && "rawMaterial".equals(name)) {
            BillDetail billDetail = (BillDetail) target;
            billDetail.setGoods(getRawMaterial((JSONObject) source));

//            System.err.println(name + "-" + target + "-" + source);
        }

        //System.err.println("1 " + name + "-" + target + "-" + source);
    }


    @SuppressWarnings("Duplicates")
    private RawMaterial getRawMaterial(JSONObject source) {
        if (source.containsKey("rawMaterialCode")) {
            return JSONObject.parseObject(source.toJSONString(), RawMaterial.class);
        } else {
            RawMaterial rawMaterial = new RawMaterial();
            rawMaterial.setCargo(JSONObject.parseObject(source.toJSONString(), Cargo.class));
            return rawMaterial;
        }
    }

}
