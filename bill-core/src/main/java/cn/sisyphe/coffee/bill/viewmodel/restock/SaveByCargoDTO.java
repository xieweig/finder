package cn.sisyphe.coffee.bill.viewmodel.restock;

import cn.sisyphe.coffee.bill.domain.base.model.goods.RawMaterial;

import java.util.Date;
import java.util.List;

/**
 *@date: 2018/1/2
 *@description: 
 *@author：xieweiguang
 */
public class SaveByCargoDTO {

    /**
     *
     *notes : 单据信息
     *  单号 出库库位 单据备注
     */
    private String billCode;

    private String storageCode;

    private String remarks;

    /**
     *
     *notes :ref BaseBill
     *   录单日期起始
     */
    private Date createTime;
    /**
     *
     *notes : ref Bill.DBStation
     *  入库站点
     */
    private String inStationCode;
    /**
     *
     *notes : ref Bill.DBStation
     *  出库站点
     */
    private String outStationCode;
    /**
     *
     *notes :计划单信息
     *  录单时间 出库站点  入库站点
     */
    private List<BillDetailsDTO> lists;

}
