package cn.sisyphe.bill.domain.base.purpose;

/**
 * Created by heyong on 2017/12/19 14:03
 * Description: 计划用途处理器
 * @author heyong
 */
public class PlanPurpose extends AbstractBillPurpose {

    /**
     * 用途处理器
     */
    @Override
    public void handle() {
        System.out.println("spliting behavior!");
    }


}
