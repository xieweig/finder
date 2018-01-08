package cn.sisyphe.coffee.bill.viewmodel.adjust;

import cn.sisyphe.coffee.bill.domain.base.model.location.Storage;

import java.util.Date;
import java.util.List;

/**
 * Created by XiongJing on 2018/1/8.
 * remark：新增和修改调剂单DTO
 * version: 1.0
 *
 * @author XiongJing
 */
public class AddAdjustBillDTO {

    /**
     * 单据编码
     */
    private String billCode;
    /**
     * 出库时间
     */
    private Date outWareHouseTime;
    /**
     * 录单人编码
     */
    private String operatorCode;
    /**
     * 审核人编码
     */
    private String auditPersonCode;
    /**
     * 出库站点编码
     */
    private String outStationCode;
    /**
     * 出库库位
     */
    private Storage outStorage;

    /**
     * 入库站点编码
     */
    private String inStationCode;

    /**
     * 单据属性
     */
    private String billType;

    /**
     * 出库状态编码
     */
    private String outStatusCode;

    /**
     * 提交状态
     */
    private String submitState;

    /**
     * 审核状态
     */
    private String auditState;

    /**
     * 调剂数量
     */
    private Integer adjustNumber;

    /**
     * 调剂品种数
     */
    private Integer varietyNumber;

    /**
     * 计划备注
     */
    private String planMemo;

    /**
     * 出库备注
     */
    private String outStorageMemo;

    /**
     * 审核意见
     */
    private String auditMemo;

    /**
     * 货物/原料明细信息
     */
    private List<AdjustBillDetailDTO> details;



}
