package cn.sisyphe.coffee.bill.domain.base.behavior;

import cn.sisyphe.coffee.bill.domain.base.model.Bill;
import org.springframework.data.domain.Page;

public class SearchBehavior extends AbstractBillBehavior{

    @Override
    public void doAction() {
        Page<Bill> billPage = null;
    }
}
