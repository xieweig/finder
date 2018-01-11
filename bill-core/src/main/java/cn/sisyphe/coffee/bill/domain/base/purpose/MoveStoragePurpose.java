package cn.sisyphe.coffee.bill.domain.base.purpose;


/**
 * @author ncmao
 * 调拨用途处理器
 */
public class MoveStoragePurpose extends AbstractBillPurpose {


    @Override
    public void handle() {

        System.out.println("调拨中");
    }
}
