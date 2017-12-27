package cn.sisyphe.coffee.bill.domain.transmit.enums;


/**
 * 打包类型
 *
 * @author yichuan
 * @company 西西弗文化传播
 * @Date 2017/12/27 16:15
 **/
public enum PackAgeType {

    //一单多包
    ONE_BILL_TO_MANY_PACKAGE,

    //一单一包
    ONE_BILL_TO_ONE_PACKAGE,

    //多单一包
    MANY_BILL_TO_ONE_PACKAGE
}
