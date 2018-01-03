package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;
/**
 *@date: 2018/1/3
 *@description:
 *@author：xieweiguang
 */
public class BillDetailsDTO {
    /**
     *
     *notes : 货物信息
     *  货物编码 货物名称 所属原料 规格spec 实际数量 货物备注
     */
    private String cargoCode;

    private String cargoName;

    private String rawMaterialName;

    private Integer spec;

    private Integer number;

    private String cargoRemarks;
}
