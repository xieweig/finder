package cn.sisyphe.coffee.bill.domain.base.model.enums;

/**
 * @Date 2018/1/8 17:34
 * @description
 */
public enum SourcePlanTypeEnum {
    //配送计划转
    DELIVERY("配送计划生成"),

    //调剂计划转
    ADJUST("调剂计划生成"),

    //退货计划转
    RETURNED("退货计划生成"),

    //退库计划转
    RESTOCK("退库计划生成"),

    //无计划转
    NOPLAN("");


    private String description;


    SourcePlanTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
