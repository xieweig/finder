package cn.sisyphe.coffee.bill.util;

import cn.sisyphe.coffee.bill.domain.base.model.goods.Cargo;
import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
import cn.sisyphe.coffee.bill.viewmodel.base.BillDetailDTO;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;

/**
 * Created by heyong on 2018/1/18 10:49
 * Description:
 * @author heyong
 */
public class DtoExtraProcessor implements ExtraProcessor {

    @Override
    public void processExtra(Object target, String name, Object source) {
        if (target instanceof BillDetailDTO && "goods".equals(name)) {
            BillDetailDTO billDetailDTO = (BillDetailDTO) target;
            billDetailDTO.setRawMaterial(getRawMaterial((JSONObject) source));

            System.err.println(name + "-" + target + "-" + source);
        }

        //System.err.println(name + "-" + target + "-" + source);
    }

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
